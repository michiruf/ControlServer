package de.michiruf.control_server.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Michael Ruf
 * @since 2015-10-01
 */
public class ExampleEvents {

    public static Event keyEvent() {
        return new Event(); // TODO
    }

    public static String keyEventString(ObjectMapper mapper) {
        try {
            return mapper.writeValueAsString(keyEvent());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Event mouseEvent() {
        return new Event(); // TODO
    }

    public static String mouseEventString(ObjectMapper mapper) {
        try {
            return mapper.writeValueAsString(mouseEvent());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
