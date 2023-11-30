package naumen.course.coffeeShop.controllers;

import naumen.course.coffeeShop.services.CoffeeShopService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;

@Controller
public class HomePageController {
    private final CoffeeShopService coffeeShopService;

    public HomePageController(CoffeeShopService coffeeShopService) {
        this.coffeeShopService = coffeeShopService;
    }

    @GetMapping("")
    public String homePage(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();

        return (authorities.stream().anyMatch(role -> role.toString().equals("ROLE_ADMIN")))
                ? "admin/adminHome" : "coffeeShopHome";
    }
}
