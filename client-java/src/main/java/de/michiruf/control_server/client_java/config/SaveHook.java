package de.michiruf.control_server.client_java.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.michiruf.control_server.client.ErrorHandler;

import javax.inject.Inject;
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

    @Inject
    public SaveHook(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public void register(Object configuration, Path path) {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> save(configuration, path)));
    }

    private void save(Object configuration, Path path) {
        try {
            byte[] data = objectMapper.writeValueAsBytes(configuration);
            Files.write(path, data, StandardOpenOption.CREATE, StandardOpenOption.WRITE,
                    StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            ErrorHandler.handle(e);
        }
    }
}
