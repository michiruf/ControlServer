package de.michiruf.control_server.server.web.model;


import de.michiruf.control_server.server.web.helper.IdModel;

import javax.persistence.OneToMany;
import java.util.List;

/**
 * @author Michael Ruf
 * @since 2016-04-07
 */
public class User extends IdModel {

    @OneToMany(mappedBy = "used")
    private List<Device> devices;
}
