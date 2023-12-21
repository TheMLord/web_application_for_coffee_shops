package naumen.course.coffeeShop.controllers.coffeeShop;

import naumen.course.coffeeShop.dto.UpdateUserDTO;
import naumen.course.coffeeShop.models.BonusClient;
import naumen.course.coffeeShop.services.BonusClientService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/coffeeShop")
public class BonusClientController {
    private final BonusClientService bonusClientService;

    public BonusClientController(BonusClientService bonusClientService) {
        this.bonusClientService = bonusClientService;
    }

    @PostMapping("/addBonusClient")
    public void addBonusClient(BonusClient bonusClient) throws Exception {
        bonusClientService.saveUsers(bonusClient);
    }

    @GetMapping("/addBonusClient")
    public String getPageAddProduct() {
        return "addBonusClient";
    }

    @GetMapping("/allBonusClient")
    @ResponseBody
    public List<BonusClient> getAllBonusClient() {
        return bonusClientService.getAllUsers();
    }

    @PostMapping("/updateBonusClient")
    public void updateBonusClient(UpdateUserDTO updateUserDTO) {
        bonusClientService.update(updateUserDTO);
    }

    @GetMapping("/updateBonusClient")
    public String getUpdateBonusClient(UpdateUserDTO updateUserDTO) {
        return "updateBonusClient";
    }

    private BonusClientService getBonusClientService() {
        return bonusClientService;
    }

}
