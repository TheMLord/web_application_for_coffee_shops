package naumen.course.coffeeShop.services;

import naumen.course.coffeeShop.dto.ProductTypeDto;
import naumen.course.coffeeShop.models.ProductType;
import naumen.course.coffeeShop.repositories.ProductTypeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductTypeService {
    private final ProductTypeRepository productTypeRepository;

    public ProductTypeService(ProductTypeRepository productTypeRepository) {
        this.productTypeRepository = productTypeRepository;
    }

    @Transactional
    public void addProductType(ProductTypeDto productTypeDto) throws Exception {
        var dbProductType = productTypeRepository.findProductTypeByNameProduct(productTypeDto.nameProduct());

        if (dbProductType.isPresent()) {
            throw new Exception("Такой тип товара уже существует");
        }
        var productType = new ProductType(productTypeDto.nameProduct(), productTypeDto.cost());
        productTypeRepository.save(productType);
    }

    @Transactional
    public void deleteProductType(String nameProduct) throws Exception {
        var dbProductType = productTypeRepository.findProductTypeByNameProduct(nameProduct);

        if (dbProductType.isEmpty()) {
            throw new Exception("Такого типа продукта не существует");
        }
        productTypeRepository.deleteProductTypeByNameProduct(nameProduct);
    }

    public List<ProductType> getProductTypeList(){
        return productTypeRepository.findAll();
    }
}
