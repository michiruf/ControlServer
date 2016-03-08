package de.michiruf.control_server.server.robot.controls;

import de.michiruf.control_server.common.Direction;
import de.michiruf.control_server.common.Event;
import de.michiruf.control_server.common.data.MouseData;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.awt.Robot;
import java.awt.event.InputEvent;

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
        if (!(event.getData() instanceof MouseData)) {
            return false;
        }

        MouseData data = event.getDataAs(MouseData.class);
        System.out.println(String.format(
                "[Server] MouseControlExecutor got x: %s, y: %s",
                data.getMouseX(), data.getMouseY()));

        // TODO CoordinateType!

        robot.mouseMove(data.getMouseX(), data.getMouseY());

        if (event.getDirection() != Direction.UNDEFINED && data.getButton() != 0) {
            switch (event.getDirection()) {
                case DOWN:
                    robot.mousePress(InputEvent.getMaskForButton(data.getButton()));
                    break;
                case UP:
                    robot.mouseRelease(InputEvent.getMaskForButton(data.getButton()));
                    break;
            }
        }

        return true;
    }
}
