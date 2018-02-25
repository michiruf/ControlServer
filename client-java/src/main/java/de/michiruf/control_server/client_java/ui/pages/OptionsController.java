package de.michiruf.control_server.client_java.ui.pages;

import de.michiruf.control_server.client_java.config.JavaClientConfiguration;
import de.michiruf.control_server.common.data.MouseData;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ToggleGroup;

import javax.inject.Inject;
import java.util.Arrays;

/**
 * @author Michael Ruf
 * @since 2016-04-01
 */
public class OptionsController {

    @Inject
    protected static JavaClientConfiguration configuration;

    @FXML
    private CheckBox allowControllable;

    @FXML
    private ToggleGroup positioningType;

    @SuppressWarnings("unused") // due to FXML
    @FXML
    protected void initialize() {
        allowControllable.setSelected(configuration.isControlListeningEnabled());

//        int positionSelection = Arrays.asList(MouseData.CoordinateType.values())
//                .indexOf(configuration.getCoordinateType());
//        positioningType.getToggles().get(positionSelection).setSelected(true);
    }

    @FXML
    protected void onChange() {
        configuration.setControlListeningEnabled(allowControllable.isSelected());

//        int positionSelection = positioningType.getToggles().indexOf(positioningType.getSelectedToggle());
//        configuration.setCoordinateType(MouseData.CoordinateType.values()[positionSelection]);
    }
}
