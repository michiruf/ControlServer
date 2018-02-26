package de.michiruf.control_server.client_java.ui;

import de.michiruf.control_server.client.comm.Client;
import de.michiruf.control_server.client.qualifier.ForDirectConnection;
import de.michiruf.control_server.client_java.capture.Capture;
import de.michiruf.control_server.client_java.config.JavaClientConfiguration;
import de.michiruf.control_server.client_java.ui.language.LanguageAdoptingController;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import javax.inject.Inject;

/**
 * @author Michael Ruf
 * @since 2016-04-01
 */
public class StartDirectConnectionWindowController extends LanguageAdoptingController {

    @Inject
    protected static JavaClientConfiguration configuration;
    @Inject
    @ForDirectConnection
    protected static Client directConnectionClient;
    @Inject
    @ForDirectConnection
    protected static FxWindowPresenter directConnectionWindowPresenter;

    @Inject
    protected static Capture capture;

    @FXML
    private TextField startDirectConnectionHost;
    @FXML
    private TextField startDirectConnectionPort;

    @SuppressWarnings("unused") // due to FXML
    @FXML
    protected void initialize() {
        super.initialize();

        startDirectConnectionHost.setText(configuration.getDirectConnectionClient().getHost());

        if (configuration.getDirectConnectionClient().getPort() != 0) {
            startDirectConnectionPort.setText(Integer.toString(configuration.getDirectConnectionClient().getPort()));
        }
    }

    @FXML
    protected void onChange() {
        configuration.getDirectConnectionClient().setHost(startDirectConnectionHost.getText());

        try {
            int intPort = Integer.parseInt(startDirectConnectionPort.getText());
            if (intPort != 0) {
                configuration.getDirectConnectionClient().setPort(intPort);
            }
        } catch (NumberFormatException e) {
            // Do nothing
        }
    }

    @FXML
    protected void onConnectClick() {
        directConnectionClient.connect();
        capture.start();
        directConnectionWindowPresenter.setVisible(false);
    }
}