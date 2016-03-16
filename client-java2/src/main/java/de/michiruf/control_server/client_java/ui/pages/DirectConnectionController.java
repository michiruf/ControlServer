package de.michiruf.control_server.client_java.ui.pages;

import de.michiruf.control_server.client_java.config.JavaClientConfiguration;
import de.michiruf.control_server.common.data.MouseData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

import javax.inject.Inject;
import java.util.Arrays;

/**
 * @author Michael Ruf
 * @since 2016-03-12
 */
public class DirectConnectionController {

    @Inject
    protected static JavaClientConfiguration configuration;

    @FXML
    protected void initialize() {
    }
}
