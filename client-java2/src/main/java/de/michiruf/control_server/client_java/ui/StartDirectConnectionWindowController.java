package de.michiruf.control_server.client_java.ui;

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
// TODO we could may embed this into the main stage?
public class StartDirectConnectionWindowController extends LanguageAdoptingController {

    @Inject
    protected static JavaClientConfiguration configuration;

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

        startDirectConnectionHost.setText(configuration.getHost());

        if (configuration.getPort() != 0) {
            startDirectConnectionPort.setText(Integer.toString(configuration.getPort()));
        }
    }

    @FXML
    protected void onChange() {
        configuration.setHost(startDirectConnectionHost.getText());

        try {
            int intPort = Integer.parseInt(startDirectConnectionPort.getText());
            if (intPort != 0) {
                configuration.setPort(intPort);
            }
        } catch (NumberFormatException e) {
            // Do nothing
        }
    }

    @FXML
    protected void onConnectClick() {
        capture.start();
    }
}
