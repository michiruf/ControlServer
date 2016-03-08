package de.michiruf.control_server.client_java.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.michiruf.control_server.client.config.Configuration;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

/**
 * @author Michael Ruf
 * @since 2016-03-08
 */
public class SaveHook {

    private final ObjectMapper objectMapper;
    private final Path configurationPath;

    @Inject
    public SaveHook(ObjectMapper objectMapper,
                    @Named("configurationPath") Path configurationPath) {
        this.objectMapper = objectMapper;
        this.configurationPath = configurationPath;
    }

    public void register(Configuration configuration) {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                try {
                    byte[] data = objectMapper.writeValueAsBytes(configuration);
                    Files.write(configurationPath, data, StandardOpenOption.CREATE, StandardOpenOption.WRITE,
                            StandardOpenOption.TRUNCATE_EXISTING);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
