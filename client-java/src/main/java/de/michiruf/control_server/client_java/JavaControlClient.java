package de.michiruf.control_server.client_java;

import dagger.ObjectGraph;
import de.michiruf.control_server.client.comm.Client;
import de.michiruf.control_server.client.comm.DirectConnectionServer;
import de.michiruf.control_server.client.qualifier.ForWebServer;
import de.michiruf.control_server.client_java.ui.FxWindowPresenter;
import de.michiruf.control_server.client_java.ui.tray.TrayControl;

import javax.inject.Inject;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 * @author Michael Ruf
 * @since 2016-03-01
 */
public class JavaControlClient {

    @Inject
    protected TrayControl trayControl;

    @Inject
    @ForWebServer
    protected Client webServerClient;

    @Inject
    protected DirectConnectionServer server;

    public JavaControlClient() {
        ObjectGraph appGraph = ObjectGraph.create(new JavaControlClientModule());
        appGraph.injectStatics();
        appGraph.inject(this);


        // TODO remove
        appGraph.get(FxWindowPresenter.class).setVisible(true);
    }

    public void init() {
        trayControl.show();

        webServerClient.connect();
        server.startIfAutoStartEnabled();
    }

    public static void main(String[] args) {
        try {
            // This should not change anything when using java fx inside
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            // Nothing to do
        }
        SwingUtilities.invokeLater(() -> new JavaControlClient().init());
    }
}
