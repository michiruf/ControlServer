package de.michiruf.control_server.client_java.ui;

import dagger.Module;
import dagger.Provides;

import javax.inject.Named;
import javax.inject.Singleton;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.net.URL;

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
        return "TrayControlIcon.png";
    }

    @SuppressWarnings("unused")
    @Provides
    @Singleton
    public Image provideIconImage(@Named("iconPath") String iconPath) {
        URL iconUri = getClass().getClassLoader().getResource(iconPath);
        ImageIcon imageIcon = new ImageIcon(iconUri, null);
        return imageIcon.getImage();
    }
}
