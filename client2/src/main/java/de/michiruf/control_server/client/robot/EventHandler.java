package de.michiruf.control_server.client.robot;

import de.michiruf.control_server.common.Event;
import de.michiruf.control_server.server.convert.StringEventParser;
import de.michiruf.control_server.server.robot.controls.ControlExecutor;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

/**
 * @author Michael Ruf
 * @since 2015-09-28
 */
@Singleton
public class EventHandler {

    private final List<ControlExecutor> controlExecutors;
    private final StringEventParser stringEventParser;

    @Inject
    public EventHandler(List<ControlExecutor> controlExecutors,
                        StringEventParser stringEventParser) {
        this.controlExecutors = controlExecutors;
        this.stringEventParser = stringEventParser;
    }

    public void handleStringEvent(String event) {
        handleEvent(stringEventParser.parse(event));
    }

    public void handleEvent(Event event) {
        System.out.println(String.format("[Server] EventHandler got event: %s", event));
        for (ControlExecutor controlExecutor : controlExecutors) {
            if (controlExecutor.perform(event)) {
                break;
            }
        }
    }
}
