package naumen.course.coffeeShop.models;

import jakarta.persistence.*;

@Entity
@Table(name = "coffee_shop_products_type")
public class ProductType {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true, nullable = false)
    private String nameProduct;

    @Column(nullable = false)
    private int cost;

    public ProductType() {

    }

    public ProductType(String nameProduct, int cost) {
        this.nameProduct = nameProduct;
        this.cost = cost;
    }

    public Long getId() {
        return id;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public int getCost() {
        return cost;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
