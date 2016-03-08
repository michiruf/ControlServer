package de.michiruf.control_server.client_java.ui;

import de.michiruf.control_server.client_java.config.JavaClientConfiguration;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javax.inject.Inject;
import javax.inject.Named;
import java.awt.Toolkit;
import java.io.IOException;

/**
 * @author Michael Ruf
 * @since 2016-03-08
 */
public class SettingsPP extends JFXPanel {

    private final JavaClientConfiguration configuration;

    @Inject
    public SettingsPP(JavaClientConfiguration configuration, @Named("settingsFxml") String settingsFxml) {
        this.configuration = configuration;

        int x = Toolkit.getDefaultToolkit().getScreenSize().width / 2 - getSize().width / 2;
        int y = Toolkit.getDefaultToolkit().getScreenSize().height / 2 - getSize().height / 2;
        setBounds(x, y, 300, 500);

        initializeFx(settingsFxml);
    }

    private void initializeFx(String settingsFxml) {
        Platform.runLater(() -> {
            try {
                Parent rootNode = FXMLLoader.load(getClass().getClassLoader().getResource(settingsFxml));
                setScene(new Scene(rootNode));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
