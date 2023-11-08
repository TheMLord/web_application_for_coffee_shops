package naumen.course.coffeeShop.repositories;

import naumen.course.coffeeShop.models.CoffeeShop;
import org.springframework.data.repository.CrudRepository;

public interface CoffeeShopRepository extends CrudRepository<CoffeeShop, Long> {

    CoffeeShop findByLogin(String login);
}
