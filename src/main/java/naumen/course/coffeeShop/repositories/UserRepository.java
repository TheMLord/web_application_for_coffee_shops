package naumen.course.coffeeShop.repositories;

import naumen.course.coffeeShop.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {
    User findByName(String Username);
//    @Query("SELECT * FROM postgres.")
    User findByPhoneNumber(String phoneNumber);
}
