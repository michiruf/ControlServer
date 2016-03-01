package de.michiruf.control_server.client_java.capture;

import de.michiruf.control_server.client.dispatch.EventDispatcher;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * @author Michael Ruf
 * @since 2016-03-01
 */
// TODO
@Singleton
public class MouseCaptureListener implements MouseListener {

    private final EventDispatcher dispatcher;

    @Inject
    public MouseCaptureListener(EventDispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("mouseClicked");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("mousePressed");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("mouseReleased");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        System.out.println("mouseEntered");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        System.out.println("mouseExited");
    }
}
