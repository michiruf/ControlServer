package de.michiruf.control_server.client_java;

import dagger.ObjectGraph;
import de.michiruf.control_server.client_java.ui.TrayControl;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 * @author Michael Ruf
 * @since 2016-03-01
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
            TrayControl trayControl = appGraph.get(TrayControl.class);
            trayControl.show();
        });
    }
}
