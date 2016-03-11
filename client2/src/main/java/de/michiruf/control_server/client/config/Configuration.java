package de.michiruf.control_server.client.config;

/**
 * @author Michael Ruf
 * @since 2015-11-24
 */
public interface Configuration {

    String getDirectConnectionHost();

    int getDirectConnectionPort();

    // is directly connectible

    // port for this
    int getPort();
}
