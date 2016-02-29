package de.michiruf.control_server.common.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.michiruf.control_server.common.EventData;

/**
 * @author Michael Ruf
 * @since 2015-11-16
 */
public class KeyData extends EventData {

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
