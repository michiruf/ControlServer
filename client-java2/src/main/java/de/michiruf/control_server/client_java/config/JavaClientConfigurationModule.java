package de.michiruf.control_server.client_java.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import dagger.Module;
import dagger.Provides;
import de.michiruf.control_server.client.config.ClientConfiguration;
import de.michiruf.control_server.client.config.ServerConfiguration;
import de.michiruf.control_server.client.qualifier.ForDirectConnection;
import de.michiruf.control_server.client.qualifier.ForWebServer;

import javax.inject.Named;
import javax.inject.Singleton;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author Michael Ruf
 * @since 2016-03-08
 */
@Module(
        injects = {
                SaveHook.class
        },
        library = true,
        complete = false
)
public class JavaClientConfigurationModule {

    @SuppressWarnings("unused")
    @Provides
    @Singleton
    @Named("configurationPath")
    public Path provideConfigurationPath() {
        return Paths.get("settings.json");
    }

    @SuppressWarnings("unused")
    @Provides
    @Singleton
    public JavaClientConfiguration provideConfiguration(ObjectMapper objectMapper,
                                                        @Named("configurationPath") Path path,
                                                        SaveHook saveHook) {
        JavaClientConfiguration configuration;
        try {
            byte[] data = Files.readAllBytes(path);
            configuration = objectMapper.readValue(data, JavaClientConfiguration.class);
        } catch (IOException e) {
            // TODO Error (only as logging)
            configuration = new JavaClientConfiguration();
        }

        saveHook.register(configuration);
        return configuration;
    }


    @SuppressWarnings("unused")
    @Provides
    @Singleton
    @ForWebServer
    public ClientConfiguration provideWebServerClientConfiguration(
            @ForWebServer String host,
            JavaClientConfiguration configuration) {
        // For the client module
        return new ClientConfiguration() {
            @Override
            public String getHost() {
                return host;
            }

            @Override
            public int getPort() {
                return 80;
            }

            @Override
            public boolean isSendControlsEnabled() {
                return configuration.isSendControlsEnabled();
            }

            @Override
            public boolean isControlListeningEnabled() {
                return configuration.isControlListeningEnabled();
            }
        };
    }

    @SuppressWarnings("unused")
    @Provides
    @Singleton
    @ForDirectConnection
    public ClientConfiguration provideDirectConnectionClientConfiguration(JavaClientConfiguration configuration) {
        // For the client module
        return new ClientConfiguration() {
            @Override
            public String getHost() {
                return configuration.getHost();
            }

            @Override
            public int getPort() {
                return configuration.getPort();
            }

            @Override
            public boolean isSendControlsEnabled() {
                return !configuration.isSendControlsEnabled();
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
    public ServerConfiguration provideServerConfiguration(JavaClientConfiguration configuration) {
        // For the client module
        return configuration;
    }
}
