package de.michiruf.control_server.client.config;

import dagger.Module;
import dagger.Provides;
import de.michiruf.control_server.client.qualifier.ForWebServer;

/**
 * @author Michael Ruf
 * @since 2016-02-29
 */
@Module(
        injects = {
                ClientConfiguration.class,
                ServerConfiguration.class
        },
        library = true,
        complete = false
)
public class ConfigurationModule {
}
