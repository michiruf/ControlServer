package de.michiruf.control_server.client.config;

/**
 * @author Michael Ruf
 * @since 2016-03-11
 */
public interface WebServerClientConfiguration {

    String getHost();

    int getPort();

    boolean isSendControlsEnabled();

    boolean isControlListeningEnabled();
}