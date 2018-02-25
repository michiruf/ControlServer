package de.michiruf.control_server.client_java.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.michiruf.control_server.client.config.DirectConnectionClientConfiguration;

/**
 * @author Michael Ruf
 * @since 2018-02-25
 */
public class JavaClientDirectConnectionClientConfiguration implements DirectConnectionClientConfiguration {

    @JsonProperty
    private String host;
    @JsonProperty
    private int port;
    @JsonProperty
    private String password;

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
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
