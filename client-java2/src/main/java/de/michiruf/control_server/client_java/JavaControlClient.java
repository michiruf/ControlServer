package de.michiruf.control_server.client_java;

import dagger.ObjectGraph;
import de.michiruf.control_server.client_java.ui.MainWindowPresenter;
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
            // TODO Try synth?
            // should not change anything when using java fx inside
        } catch (Exception e) {
            e.printStackTrace(); // TODO Error (only as logging)
        }

        SwingUtilities.invokeLater(() -> {
            ObjectGraph appGraph = ObjectGraph.create(new JavaControlClientModule());
			// TODO appGraph.injectStatics();
			// Without this the controllers should fail
		
            TrayControl trayControl = appGraph.get(TrayControl.class);
            trayControl.show();

            // TODO remove
            appGraph.get(MainWindowPresenter.class).setVisible(true);
        });
    }
}
