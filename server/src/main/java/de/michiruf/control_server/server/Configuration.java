package de.michiruf.control_server.server;

/**
 * @author Michael Ruf
 * @since 2015-09-28
 */
public interface Configuration {

    int getPort();

    Configuration DEFAULT = createDefaultConfiguration();

    static Configuration createDefaultConfiguration() {
        return new Configuration() {
            @Override
            public int getPort() {
                return 12345;
            }
        };
    }
}
