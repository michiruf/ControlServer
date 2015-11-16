package de.michiruf.control_server.server;

/**
 * @author Michael Ruf
 * @since 2015-09-28
 */
public interface Configuration {

    int getPort();

    Configuration DEFAULT = createDefaultConfiguration();

    static Configuration createDefaultConfiguration() {
        // TODO guess we will only need this while development due to
        // we can inject a "StoredConfigurationProvider" or a
        // "SerializedConfigurationProvider" or something like this
        return new Configuration() {
            @Override
            public int getPort() {
                return 12345;
            }
        };
    }
}
