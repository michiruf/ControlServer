package de.michiruf.control_server.client_java.ui.language;

import dagger.Module;
import dagger.Provides;

import javax.inject.Named;

/**
 * @author Michael Ruf
 * @since 2016-04-01
 */
@Module(
        injects = {
                LanguageProvider.class
        },
        staticInjections = {
                LanguageAdoptingController.class
        },
        library = true
)
public class LanguageModule {

    @SuppressWarnings("unused")
    @Provides
    @Named("stringsXml")
    public String provideStringsXml() {
        return "language/strings.xml";
    }
}
