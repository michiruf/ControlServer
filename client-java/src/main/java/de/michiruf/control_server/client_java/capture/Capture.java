package de.michiruf.control_server.client_java.capture;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.awt.AWTEvent;
import java.awt.Toolkit;

/**
 * @author Michael Ruf
 * @since 2016-03-01
 */
@Singleton
public class Capture {

    private boolean active;

    @Inject
    public Capture() {
    }

    public void bla() {
        // TODO
        Toolkit.getDefaultToolkit().addAWTEventListener(
                event -> {
                    System.out.println("Yolo");
                },
                AWTEvent.KEY_EVENT_MASK | AWTEvent.MOUSE_EVENT_MASK);
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isActive() {
        return active;
    }
}
