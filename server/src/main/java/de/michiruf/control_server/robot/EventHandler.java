package de.michiruf.control_server.robot;

import de.michiruf.control_server.robot.controls.ControlExecutor;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

/**
 * @author Michael Ruf
 * @since 2015-09-28
 */
@Singleton
public class EventHandler {

    private List<ControlExecutor> controlExecutors;

    @Inject
    public EventHandler(List<ControlExecutor> controlExecutors) {
        this.controlExecutors = controlExecutors;
    }

    public void handleStringEvent(String event) {
        // TODO parse string into event
    }

    public void handleEvent(Event event) {
        for (ControlExecutor controlExecutor : controlExecutors) {
            if (controlExecutor.perform(event)) {
                break;
            }
        }
    }
}
