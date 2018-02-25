package de.michiruf.control_server.client;

import dagger.Module;
import dagger.Provides;
import de.michiruf.control_server.client.config.DirectConnectionServerConfiguration;
import de.michiruf.control_server.client.config.WebServerClientConfiguration;
import de.michiruf.control_server.client.qualifier.ForDirectConnection;
import de.michiruf.control_server.client.qualifier.ForWebServer;

import javax.inject.Singleton;

/**
 * @author Michael Ruf
 * @since 2016-03-12
 */
@Module(
        library = true,
        complete = false
)
public class ConfigurationModule {

    @SuppressWarnings("unused")
    @Provides
    @Singleton
    @ForWebServer
    public WebServerClientConfiguration provideWebServerClientConfiguration(
            @ForWebServer String host,
            @ForDirectConnection WebServerClientConfiguration directConnectionClientConfiguration) {
        return new WebServerClientConfiguration() {
            @Override
            public String getHost() {
                return host;
            }

            @Override
            public int getPort() {
                return 12345;
            }

            @Override
            public boolean isSendControlsEnabled() {
                return true;
            }

            @Override
            public boolean isControlListeningEnabled() {
                return true;
            }
        };
    }

    @SuppressWarnings("unused")
    @Provides
    @Singleton
    @ForDirectConnection
    public WebServerClientConfiguration provideDirectConnectionClientConfiguration() {
        return new WebServerClientConfiguration() {
            @Override
            public String getHost() {
                return "localhost";
            }

            @Override
            public int getPort() {
                return 12345;
            }

            @Override
            public boolean isSendControlsEnabled() {
                return true;
            }

            @Override
            public boolean isControlListeningEnabled() {
                return false;
            }
        };
    }

    @SuppressWarnings("unused")
    @Provides
    @Singleton
    public DirectConnectionServerConfiguration provideServerConfiguration() {
        return new DirectConnectionServerConfiguration() {
            @Override
            public int getHostPort() {
                return 12345;
            }

            @Override
            public String getHostPassword() {
                return "HelloWorld!";
            }

            @Override
            public boolean isHostAutoStartEnabled() {
                return true;
            }
        };
    }
}
