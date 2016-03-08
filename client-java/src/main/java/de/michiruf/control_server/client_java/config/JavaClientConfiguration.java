package de.michiruf.control_server.client_java.config;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.michiruf.control_server.client.config.Configuration;

/**
 * @author Michael Ruf
 * @since 2016-03-08
 */
public class JavaClientConfiguration implements Configuration {

    @JsonProperty
    private String host;

    @JsonProperty
    private int port;

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

    @JsonIgnore
    public boolean isProperlyConfigured() {
        return host != null && host.length() > 0 && port != 0;
    }
}
