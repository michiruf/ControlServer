package de.michiruf.control_server.client_java.ui;

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

    private boolean initialized = false;
    private Node rootNode;

    @SuppressWarnings("unused") // due to FXML
    @FXML
    protected void initialize() {
        initialized = true;
        if (rootNode != null) {
            initializeImpl(rootNode);
        }
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

    public void setRootNode(Node rootNode) {
        this.rootNode = rootNode;
        if (initialized) {
            initialize();
        }
    }
}
