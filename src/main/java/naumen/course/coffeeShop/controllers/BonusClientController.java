package naumen.course.coffeeShop.controllers;

import naumen.course.coffeeShop.dto.UpdateUserDTO;
import naumen.course.coffeeShop.models.BonusClient;
import naumen.course.coffeeShop.services.BonusClientService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BonusClientController {
    private final BonusClientService bonusClientService;

    public BonusClientController(BonusClientService bonusClientService) {
        this.bonusClientService = bonusClientService;
    }

    @PostMapping("/addUser")
    public void addUser(BonusClient bonusClient) throws Exception {
        bonusClientService.saveUsers(bonusClient);
    }

    @GetMapping("/addUser")
    public String getPageAddProduct() {
        return "addUser";
    }

    @GetMapping("/allUser")
    @ResponseBody
    public List<BonusClient> getAllBook() {
        return bonusClientService.getAllUsers();
    }

    @PostMapping("/updateUser")
    public void updateUser(UpdateUserDTO updateUserDTO) {
        bonusClientService.update(updateUserDTO);
    }
    @GetMapping("/updateUser")
    public String getUpdateUser(UpdateUserDTO updateUserDTO) {
        return "updateUser";
    }

    private BonusClientService getUserService() {
        return bonusClientService;
    }

}
