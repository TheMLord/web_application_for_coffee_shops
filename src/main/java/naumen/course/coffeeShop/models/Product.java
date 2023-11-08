package naumen.course.coffeeShop.models;

import jakarta.persistence.*;

@Entity
@Table(name = "coffee_shop_products")
public class Product {
    @Id
    @JoinColumn(table = "coffee_shop", referencedColumnName = "id")
    private Long idShop;

    @Enumerated(EnumType.STRING)
    private ProductType productType;

    @Column(nullable = false)
    private Integer cost;

    @Column(nullable = false)
    private Integer amount;

    public Product() {

    }

    public Product(Long idShop, ProductType productType, Integer amount) {
        this.idShop = idShop;
        this.productType = productType;
        this.cost = this.productType.getCost();
        this.amount = amount;
    }

    public Long getIdShop() {
        return idShop;
    }

    public ProductType getProductType() {
        return productType;
    }

    public Integer getCost() {
        return cost;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}

