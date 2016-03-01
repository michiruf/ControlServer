package de.michiruf.control_server.client.config;

/**
 * @author Michael Ruf
 * @since 2016-02-29
 */
public class DefaultConfiguration implements Configuration {

    private final String host;
    private final int port;

    public DefaultConfiguration(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public String getHost() {
        return host;
    }

    @Override
    public int getPort() {
        return port;
    }
}
