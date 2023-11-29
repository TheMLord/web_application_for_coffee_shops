package naumen.course.coffeeShop.controllers;

import naumen.course.coffeeShop.models.CoffeeShop;
import naumen.course.coffeeShop.models.Role;
import naumen.course.coffeeShop.services.CoffeeShopService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
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
    public String adduser(@RequestParam String login,
                          @RequestParam String password,
                          @RequestParam String address,
                          Model model) {

        try {
            coffeeShopService.addShop(new CoffeeShop(login, password, address, Role.USER));
            model.addAttribute("message", "Кофейня добавлена");
            return "registration";
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
            return "registration";
        }
    }
}
