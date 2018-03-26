package de.michiruf.control_server.common.event;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * @author Michael Ruf
 * @since 2015-11-16
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
public enum Direction {

    UP, DOWN, UNDEFINED
}
