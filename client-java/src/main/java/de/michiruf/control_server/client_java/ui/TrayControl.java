package de.michiruf.control_server.client_java.ui;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.awt.AWTException;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * @author Michael Ruf
 * @since 2016-03-01
 */
@Singleton
public class TrayControl {

    private final TrayIcon icon;

    @Inject
    public TrayControl(Image iconImage, SettingsFrame settingsFrame) {
        icon = new TrayIcon(iconImage);
        icon.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                settingsFrame.setVisible(!settingsFrame.isShowing());
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
    }

    public void show() {
        try {
            SystemTray.getSystemTray().add(icon);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public void hide() {
        SystemTray.getSystemTray().remove(icon);
    }
}
