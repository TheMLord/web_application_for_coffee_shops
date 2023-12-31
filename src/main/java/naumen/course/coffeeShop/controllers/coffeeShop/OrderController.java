package naumen.course.coffeeShop.controllers.coffeeShop;

import naumen.course.coffeeShop.services.CoffeeShopService;
import naumen.course.coffeeShop.services.OrderService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/coffeeShop")
public class OrderController {
    private final OrderService orderService;
    private final CoffeeShopService coffeeShopService;

    public OrderController(OrderService orderService, CoffeeShopService coffeeShopService) {
        this.orderService = orderService;
        this.coffeeShopService = coffeeShopService;
    }

    @PostMapping("/getOrder")
    public String getOrder(@RequestParam(value = "text") String text, @RequestParam(required = false) String number,
                           @RequestParam(required = false) String bonus,
                           Model model) {
        try {
            boolean flag = true;
            if (bonus == null) {
                flag = false;
            }
            long price;
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            var coffeeShop = coffeeShopService.getCoffeeShop(auth.getName());
            if (number == "") {
                price = orderService.transferProducts(text, coffeeShop.getId());
            } else {
                price = orderService.transferProducts(text, coffeeShop.getId(), number, flag);
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
