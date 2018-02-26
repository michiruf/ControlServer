package de.michiruf.control_server.client.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.michiruf.control_server.client.ErrorHandler;
import de.michiruf.control_server.client.Logger;
import de.michiruf.control_server.common.event.Event;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author Michael Ruf
 * @since 2015-11-30
 */
@Singleton
public class EventStringConverter {

    private final ObjectMapper objectMapper;

    @Inject
    public EventStringConverter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public String convert(Event event) {
        Logger.log("[Client] EventStringConverter got event: %s", event);
        try {
            return objectMapper.writeValueAsString(event);
        } catch (JsonProcessingException e) {
            ErrorHandler.handle(e);
        }
        return null;
    }
}