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
@Singleton
public class SettingsController {

    private JavaClientConfiguration configuration;

    @FXML
    private TextField actiontarget;

    @Inject
    public SettingsController(JavaClientConfiguration configuration) {
        this.configuration = configuration;
    }

    @FXML
    protected void handleSubmitButtonAction(ActionEvent event) {
        actiontarget.setText("Sign in button pressed");
    }
}
