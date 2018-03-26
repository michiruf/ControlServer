package de.michiruf.control_server.common.control;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * @author Michael Ruf
 * @since 2018-02-27
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
public class EndControlResponse {
}
