package de.michiruf.control_server.client_java.config;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.michiruf.control_server.client.config.ClientConfiguration;
import de.michiruf.control_server.client.config.ServerConfiguration;
import de.michiruf.control_server.common.data.MouseData;

/**
 * @author Michael Ruf
 * @since 2016-03-08
 */
public class JavaClientConfiguration implements ClientConfiguration, ServerConfiguration {

    @JsonProperty
    private String host;

    @JsonProperty
    private int port;

    @JsonProperty
    private boolean controllable;

    @JsonIgnore
    private int hostPort;

    @JsonIgnore
    private boolean enabled;

    @JsonProperty
    private MouseData.CoordinateType coordinateType = MouseData.CoordinateType.RELATIVE;

    @Override
    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    @Override
    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public boolean isControllable() {
        return controllable;
    }

    public void setControllable(boolean controllable) {
        this.controllable = controllable;
    }

    @Override
    public int getHostPort() {
        return hostPort;
    }

    public void setHostPort(int hostPort) {
        this.hostPort = hostPort;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public MouseData.CoordinateType getCoordinateType() {
        return coordinateType;
    }

    public void setCoordinateType(MouseData.CoordinateType coordinateType) {
        this.coordinateType = coordinateType;
    }

    @JsonIgnore
    public boolean isProperlyConfigured() {
        return host != null && host.length() > 0 && port != 0 && coordinateType != null;
    }
}
