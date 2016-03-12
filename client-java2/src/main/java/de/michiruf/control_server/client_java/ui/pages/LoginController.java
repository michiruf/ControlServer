package de.michiruf.control_server.client_java.ui.pages;

import de.michiruf.control_server.client_java.config.JavaClientConfiguration;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
    private TextField hostInput;

    @FXML
    private TextField portInput;

    @FXML
    protected void onLogin(ActionEvent event) {
        System.out.println(event != null ? "nonnull" : "null");
    }
}
