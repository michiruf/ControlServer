package de.michiruf.control_server.client_java.ui.pages;

import de.michiruf.control_server.client_java.config.JavaClientConfiguration;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javax.inject.Inject;

/**
 * @author Michael Ruf
 * @since 2016-03-12
 */
public class LoginController {

    @Inject
    protected static JavaClientConfiguration configuration;

    @FXML
    private TextField usernameInput;

    @FXML
    private PasswordField passwordInput;

    @FXML
    protected void onLoginClick(ActionEvent event) {
        System.out.println(event != null ? "nonnull" : "null");
    }

    @FXML
    protected void onRegisterClick(ActionEvent actionEvent) {
        // TODO
    }

    @FXML
    protected void onForgotPasswordClick(ActionEvent actionEvent) {
        // TODO
    }
}
