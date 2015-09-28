package de.michiruf.control_server.robot.controls;

import de.michiruf.control_server.robot.Event;

/**
 * @author Michael Ruf
 * @since 2015-09-28
 */
public interface ControlExecutor {

    boolean perform(Event e);
}
