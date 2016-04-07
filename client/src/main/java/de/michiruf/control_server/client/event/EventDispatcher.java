package de.michiruf.control_server.client.event;

import de.michiruf.control_server.common.Event;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Michael Ruf
 * @since 2015-11-29
 */
@Singleton
public class EventDispatcher {

    private final List<EventDispatchListener> listeners;

    @Inject
    public EventDispatcher() {
        listeners = new ArrayList<>();
    }

    public void dispatch(Event event) {
        for (EventDispatchListener listener : listeners) {
            listener.onEvent(event);
        }
    }

    public void registerListener(EventDispatchListener listener) {
        listeners.add(listener);
    }

    public interface EventDispatchListener {

        void onEvent(Event event);
    }
}
