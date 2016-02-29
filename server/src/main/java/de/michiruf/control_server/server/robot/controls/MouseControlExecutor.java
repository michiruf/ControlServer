package de.michiruf.control_server.server.robot.controls;

import de.michiruf.control_server.common.Direction;
import de.michiruf.control_server.common.Event;
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
    private Direction lastDirection;

    @Inject
    public MouseControlExecutor(Robot robot) {
        this.robot = robot;
    }

    public boolean perform(Event event) {
        if (!(event.getData() instanceof MouseData)) {
            return false;
        }

        MouseData data = event.getDataAs(MouseData.class);
        System.out.println(String.format(
                "[Server] MouseControlExecutor got x: %s, y: %s",
                data.getMouseX(), data.getMouseY()));

        robot.mouseMove(data.getMouseX(), data.getMouseY());

        if (event.getDirection() != Direction.UNDEFINED && event.getDirection() != lastDirection) {
            switch (event.getDirection()) {
                case DOWN:
                    robot.mousePress(data.getButton());
                    break;
                case UP:
                    robot.mouseRelease(data.getButton());
                    break;
            }
        }

        return false;
    }
}
