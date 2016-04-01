package de.michiruf.control_server.client_java.ui;

import de.michiruf.control_server.Constants;
import javafx.fxml.FXML;

import java.awt.Desktop;
import java.io.IOException;

/**
 * @author Michael Ruf
 * @since 2016-03-01
 */
public class MainWindowController extends LanguageAdoptingController {

    @FXML
    public void onGithubClick() {
        try {
            Desktop.getDesktop().browse(Constants.GITHUB_URI);
        } catch (IOException e) {
            e.printStackTrace(); // TODO Error
        }
    }
}
