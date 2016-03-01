package de.michiruf.control_server.client_java.capture;

import dagger.Module;

/**
 * @author Michael Ruf
 * @since 2016-03-01
 */
@Module(
        injects = {
                Capture.class
        },
        library = true
)
public class CaptureModule {
}
