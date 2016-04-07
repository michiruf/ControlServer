package de.michiruf.control_server.client_java.capture;

import dagger.Module;
import de.michiruf.control_server.client_java.capture.control.ControlModule;

/**
 * @author Michael Ruf
 * @since 2016-03-01
 */
@Module(
        includes = {
                ControlModule.class
        },
        injects = {
                Capture.class,
                CaptureFrame.class
        },
        library = true,
        complete = false
)
public class CaptureModule {
}
