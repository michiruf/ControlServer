package de.michiruf.control_server.common.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.michiruf.control_server.common.EventData;

/**
 * @author Michael Ruf
 * @since 2015-11-16
 */
public class MouseData extends EventData {

    @JsonProperty("mouseX")
    private int mouseX;
    @JsonProperty("mouseY")
    private int mouseY;

    protected MouseData() {
        // For jackson
    }

    public MouseData(int mouseX, int mouseY) {
        this.mouseX = mouseX;
        this.mouseY = mouseY;
    }

    @JsonIgnore
    public int getMouseX() {
        return mouseX;
    }

    @JsonIgnore
    public int getMouseY() {
        return mouseY;
    }
}
