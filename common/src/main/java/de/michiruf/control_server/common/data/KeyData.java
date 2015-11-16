package de.michiruf.control_server.common.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.michiruf.control_server.common.EventData;

/**
 * @author Michael Ruf
 * @since 2015-11-16
 */
public class KeyData extends EventData {

    @JsonProperty("key")
    private String key;

    protected KeyData() {
        // For jackson
    }

    public KeyData(String key) {
        this.key = key;
    }

    @JsonIgnore
    public String getKey() {
        return key;
    }
}
