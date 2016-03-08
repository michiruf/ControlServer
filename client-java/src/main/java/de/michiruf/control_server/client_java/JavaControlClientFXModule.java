package de.michiruf.control_server.client_java;

import dagger.Module;
import dagger.Provides;
import de.michiruf.control_server.client.ControlClientModule;
import de.michiruf.control_server.client_java.capture.CaptureModule;
import de.michiruf.control_server.client_java.config.JavaClientConfigurationModule;
import de.michiruf.control_server.client_java.ui.UiModule;
import javafx.stage.Stage;

import javax.inject.Singleton;

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
        library = true // due to FX duplicate
)
public class JavaControlClientFXModule {

    private Stage primaryStage;

    public JavaControlClientFXModule(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @SuppressWarnings("unused")
    @Provides
    @Singleton
    public Stage provideMainStage() {
        return primaryStage;
    }
}
