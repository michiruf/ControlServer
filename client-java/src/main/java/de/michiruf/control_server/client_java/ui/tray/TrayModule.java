package de.michiruf.control_server.client_java.ui.tray;

import dagger.Module;
import dagger.Provides;

import javax.inject.Named;

/**
 * @author Michael Ruf
 * @since 2016-04-01
 */
@Module(
        injects = {
                TrayControl.class,
                TrayControlIconFactory.class
        },
        library = true,
        complete = false
)
public class TrayModule {

    @SuppressWarnings("unused")
    @Provides
    @Named("iconPath")
    public String provideIconPath() {
        return "TrayControlIcon.png";
    }
}
