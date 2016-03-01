package de.michiruf.control_server.client_java.capture;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author Michael Ruf
 * @since 2016-03-01
 */
@Singleton
public class Capture {

    private final CaptureFrame captureFrame;

    private boolean active;

    @Inject
    public Capture(CaptureFrame captureFrame) {
        this.captureFrame = captureFrame;

        // Initially the frame shell not be active
        setActive(false);
    }

    public void setActive(boolean active) {
        this.active = active;
        captureFrame.setVisible(active);
    }

    public boolean isActive() {
        return active;
    }
}
