package naumen.course.coffeeShop.services;

import naumen.course.coffeeShop.repositories.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {
    private final ProductRepository productRepository;
    private final CoffeeShopService coffeeShopService;

    public ReviewService(ProductRepository productRepository, CoffeeShopService coffeeShopService) {
        this.productRepository = productRepository;
        this.coffeeShopService = coffeeShopService;
    }


}
