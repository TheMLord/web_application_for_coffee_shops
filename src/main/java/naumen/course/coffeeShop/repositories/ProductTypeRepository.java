package naumen.course.coffeeShop.repositories;

import naumen.course.coffeeShop.models.ProductType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductTypeRepository extends CrudRepository<ProductType, Long> {
    Optional<ProductType> findProductTypeByNameProduct(String nameProduct);

    void deleteProductTypeByNameProduct(String nameProduct);

    List<ProductType> findAll();
}
