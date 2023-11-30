package naumen.course.coffeeShop.controllers;

import naumen.course.coffeeShop.models.CoffeeShop;
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
        return "registration";
    }

    @PostMapping("/registration")
    public String adduser(CoffeeShop coffeeShop,
                          Model model) {
        try {
            coffeeShopService.addShop(coffeeShop);
            model.addAttribute("message", "Кофейня добавлена");
            return "admin/registration";
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
            return "admin/registration";
        }
    }
}
