package de.michiruf.control_server.client_java.ui;

import de.michiruf.control_server.client_java.capture.Capture;
import de.michiruf.control_server.client_java.config.JavaClientConfiguration;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.awt.AWTException;
import java.awt.MenuItem;
import java.awt.PopupMenu;
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
    private final Capture capture;
    private final SettingsPresenter settingsPresenter;
    private final JavaClientConfiguration configuration;

    @Inject
    public TrayControl(TrayControlIconFactory iconFactory, Capture capture, SettingsPresenter settingsPresenter,
                       JavaClientConfiguration configuration) {
        this.icon = iconFactory.getImage();
        this.capture = capture;
        this.settingsPresenter = settingsPresenter;
        this.configuration = configuration;

        draw();
    }

    private void draw() {
        icon.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    toggleCapturing();
                }
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

        PopupMenu menu = new PopupMenu();
        icon.setPopupMenu(menu);

        MenuItem item1 = new MenuItem("Capture");
        item1.addActionListener(e -> toggleCapturing());
        menu.add(item1);
        menu.addSeparator();

        MenuItem item2 = new MenuItem("Settings");
        item2.addActionListener(e -> toggleSettings());
        menu.add(item2);

        MenuItem item3 = new MenuItem("Exit");
        item3.addActionListener(e -> System.exit(0));
        menu.add(item3);
    }

    public void show() {
        try {
            SystemTray.getSystemTray().add(icon);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    private void toggleCapturing() {
        if (!configuration.isProperlyConfigured()) {
            toggleSettings();
            return;
        }

        capture.setActive(!capture.isActive());
    }

    private void toggleSettings() {
        settingsPresenter.setVisible(!settingsPresenter.isVisible());
    }
}
