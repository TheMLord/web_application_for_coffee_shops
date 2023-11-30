package naumen.course.coffeeShop.controllers;

import naumen.course.coffeeShop.services.CoffeeShopService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
public class DeleteShopController {
    private final CoffeeShopService coffeeShopService;

    public DeleteShopController(CoffeeShopService coffeeShopService) {
        this.coffeeShopService = coffeeShopService;
    }

    @GetMapping("/deleteCoffeeShop")
    public String deleteShopPage() {
        return "admin/deleteShop";
    }

    @PostMapping("/deleteCoffeeShop")
    public String deleteShop(@RequestParam String login, Model model) {
        try {
            coffeeShopService.deleteShop(login);
            model.addAttribute("message", "Кофейня удалена");
            return "admin/deleteShop";
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
            return "admin/deleteShop";
        }
    }
}
