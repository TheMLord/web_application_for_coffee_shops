package naumen.course.coffeeShop.controllers.coffeeShop;


import naumen.course.coffeeShop.models.Warehouse;
import naumen.course.coffeeShop.models.ProductType;
import naumen.course.coffeeShop.repositories.ProductTypeRepository;
import naumen.course.coffeeShop.services.CoffeeShopService;
import naumen.course.coffeeShop.services.WarehouseService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping("/coffeeShop")
public class WarehouseController {
    private final WarehouseService warehouseService;
    private final CoffeeShopService coffeeShopService;
    private final ProductTypeRepository productTypeRepository;


    public WarehouseController(WarehouseService warehouseService, CoffeeShopService coffeeShopService, ProductTypeRepository productTypeRepository) {
        this.warehouseService = warehouseService;
        this.coffeeShopService = coffeeShopService;
        this.productTypeRepository = productTypeRepository;
    }

    @GetMapping("/addProduct")
    public String getPageAddProduct() {
        return "addProduct";
    }

    @PostMapping("/addProduct")
    public String addProduct(Model model, @RequestParam String nameProduct, @RequestParam Integer amountProduct) {
        warehouseService.addWarehouse(model, nameProduct, amountProduct);
        return "addProduct";
    }
}
