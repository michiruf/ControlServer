package de.michiruf.control_server.client_java.ui.pages;

import de.michiruf.control_server.Constants;
import de.michiruf.control_server.client_java.config.JavaClientConfiguration;
import de.michiruf.control_server.client_java.config.JavaClientDirectConnectionClientConfiguration;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javax.inject.Inject;
import java.awt.Desktop;
import java.io.IOException;

/**
 * @author Michael Ruf
 * @since 2016-03-12
 */
public class LoginController {

    // TODO Remove, must be there because of static injections... wtf
    @Inject
    protected static JavaClientConfiguration configuration;

    @FXML
    private TextField usernameInput;
    @FXML
    private PasswordField passwordInput;

    @FXML
    protected void onLoginClick(ActionEvent event) {
        System.out.println(event != null ? "nonnull" : "null");
        // TODO
    }

    @FXML
    protected void onRegisterClick() {
        try {
            Desktop.getDesktop().browse(Constants.REGISTRATION_URI);
        } catch (IOException e) {
            e.printStackTrace(); // TODO Error
        }
    }

    @FXML
    protected void onForgotPasswordClick() {
        try {
            Desktop.getDesktop().browse(Constants.FORGOT_PASSWORD_URI);
        } catch (IOException e) {
            e.printStackTrace(); // TODO Error
        }
    }
}
