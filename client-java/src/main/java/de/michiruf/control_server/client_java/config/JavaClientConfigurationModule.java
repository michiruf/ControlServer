package de.michiruf.control_server.client_java.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import dagger.Module;
import dagger.Provides;
import de.michiruf.control_server.client.config.DirectConnectionClientConfiguration;
import de.michiruf.control_server.client.config.DirectConnectionServerConfiguration;
import de.michiruf.control_server.client.config.WebServerClientConfiguration;

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
    @Named("ConfigurationPath")
    public Path provideDirectConnectionClientConfigurationPath() {
        return Paths.get("settings.json");
    }

    @SuppressWarnings("unused")
    @Provides
    @Singleton
    public JavaClientConfiguration provideJavaDirectConnectionClientConfiguration(
            ObjectMapper objectMapper,
            @Named("ConfigurationPath") Path path,
            SaveHook saveHook) {
        JavaClientConfiguration configuration;
        try {
            byte[] data = Files.readAllBytes(path);
            configuration = objectMapper.readValue(data, JavaClientConfiguration.class);
        } catch (IOException e) {
            configuration = new JavaClientConfiguration().reset();
        }
        saveHook.register(configuration, path);
        return configuration;
    }

    @SuppressWarnings("unused")
    @Provides
    public DirectConnectionClientConfiguration provideDirectConnectionClientConfiguration(
            JavaClientConfiguration configuration) {
        // Provide to satisfy the client module
        return configuration.getDirectConnectionClient();
    }

    @SuppressWarnings("unused")
    @Provides
    public DirectConnectionServerConfiguration provideDirectConnectionServerConfiguration(
            JavaClientConfiguration configuration) {
        // Provide to satisfy the client module
        return configuration.getDirectConnectionServer();
    }

    @SuppressWarnings("unused")
    @Provides
    public WebServerClientConfiguration provideWebServerConfiguration(
            JavaClientConfiguration configuration) {
        // Provide to satisfy the client module
        return configuration.getWebServerClient();
    }
}
