package naumen.course.coffeeShop.services;

import naumen.course.coffeeShop.repositories.WarehouseRepository;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {
    private final WarehouseRepository productRepository;
    private final CoffeeShopService coffeeShopService;

    public ReviewService(WarehouseRepository productRepository, CoffeeShopService coffeeShopService) {
        this.productRepository = productRepository;
        this.coffeeShopService = coffeeShopService;
    }


}
