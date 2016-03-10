package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Date;

/**
 * @author Michael Ruf
 * @since 2016-03-09
 */
@Entity
public class Device extends Model {

    @ManyToOne
    private User user;

    private String name;

    private String accessToken;

    private Date lastActive;

    public Device(User user, String name, String accessToken) {
        this.user = user;
        this.name = name;
        this.accessToken = accessToken;
        lastActive = new Date(System.currentTimeMillis());
    }

    public User getUser() {
        return user;
    }

    public String getName() {
        return name;
    }

    public boolean proveAccessToken(String accessToken) {
        return this.accessToken.equals(accessToken);
    }

    public Date getLastActive() {
        return lastActive;
    }
}
