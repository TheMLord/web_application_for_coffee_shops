package naumen.course.coffeeShop.repositories;

import naumen.course.coffeeShop.models.CoffeeShop;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CoffeeShopRepository extends CrudRepository<CoffeeShop, Long> {

    CoffeeShop findByLogin(String login);

    void deleteByLogin(String login);

    List<CoffeeShop> findAll();

}
