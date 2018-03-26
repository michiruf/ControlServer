package de.michiruf.control_server.common.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * @author Michael Ruf
 * @since 2015-11-16
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
public class MouseData {

    @JsonProperty("mouseX")
    private int mouseX;
    @JsonProperty("mouseY")
    private int mouseY;
    @JsonProperty("button")
    private int button;
    @JsonProperty("coordinateType")
    private CoordinateType coordinateType;

    @SuppressWarnings("unused") // for jackson
    protected MouseData() {
    }

    public MouseData(int mouseX, int mouseY, int button, CoordinateType coordinateType) {
        this.mouseX = mouseX;
        this.mouseY = mouseY;
        this.button = button;
        this.coordinateType = coordinateType;
    }

    public int getMouseX() {
        return mouseX;
    }

    public int getMouseY() {
        return mouseY;
    }

    public int getButton() {
        return button;
    }

    public CoordinateType getCoordinateType() {
        return coordinateType;
    }

    public enum CoordinateType {

        RELATIVE, ABSOLUTE
    }
}
