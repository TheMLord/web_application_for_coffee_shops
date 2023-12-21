package naumen.course.coffeeShop.services;

import naumen.course.coffeeShop.models.ProductType;
import naumen.course.coffeeShop.models.Warehouse;
import naumen.course.coffeeShop.repositories.CoffeeShopRepository;
import naumen.course.coffeeShop.repositories.ProductTypeRepository;
import naumen.course.coffeeShop.repositories.WarehouseRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

@Service
public class WarehouseService {
    private final WarehouseRepository warehouseRepository;
    private final CoffeeShopRepository coffeeShopRepository;
    private final ProductTypeRepository productTypeRepository;



    public WarehouseService(WarehouseRepository productRepository, CoffeeShopRepository coffeeShopRepository, ProductTypeRepository productTypeRepository) {
        this.warehouseRepository = productRepository;
        this.coffeeShopRepository = coffeeShopRepository;
        this.productTypeRepository = productTypeRepository;
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

    public void addWarehouse(Model model, String nameProduct, Integer amountProduct) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            var coffeeShop = coffeeShopRepository.findByLogin(auth.getName());
            var coffeeShopId = coffeeShop.getId();
            Optional<ProductType> productType = productTypeRepository.findProductTypeByNameProduct(nameProduct);
            if (productType.isEmpty()) {
                throw new Exception();
            } else {
                addWarehouse(
                        new Warehouse(coffeeShopId, productType.get().getId(), amountProduct),
                        coffeeShopId
                );
                model.addAttribute("message", "продукт добавлен");
            }
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
        }
    }
}
