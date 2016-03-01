package de.michiruf.control_server.client.config;

/**
 * @author Michael Ruf
 * @since 2015-11-24
 */
public interface Configuration {

    String getHost();

    int getPort();

    Configuration DEFAULT = createDefaultConfiguration();

    static Configuration createDefaultConfiguration() {
        // TODO guess we will only need this while development due to
        // we can inject a "StoredConfigurationProvider" or a
        // "SerializedConfigurationProvider" or something like this
        return new Configuration() {
            @Override
            public String getHost() {
                return "localhost";
            }

            @Override
            public int getPort() {
                return 12345;
            }
        };
    }
}
