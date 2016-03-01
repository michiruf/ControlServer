package de.michiruf.control_server.client_java;

import dagger.ObjectGraph;
import de.michiruf.control_server.client.comm.Client;
import de.michiruf.control_server.client_java.ui.TrayMenu;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 * @author Michael Ruf
 * @since 2015-11-14
 */
public class JavaControlClient {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            ObjectGraph appGraph = ObjectGraph.create(new JavaControlClientModule());
            TrayMenu trayMenu = appGraph.get(TrayMenu.class);
            trayMenu.show();
        });
    }
}
