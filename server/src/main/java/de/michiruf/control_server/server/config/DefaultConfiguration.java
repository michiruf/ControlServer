package de.michiruf.control_server.server.config;

/**
 * @author Michael Ruf
 * @since 2016-02-29
 */
public class DefaultConfiguration implements Configuration {

    private int port;

    public DefaultConfiguration(int port) {
        this.port = port;
    }

    @Override
    public int getPort() {
        return port;
    }
}
