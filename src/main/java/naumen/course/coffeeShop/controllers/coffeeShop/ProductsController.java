package naumen.course.coffeeShop.controllers.coffeeShop;


import naumen.course.coffeeShop.services.CoffeeShopService;
import naumen.course.coffeeShop.services.ProductsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/coffeeShop")
public class ProductsController {
    private final ProductsService productsService;
    private final CoffeeShopService coffeeShopService;


    public ProductsController(ProductsService productsService, CoffeeShopService coffeeShopService) {
        this.productsService = productsService;
        this.coffeeShopService = coffeeShopService;
    }

//    @GetMapping("/getAllProducts")
//    @ResponseBody
//    public List<Product> getAllProducts() {
//        return productsService.getAllProducts();
//    }

    @GetMapping("/addProduct")
    public String getPageAddProduct() {
        return "addProduct";
    }

//    @PostMapping("/addProduct")
//    public String addProduct(Model model, @RequestParam String nameProduct, @RequestParam Integer amountProduct) {
//        try {
//            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//            var coffeeShop = coffeeShopService.getCoffeeShop(auth.getName());
//            var coffeeShopId = coffeeShop.getId();
//
//            productsService.addProduct(
//                    new Product(coffeeShopId, ProductType.valueOf(nameProduct), amountProduct),
//                    coffeeShopId
//            );
//            model.addAttribute("message", "продукт добавлен");
//        } catch (Exception e) {
//            model.addAttribute("message", e.getMessage());
//
//        }
//        return "addProduct";
//    }
}
