package de.michiruf.control_server.common.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.List;

/**
 * @author Michael Ruf
 * @since 2018-02-26
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
public class DeviceResult {

    @JsonProperty("devices")
    List<Device> devices;

    @SuppressWarnings("unused") // for jackson
    public DeviceResult() {
    }

    public DeviceResult(List<Device> devices) {
        this.devices = devices;
    }

    public List<Device> getDevices() {
        return devices;
    }
}
