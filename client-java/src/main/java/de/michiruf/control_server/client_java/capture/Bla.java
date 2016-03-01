package de.michiruf.control_server.client_java.capture;

import java.awt.AWTEvent;
import java.awt.Toolkit;

/**
 * @author Michael Ruf
 * @since 2016-03-01
 */
public class Bla {

    public void bla() {
        Toolkit.getDefaultToolkit().addAWTEventListener(
                event -> {
                    System.out.println("Yolo");
                },
                AWTEvent.KEY_EVENT_MASK | AWTEvent.MOUSE_EVENT_MASK);
    }
}
