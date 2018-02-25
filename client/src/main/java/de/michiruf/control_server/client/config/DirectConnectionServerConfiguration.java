package de.michiruf.control_server.client.config;

/**
 * @author Michael Ruf
 * @since 2016-03-11
 */
public interface DirectConnectionServerConfiguration {

    boolean isAutoStartEnabled();

    int getHostPort();

    String getHostPassword();
}
