package naumen.course.coffeeShop.models;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "coffee_shop")
public class CoffeeShop {
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true, nullable = false)
    private String login;

    @Column(nullable = false)
    private String password;

    @Column(unique = true, nullable = false)
    private String address;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "coffee_shop_role", joinColumns = @JoinColumn(name = "coffe_shops_id"))
    private Set<Role> roles = new HashSet<>();

    public CoffeeShop() {

    }

    public CoffeeShop(String login, String password, String address) {
        this.login = login;
        this.password = password;
        this.roles.add(Role.USER);
        this.address = address;
    }

    public CoffeeShop(Long id, String login, String password, String address, Role role) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.address = address;
        this.roles.add(role);
    }

    public CoffeeShop(Long id, String login, String password, String address, Set<Role> roles) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.address = address;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getAddress() {
        return address;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
