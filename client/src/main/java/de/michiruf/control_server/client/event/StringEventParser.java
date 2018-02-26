package de.michiruf.control_server.client.event;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.michiruf.control_server.client.ErrorHandler;
import de.michiruf.control_server.client.Logger;
import de.michiruf.control_server.common.event.Event;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.IOException;

/**
 * @author Michael Ruf
 * @since 2015-09-30
 */
@Singleton
public class StringEventParser {

    private final ObjectMapper objectMapper;

    @Inject
    public StringEventParser(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public Event parse(String eventString) {
        Logger.log("[Server] StringEventParser got string event: %s", eventString);
        try {
            return objectMapper.readValue(eventString, Event.class);
        } catch (IOException e) {
            ErrorHandler.handle(e);
        }
        return null;
    }
}
