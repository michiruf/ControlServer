package de.michiruf.control_server.client_java;

import dagger.Module;
import de.michiruf.control_server.client.ControlClientModule;
import de.michiruf.control_server.client_java.capture.CaptureModule;
import de.michiruf.control_server.client_java.config.JavaClientConfigurationModule;
import de.michiruf.control_server.client_java.ui.UiModule;

/**
 * @author Michael Ruf
 * @since 2016-03-01
 */
@Module(
        includes = {
                ControlClientModule.class,
                CaptureModule.class,
                JavaClientConfigurationModule.class,
                UiModule.class
        },
        // TODO why is this necessary?
        complete = false
)
public class JavaControlClientModule {
}
