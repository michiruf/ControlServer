package de.michiruf.control_server.server.robot.controls;

import de.michiruf.control_server.common.Event;
import de.michiruf.control_server.common.Type;
import de.michiruf.control_server.common.data.MouseData;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.awt.Robot;

/**
 * @author Michael Ruf
 * @since 2015-09-28
 */
@Singleton
public class MouseControlExecutor implements ControlExecutor {

    private final Robot robot;

    @Inject
    public MouseControlExecutor(Robot robot) {
        this.robot = robot;
    }

    public boolean perform(Event event) {
        if (event.getType() != Type.MOUSE) {
            return false;
        }

        MouseData data = event.getDataAs(MouseData.class);
        System.out.println(String.format(
                "[Server] MouseControlExecutor got x: %s, y: %s",
                data.getMouseX(), data.getMouseY()));
        switch (event.getDirection()) {
            case DOWN:
                robot.mouseMove(data.getMouseX(), data.getMouseY());
                break;
            case UP:
                // TODO
                break;
        }
        return false;
    }
}
