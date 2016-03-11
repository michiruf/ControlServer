package models;

import play.db.jpa.GenericModel;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

/**
 * @author Michael Ruf
 * @since 2016-03-09
 */
@Entity
public class Device extends GenericModel {

    @Id
    private String id;

    @ManyToOne
    private User user;

    private String name;

    private Date lastActive;

    public Device(String id, User user, String name) {
        this.id = id;
        this.user = user;
        this.name = name;
        lastActive = new Date(System.currentTimeMillis());
    }

    public String getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getLastActive() {
        return lastActive;
    }
}
