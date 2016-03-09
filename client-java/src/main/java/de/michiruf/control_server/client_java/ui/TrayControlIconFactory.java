package de.michiruf.control_server.client_java.ui;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.swing.ImageIcon;
import java.awt.TrayIcon;
import java.net.URL;

/**
 * @author Michael Ruf
 * @since 2016-03-08
 */
@Singleton
public class TrayControlIconFactory {

    private final String iconPath;

    @Inject
    public TrayControlIconFactory(@Named("iconPath") String iconPath) {
        this.iconPath = iconPath;
    }

    public TrayIcon getImage() {
        URL iconUri = getClass().getClassLoader().getResource(iconPath);
        ImageIcon imageIcon = new ImageIcon(iconUri, null);
        return new TrayIcon(imageIcon.getImage());
    }
}
