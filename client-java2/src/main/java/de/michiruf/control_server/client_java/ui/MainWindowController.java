package de.michiruf.control_server.client_java.ui;

import javafx.event.ActionEvent;
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

    // TODO delete actionEvent
    @SuppressWarnings("UnusedParameters")
    @FXML
    public void githubClick(ActionEvent actionEvent) {
        try {
            Desktop.getDesktop().browse(new URL("https://github.com/michiruf").toURI());
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
