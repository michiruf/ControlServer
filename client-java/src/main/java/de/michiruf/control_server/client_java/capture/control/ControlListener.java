package de.michiruf.control_server.client_java.capture.control;

import de.michiruf.control_server.client_java.capture.CaptureFrame;

/**
 * @author Michael Ruf
 * @since 2016-04-02
 */
public interface ControlListener {

    void setCancelParameters(int mask, int key, Runnable cancel);

    void setMayRequiredCaptureFrame(CaptureFrame captureFrame);

    void start();

    void stop();
}
