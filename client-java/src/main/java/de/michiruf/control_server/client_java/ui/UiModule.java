package de.michiruf.control_server.client_java.ui;

import dagger.Module;
import dagger.Provides;
import de.michiruf.control_server.client_java.JavaControlClient;

import javax.inject.Named;
import javax.inject.Singleton;
import javax.swing.ImageIcon;
import java.awt.Image;

/**
 * @author Michael Ruf
 * @since 2016-03-01
 */
@Module(
        injects = {
                SettingsFrame.class,
                TrayControl.class
        },
        library = true
)
public class UiModule {

    @SuppressWarnings("unused")
    @Provides
    @Named("iconPath")
    public String provideIconPath() {
        return ""; // TODO
    }

    @SuppressWarnings("unused")
    @Provides
    @Singleton
    public Image provideIconImage(@Named("iconPath") String iconPath) {
        ImageIcon imageIcon = new ImageIcon(JavaControlClient.class.getResource(iconPath), null);
        return imageIcon.getImage();
    }
}
