package models;

import io.ebean.Finder;
import io.ebean.Model;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Michael Ruf
 * @since 2018-03-06
 */
@Entity
public class User extends Model {

    public static final Finder<Long, User> finder = new Finder<>(User.class);

    @Id
    public Long id;

    public String username;

    public String email;

    public String password;

    public Date registerDate;

    @OneToMany
    public List<Device> devices;

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
        registerDate = Date.from(Instant.now());
        devices = new ArrayList<>();
    }
}