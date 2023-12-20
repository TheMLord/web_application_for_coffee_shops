package naumen.course.coffeeShop.services;

import naumen.course.coffeeShop.dto.UpdateUserDTO;
import naumen.course.coffeeShop.models.BonusClient;
import naumen.course.coffeeShop.repositories.BonusClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BonusClientService {

    private BonusClientRepository bonusClientRepository;

    public BonusClientService(BonusClientRepository bonusClientRepository) {
        this.bonusClientRepository = bonusClientRepository;
    }

    public List<BonusClient> getAllUsers() {
        return bonusClientRepository.findAll();
    }

    public void saveUsers(BonusClient bonusClient) throws Exception {
        BonusClient bonusClientFromDb = bonusClientRepository.findByPhoneNumber(bonusClient.getPhoneNumber());
        if (bonusClientFromDb != null) {
            throw new Exception("user exist");
        }
        bonusClientRepository.save(bonusClient);
    }

    public void update(UpdateUserDTO updateUserDTO) {
        bonusClientRepository.save(new BonusClient(updateUserDTO.name(), updateUserDTO.phoneNumber(), updateUserDTO.mail(), updateUserDTO.scores()));
    }
}
