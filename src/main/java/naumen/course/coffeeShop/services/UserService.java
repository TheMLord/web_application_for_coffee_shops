package naumen.course.coffeeShop.services;

import naumen.course.coffeeShop.dto.UpdateUserDTO;
import naumen.course.coffeeShop.models.User;
import naumen.course.coffeeShop.repositories.UserRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    public void saveUsers(User user) throws Exception {
        User userFromDb = userRepository.findByPhoneNumber(user.getPhoneNumber());
        if (userFromDb != null) {
            throw new Exception("user exist");
        }
        userRepository.save(user);
    }
    public void update(UpdateUserDTO updateUserDTO) {
        System.out.println(updateUserDTO);
        if (updateUserDTO.mail() != null)
            userRepository.update(updateUserDTO.phoneNumber(), updateUserDTO.name(), updateUserDTO.mail(), updateUserDTO.scores());
        else
            userRepository.updateWithoutMail(updateUserDTO.phoneNumber(), updateUserDTO.name(), updateUserDTO.scores());
    }
}
