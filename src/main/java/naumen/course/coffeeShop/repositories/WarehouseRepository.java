package naumen.course.coffeeShop.repositories;

import naumen.course.coffeeShop.models.CoffeeShop;
import naumen.course.coffeeShop.models.Warehouse;
import naumen.course.coffeeShop.models.ProductType;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface WarehouseRepository extends CrudRepository<Warehouse, Long> {
    Optional<Warehouse> findFirstByIdShopAndProductTypeId(Long coffeeShopId, Long productTypeId);
}
