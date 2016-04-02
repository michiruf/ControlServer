package de.michiruf.control_server.client_java.ui.pages;

import de.michiruf.control_server.client.qualifier.ForDirectConnection;
import de.michiruf.control_server.client_java.config.JavaClientConfiguration;
import de.michiruf.control_server.client_java.ui.FxWindowPresenter;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

import javax.inject.Inject;

/**
 * @author Michael Ruf
 * @since 2016-03-12
 */
public class DirectConnectionController {

    @Inject
    protected static JavaClientConfiguration configuration;

    @Inject
    @ForDirectConnection
    protected static FxWindowPresenter directConnectionWindowPresenter;

    @FXML
    private CheckBox allowDirectConnection;

    @FXML
    private TextField directConnectionPort;

    @SuppressWarnings("unused") // due to FXML
    @FXML
    protected void initialize() {
        allowDirectConnection.setSelected(configuration.isAutoStartEnabled());

        if (configuration.getHostPort() != 0) {
            directConnectionPort.setText(Integer.toString(configuration.getHostPort()));
        }
    }

    @FXML
    protected void onChange() {
        configuration.setAutoStartEnabled(allowDirectConnection.isSelected());

        try {
            int intPort = Integer.parseInt(directConnectionPort.getText());
            if (intPort != 0) {
                configuration.setHostPort(intPort);
            }
        } catch (NumberFormatException e) {
            // Do nothing
        }
    }

    @FXML
    protected void onStartDirectConnectionClick() {
        directConnectionWindowPresenter.setVisible(true);
    }
}
