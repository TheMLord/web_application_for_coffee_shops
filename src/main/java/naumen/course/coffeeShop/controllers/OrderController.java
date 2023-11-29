package naumen.course.coffeeShop.controllers;

import naumen.course.coffeeShop.services.CoffeeShopService;
import naumen.course.coffeeShop.services.OrderService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class OrderController {
    private final OrderService orderService;
    private final CoffeeShopService coffeeShopService;

    public OrderController(OrderService orderService, CoffeeShopService coffeeShopService) {
        this.orderService = orderService;
        this.coffeeShopService = coffeeShopService;
    }

    @PostMapping("/getOrder")
    public String getOrder(String text) throws Exception {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        var coffeeShop = coffeeShopService.getCoffeeShop(auth.getName());
        return orderService.transferProducts(text, coffeeShop.getId());
    }
    @GetMapping("/getOrder")
    public String getOrder() {
        return "addOrder";
    }

    private OrderService getOrderService() {
        return orderService;
    }
}
