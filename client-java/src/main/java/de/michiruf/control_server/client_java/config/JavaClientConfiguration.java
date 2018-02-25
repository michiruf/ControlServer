package de.michiruf.control_server.client_java.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.michiruf.control_server.client.config.WebServerClientConfiguration;
import de.michiruf.control_server.client.config.DirectConnectionServerConfiguration;

/**
 * @author Michael Ruf
 * @since 2016-03-08
 */
public class JavaClientConfiguration implements WebServerClientConfiguration, DirectConnectionServerConfiguration {

    @JsonProperty
    private String host;

    @JsonProperty
    private int port;

    @JsonProperty
    private boolean sendControlsEnabled;

    // Initially the computer shell be controllable in general
    @JsonProperty
    private boolean controlListeningEnabled = true;

    @JsonProperty
    private int hostPort;

    @JsonProperty
    private boolean autoStartEnabled;

    @JsonProperty
    private String hostPassword;

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
    public boolean isAutoStartEnabled() {
        return autoStartEnabled;
    }

    public void setAutoStartEnabled(boolean autoStartEnabled) {
        this.autoStartEnabled = autoStartEnabled;
    }

    @Override
    public int getHostPort() {
        return hostPort;
    }

    public void setHostPort(int hostPort) {
        this.hostPort = hostPort;
    }

    @Override
    public String getHostPassword() {
        return hostPassword;
    }

    public void getHostPassword(String hostPassword) {
        this.hostPassword = hostPassword;
    }
}
