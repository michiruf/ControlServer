package de.michiruf.control_server.client.convert;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.michiruf.control_server.common.Event;

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
        System.out.println(String.format(
                "[Client] EventStringConverter got event: %s", event));
        try {
            return objectMapper.writeValueAsString(event);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return null;
    }
}
