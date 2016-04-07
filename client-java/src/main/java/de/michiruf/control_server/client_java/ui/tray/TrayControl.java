package de.michiruf.control_server.client_java.ui.tray;

import de.michiruf.control_server.client_java.ui.FxWindowPresenter;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.awt.AWTException;
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
    private final FxWindowPresenter mainWindowPresenter;

    @Inject
    public TrayControl(TrayControlIconFactory iconFactory, FxWindowPresenter mainWindowPresenter) {
        this.icon = iconFactory.getImage();
        this.mainWindowPresenter = mainWindowPresenter;
        initialize();
    }

    private void initialize() {
        icon.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                toggleMainWindow();
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
            e.printStackTrace(); // TODO Error
        }
    }

    private void toggleMainWindow() {
        mainWindowPresenter.setVisible(!mainWindowPresenter.isVisible());
    }
}
