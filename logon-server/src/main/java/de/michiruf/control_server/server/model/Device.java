package de.michiruf.control_server.server.model;

import de.michiruf.control_server.server.helper.IdModel;

import javax.persistence.ManyToOne;

/**
 * @author Michael Ruf
 * @since 2016-04-07
 */
public class Device extends IdModel {

    @ManyToOne
    private User user;
}
