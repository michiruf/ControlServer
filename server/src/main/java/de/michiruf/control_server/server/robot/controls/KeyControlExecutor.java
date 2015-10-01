package de.michiruf.control_server.server.robot.controls;

import de.michiruf.control_server.common.Event;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.awt.Robot;

/**
 * @author Michael Ruf
 * @since 2015-09-28
 */
@Singleton
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
