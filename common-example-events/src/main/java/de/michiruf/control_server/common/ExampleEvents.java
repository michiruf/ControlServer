package de.michiruf.control_server.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.michiruf.control_server.common.data.KeyData;
import de.michiruf.control_server.common.data.MouseData;

import java.awt.event.KeyEvent;
import java.util.Date;

/**
 * @author Michael Ruf
 * @since 2015-10-01
 */
public class ExampleEvents {

    public static Event keyEvent() {
        return new Event(
                Type.KEY,
                Direction.DOWN,
                new KeyData(KeyEvent.VK_W),
                new Date(System.currentTimeMillis()));
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
        return new Event(
                Type.MOUSE,
                Direction.DOWN,
                new MouseData(100, 100),
                new Date(System.currentTimeMillis()));
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
