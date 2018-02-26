package de.michiruf.control_server.client.config;

import dagger.Module;

/**
 * @author Michael Ruf
 * @since 2016-02-29
 */
@Module(
        injects = {
                DirectConnectionClientConfiguration.class,
                DirectConnectionServerConfiguration.class,
                WebServerClientConfiguration.class
        },
        library = true,
        complete = false
)
public class ConfigurationModule {
}
