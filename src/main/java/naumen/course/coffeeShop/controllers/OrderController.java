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
    public String getOrder(String text, String number, Model model) {
        //@RequestParam("pay") String action, @RequestParam("bonus") String action2
        //        try {
//            long price;
//            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//            var coffeeShop = coffeeShopService.getCoffeeShop(auth.getName());
//
//            if("")
//        } catch (Exception e) {
//            model.addAttribute("message", e.getMessage());
//            return "addOrder";
//        }
        try {
            long price;
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            var coffeeShop = coffeeShopService.getCoffeeShop(auth.getName());
            if (number == "") {
                price = orderService.transferProducts(text, coffeeShop.getId());
            } else {
                price = orderService.transferProducts(text, coffeeShop.getId(), number, false);
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
