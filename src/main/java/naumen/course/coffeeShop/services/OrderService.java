package naumen.course.coffeeShop.services;

import naumen.course.coffeeShop.models.BonusClient;
import naumen.course.coffeeShop.models.ProductType;
import naumen.course.coffeeShop.models.Warehouse;
import naumen.course.coffeeShop.repositories.BonusClientRepository;
import naumen.course.coffeeShop.repositories.ProductTypeRepository;
import naumen.course.coffeeShop.repositories.WarehouseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final WarehouseRepository productRepository;
    private final BonusClientRepository bonusClientRepository;
    private final ProductTypeRepository productTypeRepository;

    public OrderService(WarehouseRepository productRepository, BonusClientRepository bonusClientRepository, ProductTypeRepository productTypeRepository) {
        this.productRepository = productRepository;
        this.bonusClientRepository = bonusClientRepository;
        this.productTypeRepository = productTypeRepository;
    }

    public List<Warehouse> getAllProducts() {
        return (List<Warehouse>) productRepository.findAll();
    }


    // Просто списывает товар со склада
    @Transactional
    public long transferProducts(String text, Long coffeeShopId) throws Exception {
        var productList = getOrder(text);
        long price = 0;
        for (var product : productList.entrySet()) {
            Optional<ProductType> optionalProductType = productTypeRepository.findProductTypeByNameProduct(product.getKey());
            if (optionalProductType.isEmpty()) {
                throw new Exception("Такого продукта нет на складе");
            }
            var optionalProduct = productRepository.findFirstByIdShopAndProductTypeId(coffeeShopId, optionalProductType.get().getId());
            if (optionalProduct.isPresent()) {
                Warehouse productDB = optionalProduct.get();
                int newAmount = productDB.getAmount() - product.getValue();
                if (newAmount < 0) {
                    throw new Exception("Товара не хватает на складе");
                }
                productRepository.save(new Warehouse(coffeeShopId, productDB.getProductTypeId(), newAmount));
                price += product.getValue() * optionalProductType.get().getCost();
            } else {
                throw new Exception("Товара нет на складе");
            }
        }
        return price;
    }

    //Списывает или зачисляет баллы
    @Transactional
    public long transferProducts(String text, Long coffeeShopId, String number, boolean flag) throws Exception {
        var productList = getOrder(text);
        var bonusClient = bonusClientRepository.findByPhoneNumber(number);
        long price = 0l;

        for (var product : productList.entrySet()) {

            Optional<ProductType> optionalProductType = productTypeRepository.findProductTypeByNameProduct(product.getKey());

            if (optionalProductType.isEmpty()) {
                throw new Exception("Такого продукта нет на складе");
            }

            var optionalProduct = productRepository.findFirstByIdShopAndProductTypeId(coffeeShopId, optionalProductType.get().getId());
            if (optionalProduct.isPresent()) {
                var productDB = optionalProduct.get();
                int newAmount = productDB.getAmount() - product.getValue();
                if (newAmount < 0) {
                    throw new Exception("Товара не хватает на складе");
                }
                price += product.getValue() * optionalProductType.get().getCost();
                productRepository.save(new Warehouse(coffeeShopId, productDB.getProductTypeId(), newAmount));
            } else {
                throw new Exception("Товара нет на складе");
            }
        }
        if (flag) {
            long scores = 0;
            long bonusClientScores = bonusClient.getScores();
            if (price > bonusClientScores) {
                price -= bonusClientScores;
                bonusClientRepository.save(new BonusClient(bonusClient.getName(), bonusClient.getPhoneNumber(),
                        bonusClient.getMail(), scores));
            } else {
                scores = bonusClientScores - price;
                price = 0;
                bonusClientRepository.save(new BonusClient(bonusClient.getName(), bonusClient.getPhoneNumber(),
                        bonusClient.getMail(), scores));
            }
        } else {
            bonusClientRepository.save(new BonusClient(bonusClient.getName(), bonusClient.getPhoneNumber(),
                    bonusClient.getMail(), bonusClient.getScores() + price / 10));
        }

        return price;
    }

    public static Map<String, Integer> getOrder(String text) {
        return Arrays.stream(text.split(" "))
                .map(entry -> entry.split(":"))
                .collect(Collectors.toMap(
                        pair -> pair[0],
                        pair -> Integer.parseInt(pair[1])
                ));
    }
}
