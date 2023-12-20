package naumen.course.coffeeShop.controllers.admins;

import naumen.course.coffeeShop.services.ProductTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
public class DeleteProductTypeController {
    private final ProductTypeService productTypeService;

    public DeleteProductTypeController(ProductTypeService productTypeService) {
        this.productTypeService = productTypeService;
    }

    @GetMapping("/deleteTypeProduct")
    public String getDeleteTypeProductPage() {
        return "admin/deleteProductType";
    }


    @PostMapping("/deleteTypeProduct")
    public String getDeleteTypeProductPage(@RequestParam String nameProduct, Model model) {
        try {
            productTypeService.deleteProductType(nameProduct);
            model.addAttribute("message", "Тип продукта успешно удален");
            return "admin/deleteProductType";
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
            return "admin/deleteProductType";
        }
    }
}
