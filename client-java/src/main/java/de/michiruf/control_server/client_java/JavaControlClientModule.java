package de.michiruf.control_server.client_java;

import dagger.Module;
import dagger.Provides;
import de.michiruf.control_server.client.ControlClientModule;
import de.michiruf.control_server.client.config.Configuration;
import de.michiruf.control_server.client.config.DefaultConfiguration;
import de.michiruf.control_server.client_java.capture.CaptureModule;
import de.michiruf.control_server.client_java.ui.UiModule;

import javax.inject.Singleton;

/**
 * @author Michael Ruf
 * @since 2016-03-01
 */
@Module(
        includes = {
                ControlClientModule.class,
                CaptureModule.class,
                UiModule.class
        }
)
public class JavaControlClientModule {

    @SuppressWarnings("unused")
    @Provides
    @Singleton
    public Configuration provideConfiguration() {
        return new DefaultConfiguration("localhost", 12345);
    }
}
