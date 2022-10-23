package inz.inzynierka.praca.entites;

import org.springframework.boot.CommandLineRunner;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;


@Entity
@Table(name = "users")
public class UserEntity{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long account_id;
    private String email;
    private String password;
    private String name;
    private String lastname;

    public UserEntity(Long account_id, String email, String password, String name, String lastname) {
        this.account_id = account_id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.lastname = lastname;
    }

    public UserEntity() {

    }


    @java.lang.Override
    public java.lang.String toString() {
        return "UserEntity{" +
                "account_id=" + account_id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }
    public Long getAccount_id() {
        return account_id;
    }

    public void setAccount_id(Long account_id) {
        this.account_id = account_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

}
