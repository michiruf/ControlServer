package de.michiruf.control_server.client_java.ui;

import de.michiruf.control_server.client_java.config.JavaClientConfiguration;
import de.michiruf.control_server.common.data.MouseData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Arrays;

/**
 * @author Michael Ruf
 * @since 2016-03-01
 */
// TODO we need an onLoaded callback to prepare initial values
@SuppressWarnings("unused") // due to FXML with unbound controller
@Singleton
public class SettingsController {

    private JavaClientConfiguration configuration;

    @FXML
    private TextField hostInput;

    @FXML
    private TextField portInput;

    @FXML
    private ToggleGroup positionToggleGroup;

    @Inject
    public SettingsController(JavaClientConfiguration configuration) {
        this.configuration = configuration;
    }

    @FXML
    protected void initialize() {
        hostInput.setText(configuration.getHost());

        if (configuration.getPort() != 0) {
            portInput.setText(Integer.toString(configuration.getPort()));
        }

        int positionSelection = Arrays.asList(MouseData.CoordinateType.values())
                .indexOf(configuration.getCoordinateType());
        positionToggleGroup.getToggles().get(positionSelection).setSelected(true);
    }

    @FXML
    protected void onSave(ActionEvent event) {
        configuration.setHost(hostInput.getText());

        try {
            int port = Integer.parseInt(portInput.getText());
            if (port != 0) {
                configuration.setPort(port);
            }
        } catch (NumberFormatException e) {
            // Do nothing
        }

        int positionSelection = positionToggleGroup.getToggles().indexOf(positionToggleGroup.getSelectedToggle());
        configuration.setCoordinateType(MouseData.CoordinateType.values()[positionSelection]);
    }
}
