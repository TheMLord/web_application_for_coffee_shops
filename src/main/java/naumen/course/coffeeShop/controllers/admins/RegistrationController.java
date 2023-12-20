package naumen.course.coffeeShop.controllers.admins;

import naumen.course.coffeeShop.dto.CoffeeShopDTO;
import naumen.course.coffeeShop.services.CoffeeShopService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class RegistrationController {
    private final CoffeeShopService coffeeShopService;

    public RegistrationController(CoffeeShopService coffeeShopService) {
        this.coffeeShopService = coffeeShopService;
    }

    @GetMapping("/registration")
    public String getRegistration() {
        return "admin/registration";
    }

    @PostMapping("/registration")
    public String adduser(CoffeeShopDTO newCoffeeShop,
                          Model model) {
        try {
            coffeeShopService.addShop(newCoffeeShop);
            model.addAttribute("message", "Кофейня добавлена");
            return "admin/registration";
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
            return "admin/registration";
        }
    }
}
