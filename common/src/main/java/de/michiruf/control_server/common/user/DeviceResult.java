package de.michiruf.control_server.common.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Michael Ruf
 * @since 2018-02-26
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
public class DeviceResult {

    @JsonProperty("devices")
    private List<Device> devices;

    public DeviceResult() {
        devices = new ArrayList<>();
    }

    public DeviceResult(List<Device> devices) {
        this.devices = devices;
    }

    public List<Device> getDevices() {
        return devices;
    }

    public DeviceResult addDevice(Device device) {
        devices.add(device);
        return this;
    }
}
