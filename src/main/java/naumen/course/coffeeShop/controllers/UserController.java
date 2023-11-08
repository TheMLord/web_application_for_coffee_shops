package naumen.course.coffeeShop.controllers;

import naumen.course.coffeeShop.dto.UpdateUserDTO;
import naumen.course.coffeeShop.models.User;
import naumen.course.coffeeShop.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/addUser")
    public void addUser(@RequestBody User user) throws Exception {
        userService.saveUsers(user);
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
