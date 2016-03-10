package models;

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

    @OneToMany(mappedBy = "", cascade = CascadeType.REMOVE)
    private List<Device> devices;
}
