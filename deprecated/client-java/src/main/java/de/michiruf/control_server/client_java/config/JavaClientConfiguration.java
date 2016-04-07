package de.michiruf.control_server.client_java.config;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.michiruf.control_server.client.config.Configuration;
import de.michiruf.control_server.common.data.MouseData;

/**
 * @author Michael Ruf
 * @since 2016-03-08
 */
public class JavaClientConfiguration implements Configuration {

    @JsonProperty
    private String host;

    @JsonProperty
    private int port;

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
