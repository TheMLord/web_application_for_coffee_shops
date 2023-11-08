package naumen.course.coffeeShop.services;

import naumen.course.coffeeShop.models.User;
import naumen.course.coffeeShop.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;

    private List<User> users = new ArrayList<>();

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return users;
    }

    public void saveUsers(String name, String phoneNumber, String mail, Long scores) throws Exception {
        User userFromDb = userRepository.findByPhoneNumber(phoneNumber);
        if (userFromDb != null)
        {
            throw new Exception("user exist");
        }
        users.add(new User(name, phoneNumber, mail, scores));
    }

    public void saveUsers(String name, String phoneNumber, Long scores) throws Exception {
        User userFromDb = userRepository.findByPhoneNumber(phoneNumber);
        if (userFromDb != null)
        {
            throw new Exception("user exist");
        }
        users.add(new User(name, phoneNumber, scores));
    }

    public void saveUsers(User user) throws Exception {
        User userFromDb = userRepository.findByPhoneNumber(user.getPhoneNumber());
        if (userFromDb != null)
        {
            throw new Exception("user exist");
        }
        users.add(user);
    }
}
