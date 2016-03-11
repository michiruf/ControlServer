package de.michiruf.control_server.client.config;

import dagger.Module;
import dagger.Provides;
import de.michiruf.control_server.client.qualifier.ForWebServer;

import javax.inject.Singleton;

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

    @SuppressWarnings("unused")
    @Provides
    @Singleton
    @ForWebServer
    public ClientConfiguration provideWebClientConfiguration() {
        return new ClientConfiguration() {
            @Override
            public String getHost() {
                return "localhost"; // TODO
            }

            @Override
            public int getPort() {
                return 80;
            }

            @Override
            public boolean isControllable() {
                return true;
            }
        };
    }
}
