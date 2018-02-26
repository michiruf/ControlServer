package de.michiruf.control_server.client.control;

import de.michiruf.control_server.client.Logger;
import de.michiruf.control_server.common.event.Event;
import de.michiruf.control_server.common.event.KeyData;

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
        if (!(event.getData() instanceof KeyData)) {
            return false;
        }

        KeyData data = event.getDataAs(KeyData.class);
        Logger.log("[Server] KeyControlExecutor got key: %s", data.getKey());

        switch (event.getDirection()) {
            case DOWN:
                robot.keyPress(data.getKey());
                break;
            case UP:
                robot.keyRelease(data.getKey());
                break;
        }

        return true;
    }
}