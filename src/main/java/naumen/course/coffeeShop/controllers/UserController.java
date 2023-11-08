package naumen.course.coffeeShop.controllers;

import naumen.course.coffeeShop.dto.UpdateUserDTO;
import naumen.course.coffeeShop.models.User;
import naumen.course.coffeeShop.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/addUser")
    public void addUser(@RequestParam("name") String name, @RequestParam("phoneNumber") String phoneNumber,
                        @RequestParam(value = "mail", required = false) String mail, @RequestParam("scores") Long scores) throws Exception {
        userService.saveUsers(new User(name, phoneNumber, mail, scores));
    }
    @GetMapping("/addUser")
    public String getPageAddProduct() {
        return "addUser";
    }

    @GetMapping("/allUser")
    @ResponseBody
    public List<User> getAllBook() {
        return userService.getAllUsers();
    }

    @PostMapping("/updateUser")
    public void updateUser(UpdateUserDTO updateUserDTO) {
        userService.update(updateUserDTO);
    }

    private UserService getUserService() {
        return userService;
    }

}
