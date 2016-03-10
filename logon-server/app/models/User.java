package models;

import helper.HashHelper;
import play.db.jpa.Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * @author Michael Ruf
 * @since 2016-03-09
 */
@Entity
public class User extends Model {

    private String username;

    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Device> devices;

    public User(String username, String plainPassword) {
        this.username = username;
        this.password = HashHelper.sha(plainPassword);
    }

    public String getUsername() {
        return username;
    }

    public boolean provePassword(String plainPassword) {
        return password == HashHelper.sha(plainPassword);
    }

    public List<Device> getDevices() {
        return devices;
    }
}
