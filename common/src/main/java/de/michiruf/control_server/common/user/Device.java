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

    @JsonProperty("isApproved")
    private boolean isApproved;

    @SuppressWarnings("unused") // for jackson
    protected Device() {
    }

    public Device(String id, String name, boolean isApproved) {
        this.id = id;
        this.name = name;
        this.isApproved = isApproved;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isApproved() {
        return isApproved;
    }
}
