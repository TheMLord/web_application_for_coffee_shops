package naumen.course.coffeeShop.controllers;

import naumen.course.coffeeShop.services.CoffeeShopService;
import naumen.course.coffeeShop.services.OrderService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OrderController {
    private final OrderService orderService;
    private final CoffeeShopService coffeeShopService;

    public OrderController(OrderService orderService, CoffeeShopService coffeeShopService) {
        this.orderService = orderService;
        this.coffeeShopService = coffeeShopService;
    }

    @PostMapping("/getOrder")
    public String getOrder(String text, @RequestParam(defaultValue = "") String number, Model model) {
        try {
            long price;
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            var coffeeShop = coffeeShopService.getCoffeeShop(auth.getName());
            if (number == "") {
                price = orderService.transferProducts(text, coffeeShop.getId());
            } else {
                price = orderService.transferProducts(text, coffeeShop.getId(), number);
            }
            model.addAttribute("message", "Оплата прошла успешно. Итоговая ценна: " + price);
            return "addOrder";
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
            return "addOrder";
        }
    }

    @GetMapping("/getOrder")
    public String getOrder() {
        return "addOrder";
    }

    private OrderService getOrderService() {
        return orderService;
    }
}
