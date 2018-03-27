package models;

import io.ebean.Finder;
import io.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * @author Michael Ruf
 * @since 2018-03-23
 */
@Entity
public class Device extends Model {

    public static final Finder<Long, Device> finder = new Finder<>(Device.class);

    @Id
    public Long id;

    @ManyToOne
    public User user;

    public String name;

    public Device(User user, String name) {
        this.user = user;
        this.name = name;
    }

    public de.michiruf.control_server.common.user.Device toCommon() {
        return new de.michiruf.control_server.common.user.Device(Long.toString(id), name);
    }
}
