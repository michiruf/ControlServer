package de.michiruf.control_server.client_java.ui.pages;

import de.michiruf.control_server.client.comm.DirectConnectionServer;
import de.michiruf.control_server.client.qualifier.ForDirectConnection;
import de.michiruf.control_server.client_java.config.JavaClientConfiguration;
import de.michiruf.control_server.client_java.config.JavaClientDirectConnectionServerConfiguration;
import de.michiruf.control_server.client_java.ui.FxWindowPresenter;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

import javax.inject.Inject;

/**
 * @author Michael Ruf
 * @since 2016-03-12
 */
public class DirectConnectionServerController {

    @Inject
    protected static JavaClientConfiguration configuration;
    @Inject
    protected static DirectConnectionServer server;
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
        allowDirectConnection.setSelected(configuration.getDirectConnectionServer().isHostAutoStartEnabled());

        if (configuration.getDirectConnectionServer().getHostPort() != 0) {
            directConnectionPort.setText(Integer.toString(configuration.getDirectConnectionServer().getHostPort()));
        }
    }

    @FXML
    protected void onChange() {
        configuration.getDirectConnectionServer().setHostAutoStartEnabled(allowDirectConnection.isSelected());
        if (allowDirectConnection.isSelected() && !server.isRunning()) {
            server.start();
        } else if (!allowDirectConnection.isSelected() && server.isRunning()) {
            server.stop();
        }

        try {
            int intPort = Integer.parseInt(directConnectionPort.getText());
            if (intPort != 0) {
                configuration.getDirectConnectionServer().setHostPort(intPort);
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
