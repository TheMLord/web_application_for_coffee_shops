package naumen.course.coffeeShop.controllers.admins;

import naumen.course.coffeeShop.dto.ProductTypeDto;
import naumen.course.coffeeShop.services.ProductTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AddProductTypeController {
    private final ProductTypeService productTypeService;

    public AddProductTypeController(ProductTypeService productTypeService) {
        this.productTypeService = productTypeService;
    }

    @GetMapping("/addProductType")
    public String getAddProductTypePage() {
        return "admin/addProductType";
    }

    @PostMapping("/addProductType")
    public String addNewProductType(ProductTypeDto productTypeDto, Model model) {
        try {
            productTypeService.addProductType(productTypeDto);
            model.addAttribute("message", "Новый тип продукта добавлен");
            return "admin/addProductType";
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
            return "admin/addProductType";
        }
    }
}
