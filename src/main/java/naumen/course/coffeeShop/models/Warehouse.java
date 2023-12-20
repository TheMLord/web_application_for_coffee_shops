package naumen.course.coffeeShop.models;

import jakarta.persistence.*;

@Entity
@Table(name = "coffee_shop_products")
public class Warehouse {
    @Id
    @JoinColumn(table = "coffee_shop", referencedColumnName = "id")
    private Long idShop;

    @JoinColumn(table = "coffee_shop_products_type", referencedColumnName = "id")
    private Long productTypeId;

    @Column(nullable = false)
    private Integer cost;

    @Column(nullable = false)
    private Integer amount;

    public Warehouse() {

    }
    public Warehouse(Long idShop, Long productTypeId, Integer amount) {
        this.idShop = idShop;
        this.productTypeId = productTypeId;
        this.amount = amount;
    }

    public Long getIdShop() {
        return idShop;
    }

    public Long getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(Long productTypeId) {
        this.productTypeId = productTypeId;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}

