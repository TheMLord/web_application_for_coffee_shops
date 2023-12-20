package naumen.course.coffeeShop.repositories;

import naumen.course.coffeeShop.models.BonusClient;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BonusClientRepository extends CrudRepository<BonusClient, String> {
    BonusClient findByName(String Username);

    BonusClient findByPhoneNumber(String phoneNumber);

    List<BonusClient> findAll();

}
