package naumen.course.coffeeShop.services;

import jakarta.transaction.Transactional;
import naumen.course.coffeeShop.models.Product;
import naumen.course.coffeeShop.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductsService {
    private final ProductRepository productRepository;

    public ProductsService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public void addProduct(Product newProduct, Long coffeeShopId) {
        var optionalProduct = productRepository.findByIdShopAndProductType(coffeeShopId, newProduct.getProductType());

        if (optionalProduct.isPresent()) {
            var product = optionalProduct.get();
            var newAmount = product.getAmount() + newProduct.getAmount();
            productRepository.updateAmountByIdShopAndProductType(coffeeShopId, product.getProductType(), newAmount);
        } else {
            productRepository.save(newProduct);
        }
    }

    public List<Product> getAllProducts() {
        return (List<Product>) productRepository.findAll();
    }
}
