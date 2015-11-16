package de.michiruf.control_server.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import de.michiruf.control_server.common.data.KeyData;
import de.michiruf.control_server.common.data.MouseData;

import java.util.Date;

/**
 * @author Michael Ruf
 * @since 2015-09-28
 */
public class Event {

    @JsonProperty("type")
    private Type type;
    @JsonProperty("direction")
    private Direction direction;
    @JsonProperty("data")
    @JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
    @JsonSubTypes({
            @JsonSubTypes.Type(KeyData.class),
            @JsonSubTypes.Type(MouseData.class)
    })
    private EventData data;
    @JsonProperty("time")
    private Date time;

    protected Event() {
        // For jackson
    }

    public Event(Type type, Direction direction, EventData data, Date date) {
        this.type = type;
        this.direction = direction;
        this.data = data;
        this.time = date;
    }

    @JsonIgnore
    public Type getType() {
        return type;
    }

    @JsonIgnore
    public Direction getDirection() {
        return direction;
    }

    @JsonIgnore
    public EventData getData() {
        return data;
    }

    @JsonIgnore
    @SuppressWarnings("unchecked")
    public <T> T getDataAs(Class<T> clazz) {
        try {
            return (T) getData();
        } catch (ClassCastException e) {
            return null;
        }
    }

    @JsonIgnore
    public Date getTime() {
        return time;
    }
}
