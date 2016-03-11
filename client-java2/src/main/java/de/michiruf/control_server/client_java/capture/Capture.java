package de.michiruf.control_server.client_java.capture;

import de.michiruf.control_server.client.comm.Client;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author Michael Ruf
 * @since 2016-03-01
 */
@Singleton
public class Capture {

    private final CaptureFrame captureFrame;
    private final Client client;

    private boolean active;

    @Inject
    public Capture(CaptureFrame captureFrame, Client client) {
        this.captureFrame = captureFrame;
        this.client = client;

        // Initially the frame shell not be active
        setActive(false);
    }

    public void setActive(boolean active) {
        this.active = active;
        captureFrame.setVisible(active);

        // TODO move this anywhere else?!
        if (active)
            client.connect();
        else
            client.disconnect();
    }

    public boolean isActive() {
        return active;
    }
}
