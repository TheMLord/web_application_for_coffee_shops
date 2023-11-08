package naumen.course.coffeeShop.repositories;

import naumen.course.coffeeShop.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, String> {
    User findByName(String Username);
//    @Query("SELECT * FROM postgres.")
    User findByPhoneNumber(String phoneNumber);

    List<User> findAll();
}
