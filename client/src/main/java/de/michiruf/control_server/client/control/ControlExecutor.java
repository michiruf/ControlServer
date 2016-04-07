package de.michiruf.control_server.client.control;

import de.michiruf.control_server.common.Event;

/**
 * @author Michael Ruf
 * @since 2015-09-28
 */
public interface ControlExecutor {

    boolean perform(Event e);
}
