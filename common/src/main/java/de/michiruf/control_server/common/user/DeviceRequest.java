package de.michiruf.control_server.common.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * @author Michael Ruf
 * @since 2018-02-26
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
public class DeviceRequest {

    @JsonProperty("device")
    private Device ownDevice;

    @SuppressWarnings("unused") // for jackson
    protected DeviceRequest() {
    }

    public DeviceRequest(Device ownDevice) {
        this.ownDevice = ownDevice;
    }

    public Device getOwnDevice() {
        return ownDevice;
    }
}
