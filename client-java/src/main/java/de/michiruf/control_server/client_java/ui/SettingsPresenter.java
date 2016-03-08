package de.michiruf.control_server.client_java.ui;

import de.michiruf.control_server.client_java.config.JavaClientConfiguration;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javax.inject.Inject;
import javax.inject.Named;
import javax.swing.JFrame;
import java.awt.Toolkit;
import java.io.IOException;

/**
 * @author Michael Ruf
 * @since 2016-03-08
 */
public class SettingsPresenter extends JFrame {

    private final JavaClientConfiguration configuration;
    private final String settingsFxml;

    @Inject
    public SettingsPresenter(JavaClientConfiguration configuration, @Named("settingsFxml") String settingsFxml) {
        this.configuration = configuration;
        this.settingsFxml = settingsFxml;

        setSize(300, 500);
        int x = Toolkit.getDefaultToolkit().getScreenSize().width / 2 - getSize().width / 2;
        int y = Toolkit.getDefaultToolkit().getScreenSize().height / 2 - getSize().height / 2;
        setLocation(x, y);

        initializeFx();
    }

    private void initializeFx() {
        JFXPanel jfxPanel = new JFXPanel();
        add(jfxPanel);

        Platform.runLater(() -> {
            try {
                @SuppressWarnings("ConstantConditions")
                Parent rootNode = FXMLLoader.load(getClass().getClassLoader().getResource(settingsFxml));
                jfxPanel.setScene(new Scene(rootNode));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
