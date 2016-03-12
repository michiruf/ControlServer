package de.michiruf.control_server.client.config;

/**
 * @author Michael Ruf
 * @since 2016-03-11
 */
public interface ServerConfiguration {

    int getHostPort();

    boolean isAutoStartEnabled(); // TODO maybe back to isEnabled?
}
