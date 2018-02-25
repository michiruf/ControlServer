package de.michiruf.control_server.client_java.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import dagger.Module;
import dagger.Provides;

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
    @Named("DirectConnectionClientConfigurationPath")
    public Path provideDirectConnectionClientConfigurationPath() {
        return Paths.get("settings-direct-client.json");
    }

    @SuppressWarnings("unused")
    @Provides
    @Singleton
    public JavaClientDirectConnectionClientConfiguration provideDirectConnectionClientConfiguration(
            ObjectMapper objectMapper,
            @Named("DirectConnectionClientConfigurationPath") Path path,
            SaveHook saveHook) {
        JavaClientDirectConnectionClientConfiguration configuration;
        try {
            byte[] data = Files.readAllBytes(path);
            configuration = objectMapper.readValue(data, JavaClientDirectConnectionClientConfiguration.class);
        } catch (IOException e) {
            configuration = new JavaClientDirectConnectionClientConfiguration();
        }
        saveHook.register(configuration);
        return configuration;
    }

    @SuppressWarnings("unused")
    @Provides
    @Named("DirectConnectionServerConfigurationPath")
    public Path provideDirectConnectionServerConfigurationPath() {
        return Paths.get("settings-direct-server.json");
    }

    @SuppressWarnings("unused")
    @Provides
    @Singleton
    public JavaClientDirectConnectionServerConfiguration provideDirectConnectionServerConfiguration(
            ObjectMapper objectMapper,
            @Named("DirectConnectionServerConfigurationPath") Path path,
            SaveHook saveHook) {
        JavaClientDirectConnectionServerConfiguration configuration;
        try {
            byte[] data = Files.readAllBytes(path);
            configuration = objectMapper.readValue(data, JavaClientDirectConnectionServerConfiguration.class);
        } catch (IOException e) {
            configuration = new JavaClientDirectConnectionServerConfiguration();
        }
        saveHook.register(configuration);
        return configuration;
    }

    @SuppressWarnings("unused")
    @Provides
    @Named("WebServerClientConfigurationPath")
    public Path provideWebServerClientConfigurationPath() {
        return Paths.get("settings-web-client.json");
    }

    @SuppressWarnings("unused")
    @Provides
    @Singleton
    public JavaClientWebServerClientConfiguration provideConfiguration(
            ObjectMapper objectMapper,
            @Named("WebServerClientConfigurationPath") Path path,
            SaveHook saveHook) {
        JavaClientWebServerClientConfiguration configuration;
        try {
            byte[] data = Files.readAllBytes(path);
            configuration = objectMapper.readValue(data, JavaClientWebServerClientConfiguration.class);
        } catch (IOException e) {
            configuration = new JavaClientWebServerClientConfiguration();
        }
        saveHook.register(configuration);
        return configuration;
    }
}
