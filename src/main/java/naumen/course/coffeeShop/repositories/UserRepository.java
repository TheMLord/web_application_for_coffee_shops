package naumen.course.coffeeShop.repositories;

import jakarta.transaction.Transactional;
import naumen.course.coffeeShop.models.User;
import org.hibernate.annotations.Parameter;
import org.springframework.data.annotation.Id;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, String> {
    User findByName(String Username);
//    @Query("SELECT * FROM postgres.")
    User findByPhoneNumber(String phoneNumber);

    List<User> findAll();

    @Modifying
    @Query("UPDATE User p SET p.name = :name, p.mail = :mail, p.scores = :scores WHERE p.phoneNumber = :phoneNumber")
    void update(String phoneNumber, String name, String mail, Long scores);
    @Modifying
    @Query("UPDATE User p SET p.name = :name, p.scores = :scores WHERE p.phoneNumber = :phoneNumber")
    void updateWithoutMail(String phoneNumber, String name, Long scores);

}
