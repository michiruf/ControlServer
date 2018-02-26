package de.michiruf.control_server.client.event;

import de.michiruf.control_server.client.Logger;
import de.michiruf.control_server.client.control.ControlExecutor;
import de.michiruf.control_server.common.event.Event;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

/**
 * @author Michael Ruf
 * @since 2015-09-28
 */
@Singleton
public class EventExecutionHandler {

    private final List<ControlExecutor> controlExecutors;
    private final StringEventParser stringEventParser;

    @Inject
    public EventExecutionHandler(List<ControlExecutor> controlExecutors,
                                 StringEventParser stringEventParser) {
        this.controlExecutors = controlExecutors;
        this.stringEventParser = stringEventParser;
    }

    public void handleStringEvent(String event) {
        handleEvent(stringEventParser.parse(event));
    }

    public void handleEvent(Event event) {
        Logger.log("[Server] EventHandler got event: %s", event);
        for (ControlExecutor controlExecutor : controlExecutors) {
            if (controlExecutor.perform(event)) {
                break;
            }
        }
    }
}
