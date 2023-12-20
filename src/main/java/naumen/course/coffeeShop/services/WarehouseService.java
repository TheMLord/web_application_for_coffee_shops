package naumen.course.coffeeShop.services;

import naumen.course.coffeeShop.models.Warehouse;
import naumen.course.coffeeShop.repositories.WarehouseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class WarehouseService {
    private final WarehouseRepository warehouseRepository;

    public WarehouseService(WarehouseRepository productRepository) {
        this.warehouseRepository = productRepository;
    }

    @Transactional
    public void addWarehouse(Warehouse newWarehouse, Long coffeeShopId) {
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findFirstByIdShopAndProductTypeId(coffeeShopId, newWarehouse.getProductTypeId());
        if (optionalWarehouse.isPresent()) {
            var warehouse = optionalWarehouse.get();
            var newAmount = warehouse.getAmount() + newWarehouse.getAmount();

            warehouseRepository.save(new Warehouse(coffeeShopId, warehouse.getProductTypeId(), newAmount));
        } else {
            warehouseRepository.save(newWarehouse);
        }
    }

    public List<Warehouse> getAllProducts() {
        return (List<Warehouse>) warehouseRepository.findAll();
    }
}
