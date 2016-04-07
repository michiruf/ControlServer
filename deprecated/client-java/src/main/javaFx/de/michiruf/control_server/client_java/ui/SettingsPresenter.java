package de.michiruf.control_server.client_java.ui;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import java.io.IOException;

/**
 * @author Michael Ruf
 * @since 2016-03-08
 */
@Singleton
public class SettingsPresenter {

    private final Stage mainStage;
    private Parent rootNode;

    @Inject
    public SettingsPresenter(Stage mainStage, @Named("settingsFxml") String settingsFxml) {
        this.mainStage = mainStage;

        try {
            rootNode = FXMLLoader.load(getClass().getClassLoader().getResource(settingsFxml));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setVisible(boolean visible) {
        Platform.runLater(() -> {
            if (mainStage.getScene() != null) {
                Scene settingsScene = new Scene(rootNode, 300, 500);
                mainStage.setScene(settingsScene);
            }

            if (visible)
                mainStage.show();
            else
                mainStage.hide();
        });
    }

    public boolean isVisible() {
        return mainStage.isShowing();
    }
}
