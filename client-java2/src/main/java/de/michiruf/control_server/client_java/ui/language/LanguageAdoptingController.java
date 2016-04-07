package de.michiruf.control_server.client_java.ui.language;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Labeled;
import javafx.scene.layout.Pane;

import javax.inject.Inject;

/**
 * @author Michael Ruf
 * @since 2016-04-01
 */
public class LanguageAdoptingController {

    public static final String LANGUAGE_REPLACE_START_TOKEN = "_";

    @Inject
    protected static LanguageProvider languageProvider;

    @SuppressWarnings("unused") // due to FXML inheritance
    @FXML
    private Node root;

    @SuppressWarnings("unused") // due to FXML
    @FXML
    protected void initialize() {
        initializeImpl(root);
    }

    private void initializeImpl(Node node) {
        if (node instanceof Pane) {
            ((Pane) node).getChildren().forEach(this::initializeImpl);
        } else if (node instanceof Labeled) {
            Labeled label = (Labeled) node;
            if (label.getText().startsWith(LANGUAGE_REPLACE_START_TOKEN)) {
                String newValue = languageProvider.getString(label.getText()
                        .substring(LANGUAGE_REPLACE_START_TOKEN.length()));
                if (newValue != null) {
                    label.setText(newValue);
                }
            }
        }
    }
}
