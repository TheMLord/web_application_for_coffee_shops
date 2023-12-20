package naumen.course.coffeeShop.controllers.admins;

import naumen.course.coffeeShop.services.CoffeeShopService;
import naumen.course.coffeeShop.services.ProductTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class ReviewController {
    private final CoffeeShopService coffeeShopService;
    private final ProductTypeService productTypeService;

    public ReviewController(CoffeeShopService coffeeShopService, ProductTypeService productTypeService) {
        this.coffeeShopService = coffeeShopService;
        this.productTypeService = productTypeService;
    }

    @GetMapping("/review")
    public String getReviewPage() {
        return "admin/reviews/review";
    }

    @PostMapping("/review/getCoffeeShops")
    public String getCoffeeShopsList(Model model) {
        var listCoffeeShops = coffeeShopService.getCoffeeShopList();
        model.addAttribute("coffeeShops", listCoffeeShops);
        return "admin/reviews/reviewCoffeeShops";
    }

    @PostMapping("/review/getProductTypesList")
    public String getProductTypesList(Model model) {
        var listTypesProducts = productTypeService.getProductTypeList();
        model.addAttribute("products", listTypesProducts);
        return "admin/reviews/reviewTypeProducts";
    }
}