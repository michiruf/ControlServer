package de.michiruf.control_server.client_java.capture;

import de.michiruf.control_server.client.dispatch.EventDispatcher;
import de.michiruf.control_server.common.Direction;
import de.michiruf.control_server.common.Event;
import de.michiruf.control_server.common.data.KeyData;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Date;

/**
 * @author Michael Ruf
 * @since 2016-03-01
 */
// NOTE we want to exit capturing when pressing CTRL + ESC
// everything else shell be captured including key combinations
@Singleton
public class KeyCaptureListener implements KeyListener {

    private final EventDispatcher dispatcher;

    @Inject
    public KeyCaptureListener(EventDispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // This event gets triggered after
        //System.out.println("keyTyped");
        //
        //dispatcher.dispatch(new Event(
        //        Direction.DOWN,
        //        new KeyData(e.getKeyCode()),
        //        new Date(System.currentTimeMillis())));
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("keyPressed");

        dispatcher.dispatch(new Event(
                Direction.DOWN,
                new KeyData(e.getKeyCode()),
                new Date(System.currentTimeMillis())));
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("keyReleased");

        dispatcher.dispatch(new Event(
                Direction.UP,
                new KeyData(e.getKeyCode()),
                new Date(System.currentTimeMillis())));
    }
}
