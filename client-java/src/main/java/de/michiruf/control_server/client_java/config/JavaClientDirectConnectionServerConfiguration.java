package de.michiruf.control_server.client_java.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.michiruf.control_server.client.config.DirectConnectionServerConfiguration;

/**
 * @author Michael Ruf
 * @since 2018-02-25
 */
public class JavaClientDirectConnectionServerConfiguration implements DirectConnectionServerConfiguration {

    @JsonProperty
    private boolean hostAutoStartEnabled;
    @JsonProperty
    private int hostPort;
    @JsonProperty
    private String hostPassword;

    @Override
    public boolean isHostAutoStartEnabled() {
        return hostAutoStartEnabled;
    }

    public void setHostAutoStartEnabled(boolean hostAutoStartEnabled) {
        this.hostAutoStartEnabled = hostAutoStartEnabled;
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

    public void setHostPassword(String hostPassword) {
        this.hostPassword = hostPassword;
    }
}
