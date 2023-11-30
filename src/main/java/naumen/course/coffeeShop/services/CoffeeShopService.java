package naumen.course.coffeeShop.services;

import jakarta.transaction.Transactional;
import naumen.course.coffeeShop.models.CoffeeShop;
import naumen.course.coffeeShop.models.Role;
import naumen.course.coffeeShop.repositories.CoffeeShopRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CoffeeShopService implements UserDetailsService {
    private final CoffeeShopRepository coffeeShopRepository;
    private final PasswordEncoder passwordEncoder;

    public CoffeeShopService(CoffeeShopRepository coffeeShopRepository, PasswordEncoder passwordEncoder) {
        this.coffeeShopRepository = coffeeShopRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String coffeeShopName) throws UsernameNotFoundException {
        var coffeeShop = coffeeShopRepository.findByLogin(coffeeShopName);
        return new org.springframework.security.core.userdetails.User(
                coffeeShop.getLogin(),
                coffeeShop.getPassword(),
                mapRolesToAthorities(coffeeShop.getRoles()));
    }

    private List<? extends GrantedAuthority> mapRolesToAthorities(Set<Role> roles) {
        return roles.stream().map(r -> new SimpleGrantedAuthority(
                "ROLE_" + r.name())).collect(Collectors.toList()
        );
    }

    public CoffeeShop getCoffeeShop(String login) {
        return coffeeShopRepository.findByLogin(login);
    }

    @Transactional
    public void addShop(CoffeeShop coffeeShop) throws Exception {
        var coffeeShopFromDb = coffeeShopRepository.findByLogin(coffeeShop.getLogin());
        if (coffeeShopFromDb != null) {
            throw new Exception("user exist");
        }
        coffeeShop.setPassword(passwordEncoder.encode(coffeeShop.getPassword()));

        coffeeShop.setRoles(Collections.singleton(Role.USER));
        coffeeShopRepository.save(coffeeShop);
    }

    @Transactional
    public void deleteShop(String login) throws Exception {
        var coffeeShopFromDb = coffeeShopRepository.findByLogin(login);
        if (coffeeShopFromDb == null) {
            throw new Exception("нет такой кофейни, удалить невозможно");
        }
        coffeeShopRepository.deleteByLogin(login);
    }
}