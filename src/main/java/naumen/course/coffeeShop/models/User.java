package naumen.course.coffeeShop.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "users")
public class User {

    private String name;
    @Id
    private String phoneNumber;
    private String mail;

    private Long scores;

    public User() {

    }

    public User(String name, String phoneNumber, String mail, Long scores) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.mail = mail;
        this.scores = scores;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Long getScores() {
        return scores;
    }

    public void setScores(Long scores) {
        this.scores = scores;
    }
}
