package de.michiruf.control_server.client_java.ui;

import javafx.fxml.FXML;

import javax.inject.Inject;
import javax.inject.Named;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;

/**
 * @author Michael Ruf
 * @since 2016-03-01
 */
public class MainWindowController {

    @Inject
    @Named("githubUri")
    protected static URI githubUri;

    @FXML
    public void onGithubClick() {
        try {
            Desktop.getDesktop().browse(githubUri);
        } catch (IOException e) {
            e.printStackTrace(); // TODO Error
        }
    }
}
