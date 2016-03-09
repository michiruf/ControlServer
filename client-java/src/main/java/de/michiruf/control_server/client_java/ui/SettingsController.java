package de.michiruf.control_server.client_java.ui;

import de.michiruf.control_server.client_java.config.JavaClientConfiguration;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import javax.inject.Inject;
import javax.inject.Singleton;

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

    @Inject
    public SettingsController(JavaClientConfiguration configuration) {
        this.configuration = configuration;
    }

    @FXML
    protected void onSave(ActionEvent event) {
        hostInput.setText("Sign in button pressed");
        // TODO this is up next with initial values
    }
}
