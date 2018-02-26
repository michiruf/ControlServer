package de.michiruf.control_server.common.event;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.Date;

/**
 * @author Michael Ruf
 * @since 2015-09-28
 */
public class Event {

    @JsonProperty("direction")
    private Direction direction;
    @JsonProperty("data")
    @JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
    @JsonSubTypes({
            @JsonSubTypes.Type(KeyData.class),
            @JsonSubTypes.Type(MouseData.class)
    })
    private Object data;
    @JsonProperty("time")
    private Date time;

    @SuppressWarnings("unused") // for jackson
    protected Event() {
    }

    public Event(Direction direction, Object data, Date date) {
        this.direction = direction;
        this.data = data;
        this.time = date;
    }

    public Direction getDirection() {
        return direction;
    }

    public Object getData() {
        return data;
    }

    @SuppressWarnings("unchecked")
    @JsonIgnore
    public <T> T getDataAs(Class<T> clazz) {
        if (getData().getClass() == clazz) {
            return (T) getData();
        }
        return null;
    }

    public Date getTime() {
        return time;
    }
}
