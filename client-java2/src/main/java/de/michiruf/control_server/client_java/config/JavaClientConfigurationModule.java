package de.michiruf.control_server.client_java.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import dagger.Module;
import dagger.Provides;
import de.michiruf.control_server.client.config.ClientConfiguration;
import de.michiruf.control_server.client.config.ServerConfiguration;

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
            configuration = new JavaClientConfiguration();
        }

        saveHook.register(configuration);
        return configuration;
    }

    @SuppressWarnings("unused")
    @Provides
    @Singleton
    public ClientConfiguration provideClientConfiguration(JavaClientConfiguration configuration) {
        // For the client module
        return configuration;
    }

    @SuppressWarnings("unused")
    @Provides
    @Singleton
    public ServerConfiguration provideServerConfiguration(JavaClientConfiguration configuration) {
        // For the client module
        return configuration;
    }
}
