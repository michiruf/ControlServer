package de.michiruf.control_server.client_java.ui;

import dagger.Module;
import dagger.Provides;
import de.michiruf.control_server.client_java.ui.pages.PagesModule;

import javax.inject.Named;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

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
                MainWindowController.class
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

    @SuppressWarnings("unused")
    @Provides
    @Named("githubUri")
    public URI provideGithubUrl() {
        try {
            return new URL("https://github.com/michiruf").toURI();
        } catch (URISyntaxException | MalformedURLException e) {
            e.printStackTrace(); // TODO Error
        }
        return null;
    }
}
