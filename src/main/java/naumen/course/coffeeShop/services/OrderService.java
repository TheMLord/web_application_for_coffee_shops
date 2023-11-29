package naumen.course.coffeeShop.services;

import naumen.course.coffeeShop.models.BonusClient;
import naumen.course.coffeeShop.models.Product;
import naumen.course.coffeeShop.models.ProductType;
import naumen.course.coffeeShop.repositories.BonusClientRepository;
import naumen.course.coffeeShop.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final ProductRepository productRepository;
    private final BonusClientRepository bonusClientRepository;

    public OrderService(ProductRepository productRepository, BonusClientRepository bonusClientRepository) {
        this.productRepository = productRepository;
        this.bonusClientRepository = bonusClientRepository;
    }

    public List<Product> getAllProducts() {
        return (List<Product>) productRepository.findAll();
    }

    @Transactional
    public long transferProducts(String text, Long coffeeShopId) throws Exception {
        var productList = getOrder(text);
        for (var product : productList.entrySet()) {
            var optionalProduct = productRepository.findByIdShopAndProductType(coffeeShopId, ProductType.valueOf(product.getKey()));
            if (optionalProduct.isPresent()) {
                var productDB = optionalProduct.get();
                int newAmount = productDB.getAmount() - product.getValue();
                if (newAmount < 0) {
                    throw new Exception("Товара не хватает на складе");
                }
                productRepository.updateAmountByIdShopAndProductType(coffeeShopId, productDB.getProductType(), newAmount);
                // bonusClientRepository.save(new BonusClient());
                return newAmount * productDB.getCost();
            } else {
                throw new Exception("Товара нет на складе");
            }
        }
        return 0l;
    }

    @Transactional
    public long transferProducts(String text, Long coffeeShopId, String number) throws Exception {
        var productList = getOrder(text);
        for (var product : productList.entrySet()) {
            var optionalProduct = productRepository.findByIdShopAndProductType(coffeeShopId, ProductType.valueOf(product.getKey()));
            if (optionalProduct.isPresent()) {
                var productDB = optionalProduct.get();
                int newAmount = productDB.getAmount() - product.getValue();
                if (newAmount < 0) {
                    throw new Exception("Товара не хватает на складе");
                }
                int price = newAmount * productDB.getCost();
                var bonusClient = bonusClientRepository.findByPhoneNumber(number);
//                bonusClientRepository.save(new BonusClient(bonusClient.getName(), bonusClient.getPhoneNumber(),
//                        bonusClient.getMail(), bonusClient.getScores()));
                return price - bonusClient.getScores();
            } else {
                throw new Exception("Товара нет на складе");
            }
        }
        return coffeeShopId;
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
