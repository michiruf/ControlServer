package de.michiruf.control_server.client_java.ui;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import java.awt.Toolkit;
import java.io.IOException;

/**
 * @author Michael Ruf
 * @since 2016-03-08
 */
@Singleton
public class SettingsPresenter extends JFrame {

    @Inject
    public SettingsPresenter(SettingsController controller, @Named("settingsFxml") String settingsFxml) {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // TODO HIDE_ON_CLOSE
        setSize(300, 500);
        int x = Toolkit.getDefaultToolkit().getScreenSize().width / 2 - getSize().width / 2;
        int y = Toolkit.getDefaultToolkit().getScreenSize().height / 2 - getSize().height / 2;
        setLocation(x, y);

        initializeFx(controller, settingsFxml);
    }

    private void initializeFx(SettingsController controller, String settingsFxml) {
        JFXPanel jfxPanel = new JFXPanel();
        add(jfxPanel);

        Platform.runLater(() -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(settingsFxml));
                loader.setController(controller);
                Parent root = loader.load();
                jfxPanel.setScene(new Scene(root));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
