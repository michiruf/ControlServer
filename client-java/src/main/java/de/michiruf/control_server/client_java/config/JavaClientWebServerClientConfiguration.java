package de.michiruf.control_server.client_java.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.michiruf.control_server.client.config.WebServerClientConfiguration;

/**
 * @author Michael Ruf
 * @since 2018-02-25
 */
public class JavaClientWebServerClientConfiguration implements WebServerClientConfiguration {

    @JsonProperty
    private String host;
    @JsonProperty
    private int port;
    @JsonProperty
    private boolean sendControlsEnabled;
    @JsonProperty
    private boolean controlListeningEnabled;

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
}
