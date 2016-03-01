package de.michiruf.control_server.client_java.capture;

import de.michiruf.control_server.client.dispatch.EventDispatcher;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * @author Michael Ruf
 * @since 2016-03-01
 */
// TODO
// NOTE we want to exit capturing when pressing CTRL + ESC
@Singleton
public class KeyCaptureListener implements KeyListener {

    private final EventDispatcher dispatcher;

    @Inject
    public KeyCaptureListener(EventDispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println("keyTyped");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("keyPressed");
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("keyReleased");
    }
}
