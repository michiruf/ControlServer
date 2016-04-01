package de.michiruf.control_server.client_java.ui;

import dagger.Module;
import dagger.Provides;
import de.michiruf.control_server.client.qualifier.ForDirectConnection;
import de.michiruf.control_server.client_java.ui.language.LanguageModule;
import de.michiruf.control_server.client_java.ui.pages.PagesModule;
import de.michiruf.control_server.client_java.ui.tray.TrayModule;

import javax.inject.Singleton;
import javax.swing.WindowConstants;

/**
 * @author Michael Ruf
 * @since 2016-03-01
 */
@Module(
        includes = {
                LanguageModule.class,
                PagesModule.class,
                TrayModule.class
        },
        injects = {
                FxWindowPresenter.class
        },
        staticInjections = {
                DirectConnectionWindowController.class,
        },
        library = true,
        complete = false
)
public class UiModule {

    @SuppressWarnings("unused")
    @Provides
    @Singleton
    public FxWindowPresenter provideMainWindowPresenter() {
        return new FxWindowPresenter("MainWindow.fxml", true) {{
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        }};
    }

    @SuppressWarnings("unused")
    @Provides
    @Singleton
    @ForDirectConnection
    public FxWindowPresenter provideDirectConnectionWindowPresenter() {
        return new FxWindowPresenter("DirectConnectionWindow.fxml");
    }
}
