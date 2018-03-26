package de.michiruf.control_server.common.user;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * @author Michael Ruf
 * @since 2018-03-21
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
public class UnauthenticatedError {
}
