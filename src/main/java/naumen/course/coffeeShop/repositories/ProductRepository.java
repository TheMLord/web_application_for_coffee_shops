package naumen.course.coffeeShop.repositories;

import naumen.course.coffeeShop.models.Product;
import naumen.course.coffeeShop.models.ProductType;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProductRepository extends CrudRepository<Product, Long> {
    Optional<Product> findByIdShopAndProductType(Long idShop, ProductType productType);

    @Modifying
    @Query("UPDATE Product p SET p.amount = :newAmount WHERE p.idShop = :idShop AND p.productType = :productType")
    void updateAmountByIdShopAndProductType(Long idShop, ProductType productType, Integer newAmount);
}
