package de.michiruf.control_server.common.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * @author Michael Ruf
 * @since 2015-11-16
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
public class KeyData {

    @JsonProperty("key")
    private int key;

    @SuppressWarnings("unused") // for jackson
    protected KeyData() {
    }

    public KeyData(int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }
}
