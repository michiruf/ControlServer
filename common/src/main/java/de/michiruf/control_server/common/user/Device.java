package de.michiruf.control_server.common.user;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Michael Ruf
 * @since 2018-03-21
 */
public class Device {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @SuppressWarnings("unused") // for jackson
    public Device() {
    }

    public Device(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
