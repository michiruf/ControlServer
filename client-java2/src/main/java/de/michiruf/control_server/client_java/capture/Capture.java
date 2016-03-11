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
    private final KeyCaptureListener keyCaptureListener;
    private final MouseCaptureListener mouseCaptureListener;

    private boolean active;

    @Inject
    public Capture(CaptureFrame captureFrame, KeyCaptureListener keyCaptureListener, MouseCaptureListener mouseCaptureListener) {
        this.captureFrame = captureFrame;
        this.keyCaptureListener = keyCaptureListener;
        this.mouseCaptureListener = mouseCaptureListener;
        registerListeners();

        // Initially the frame shell not be active
        setActive(false);
    }

    private void registerListeners() {
        captureFrame.addKeyListener(keyCaptureListener);
        captureFrame.addMouseListener(mouseCaptureListener);
        captureFrame.addMouseMotionListener(mouseCaptureListener);
        captureFrame.addMouseWheelListener(mouseCaptureListener);
    }

    // TODO this should be no good option (Client param)
    public void startFor(Client client) {
        active = true;
        captureFrame.setVisible(true);
        client.connect();
    }

    public void stopFor(Client client) {
        active = true;
        captureFrame.setVisible(true);
        client.disconnect();
    }

    public void setActive(boolean active) {
        this.active = active;
        captureFrame.setVisible(active);

        // TODO move this anywhere else?!
//        if (active)
//            client.connect();
//        else
//            client.disconnect();
    }

    public boolean isActive() {
        return active;
    }
}
