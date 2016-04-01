package de.michiruf.control_server.client_java.ui;

import de.michiruf.control_server.client_java.config.JavaClientConfiguration;
import de.michiruf.control_server.client_java.ui.language.LanguageAdoptingController;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import javax.inject.Inject;

/**
 * @author Michael Ruf
 * @since 2016-04-01
 */
public class DirectConnectionWindowController extends LanguageAdoptingController {

    @Inject
    protected static JavaClientConfiguration configuration;

    @FXML
    private TextField startDirectConnectionHost;

    @FXML
    private TextField startDirectConnectionPort;

    @FXML
    protected void onChange() {
        //
    }

    @FXML
    protected void onConnectClick() {
        //
    }
}
