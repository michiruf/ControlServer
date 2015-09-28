package de.michiruf.control_server.robot.controls;

import de.michiruf.control_server.robot.Event;

import javax.inject.Inject;
import java.awt.Robot;

/**
 * @author Michael Ruf
 * @since 2015-09-28
 */
public class KeyControlExecutor implements ControlExecutor {

    private final Robot robot;

    @Inject
    public KeyControlExecutor(Robot robot) {
        this.robot = robot;
    }

    public boolean perform(Event event) {
        // TODO
        return true;
    }
}
