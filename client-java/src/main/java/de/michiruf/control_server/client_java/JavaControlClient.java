package de.michiruf.control_server.client_java;

import dagger.ObjectGraph;
import de.michiruf.control_server.client_java.ui.SettingsFrame;
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
            //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            // TODO Just a try:
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            // TODO Try synth?
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            ObjectGraph appGraph = ObjectGraph.create(new JavaControlClientModule());
            TrayControl trayControl = appGraph.get(TrayControl.class);
            trayControl.show();

            // TODO remove this
            appGraph.get(SettingsFrame.class).setVisible(true);
        });
    }
}
