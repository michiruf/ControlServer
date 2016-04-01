package de.michiruf.control_server.client_java.ui;

import dagger.Module;
import dagger.Provides;
import de.michiruf.control_server.client_java.ui.pages.PagesModule;

import javax.inject.Named;

/**
 * @author Michael Ruf
 * @since 2016-03-01
 */
@Module(
        includes = {
                PagesModule.class
        },
        injects = {
                LanguageProvider.class,
                MainWindowPresenter.class,
                TrayControl.class,
                TrayControlIconFactory.class
        },
        staticInjections = {
                LanguageAdoptingController.class
        },
        library = true,
        complete = false
)
public class UiModule {

    @SuppressWarnings("unused")
    @Provides
    @Named("iconPath")
    public String provideIconPath() {
        return "TrayControlIcon.png";
    }

    @SuppressWarnings("unused")
    @Provides
    @Named("stringsXml")
    public String provideStringsXml() {
        return "language/strings.xml";
    }

    @SuppressWarnings("unused")
    @Provides
    @Named("mainFxml")
    public String provideMainFxml() {
        return "MainWindow.fxml";
    }
}
