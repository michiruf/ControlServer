package de.michiruf.control_server.client_java.capture;

import de.michiruf.control_server.client_java.capture.control.ControlListener;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.awt.event.KeyEvent;

/**
 * @author Michael Ruf
 * @since 2016-03-01
 */
@Singleton
public class Capture {

    private final CaptureFrame captureFrame;
    private final ControlListener controlListener;

    @Inject
    public Capture(CaptureFrame captureFrame, ControlListener controlListener) {
        this.captureFrame = captureFrame;
        this.controlListener = controlListener;

        // Initially the frame shell not be active
        captureFrame.setVisible(false);

        controlListener.setCancelParameters(KeyEvent.CTRL_DOWN_MASK, KeyEvent.VK_ESCAPE, this::stop);
        controlListener.setMayRequiredCaptureFrame(captureFrame);
    }

    public void start() {
        captureFrame.setVisible(true);
        controlListener.start();
    }

    public void stop() {
        captureFrame.setVisible(false);
        controlListener.stop();
    }
}
