package de.michiruf.control_server.server.config;

import dagger.Module;

/**
 * @author Michael Ruf
 * @since 2016-02-29
 */
@Module(
        injects = {
                Configuration.class
        },
        library = true,
        complete = false
)
public class ConfigurationModule {
}
