package de.michiruf.control_server.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import de.michiruf.control_server.common.event.Event;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * @author Michael Ruf
 * @since 2015-10-01
 */
public class EventSerializationTest {

    private ObjectMapper objectMapper;

    @Before
    public void setUp() {
        objectMapper = new ObjectMapper();
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
    }

    @Test
    public void testSerializeKeyEvent() throws JsonProcessingException {
        objectMapper.writeValueAsString(ExampleEvents.keyEvent());
    }

    @Test
    public void testSerializeMouseEvent() throws JsonProcessingException {
        objectMapper.writeValueAsString(ExampleEvents.mouseEvent());
    }

    @Test
    public void testDeserializeKeyEvent() throws IOException {
        objectMapper.readValue(ExampleEvents.keyEventString(objectMapper), Event.class);
    }

    @Test
    public void testDeserializeMouseEvent() throws IOException {
        objectMapper.readValue(ExampleEvents.mouseEventString(objectMapper), Event.class);
    }
}
