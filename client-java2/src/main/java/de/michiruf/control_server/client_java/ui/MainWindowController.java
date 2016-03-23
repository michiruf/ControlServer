package de.michiruf.control_server.client_java.ui;

import javafx.fxml.FXML;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * @author Michael Ruf
 * @since 2016-03-01
 */
public class MainWindowController {

    @FXML
    public void githubClick() {
        try {
            Desktop.getDesktop().browse(new URL("https://github.com/michiruf").toURI());
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace(); // TODO Error
        }
    }
}
