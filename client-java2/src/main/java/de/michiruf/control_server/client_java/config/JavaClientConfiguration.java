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
    private boolean sendControlsEnabled;

    @JsonProperty
    private boolean controlListeningEnabled;

    @JsonProperty
    private int hostPort;

    @JsonProperty
    private boolean autoStartEnabled;

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
    public boolean isSendControlsEnabled() {
        return sendControlsEnabled;
    }

    public void setSendControlsEnabled(boolean sendControlsEnabled) {
        this.sendControlsEnabled = sendControlsEnabled;
    }

    @Override
    public boolean isControlListeningEnabled() {
        return controlListeningEnabled;
    }

    public void setControlListeningEnabled(boolean controlListeningEnabled) {
        this.controlListeningEnabled = controlListeningEnabled;
    }

    @Override
    public int getHostPort() {
        return hostPort;
    }

    public void setHostPort(int hostPort) {
        this.hostPort = hostPort;
    }

    @Override
    public boolean isAutoStartEnabled() {
        return autoStartEnabled;
    }

    public void setAutoStartEnabled(boolean autoStartEnabled) {
        this.autoStartEnabled = autoStartEnabled;
    }

    public MouseData.CoordinateType getCoordinateType() {
        return coordinateType;
    }

    public void setCoordinateType(MouseData.CoordinateType coordinateType) {
        this.coordinateType = coordinateType;
    }

    @JsonIgnore
    public boolean isProperlyConfigured() {
        // TODO!!! (do we still need this?)
        return host != null && host.length() > 0 && port != 0 && coordinateType != null;
    }
}
