package de.michiruf.control_server.client_java.capture;

import de.michiruf.control_server.client_java.capture.control.KeyCaptureListener;
import de.michiruf.control_server.client_java.capture.control.MouseCaptureListener;

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

    @Deprecated
    public void setActive(boolean active) {
        captureFrame.setVisible(active);
    }

    @Deprecated
    public boolean isActive() {
        return captureFrame.isVisible();
    }

    public void start() {
        captureFrame.setVisible(true);
    }
}
