package de.michiruf.control_server.client.dispatch;

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

    public void dispatch() {
        for(EventDispatchListener listener : listeners) {
            listener.onEvent(); // TODO add Event!
        }
    }

    public void registerListener(EventDispatchListener listener) {
        listeners.add(listener);
    }

    public interface EventDispatchListener {

        void onEvent(); // TODO add Event!
    }
}
