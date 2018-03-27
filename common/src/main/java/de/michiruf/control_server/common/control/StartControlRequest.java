package de.michiruf.control_server.common.control;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import de.michiruf.control_server.common.user.Device;

/**
 * @author Michael Ruf
 * @since 2018-02-27
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
public class StartControlRequest {

    @JsonProperty("device")
    private Device device;

    protected StartControlRequest() {
    }

    public StartControlRequest(Device device) {
        this.device = device;
    }

    public Device getDevice() {
        return device;
    }
}
