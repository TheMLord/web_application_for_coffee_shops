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

                return product.getValue() * optionalProductType.get().getCost();
            } else {
                throw new Exception("Товара нет на складе");
            }
        }
        return 0l;
    }

    //Списывает или зачисляет баллы
    @Transactional
    public long transferProducts(String text, Long coffeeShopId, String number, boolean flag) throws Exception {
        var productList = getOrder(text);
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
                int price = product.getValue() * optionalProductType.get().getCost();
                var bonusClient = bonusClientRepository.findByPhoneNumber(number);

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
                            bonusClient.getMail(), bonusClient.getScores() + price / 100));
                }
                productRepository.save(new Warehouse(coffeeShopId, productDB.getProductTypeId(), newAmount));
                return price;
            } else {
                throw new Exception("Товара нет на складе");
            }
        }
        return 0l;
    }

//    @Transactional
//    public long transferProducts(String text, Long coffeeShopId, String number) throws Exception {
//        var productList = getOrder(text);
//        for (var product : productList.entrySet()) {
//            var optionalProduct = productRepository.findByIdShopAndProductType(coffeeShopId, ProductType.valueOf(product.getKey()));
//            if (optionalProduct.isPresent()) {
//                var productDB = optionalProduct.get();
//                int newAmount = productDB.getAmount() - product.getValue();
//                if (newAmount < 0) {
//                    throw new Exception("Товара не хватает на складе");
//                }
//                int price = newAmount * productDB.getCost();
//                var bonusClient = bonusClientRepository.findByPhoneNumber(number);
////                bonusClientRepository.save(new BonusClient(bonusClient.getName(), bonusClient.getPhoneNumber(),
////                        bonusClient.getMail(), bonusClient.getScores()));
//                return price - bonusClient.getScores();
//            } else {
//                throw new Exception("Товара нет на складе");
//            }
//        }
//        return coffeeShopId;
//    }

    public static Map<String, Integer> getOrder(String text) {
        return Arrays.stream(text.split(" "))
                .map(entry -> entry.split(":"))
                .collect(Collectors.toMap(
                        pair -> pair[0],
                        pair -> Integer.parseInt(pair[1])
                ));
    }
}
