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

    User findByPhoneNumber(String phoneNumber);

    List<User> findAll();

}
