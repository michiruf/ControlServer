package de.michiruf.control_server.client_java.capture;

import dagger.Module;
import de.michiruf.control_server.client_java.capture.control.KeyCaptureListener;
import de.michiruf.control_server.client_java.capture.control.MouseCaptureListener;

/**
 * @author Michael Ruf
 * @since 2016-03-01
 */
@Module(
        injects = {
                Capture.class,
                CaptureFrame.class,
                KeyCaptureListener.class,
                MouseCaptureListener.class
        },
        library = true,
        complete = false
)
public class CaptureModule {
}
