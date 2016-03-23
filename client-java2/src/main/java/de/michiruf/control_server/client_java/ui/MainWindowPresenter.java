package de.michiruf.control_server.client_java.ui;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

/**
 * @author Michael Ruf
 * @since 2016-03-08
 */
@Singleton
public class MainWindowPresenter extends JFrame {

    @Inject
    public MainWindowPresenter(@Named("mainFxml") String fxmlPath) {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // TODO maybe: setResizable(false);
        setSize(300, 500);
        int x = Toolkit.getDefaultToolkit().getScreenSize().width / 2 - getSize().width / 2;
        int y = Toolkit.getDefaultToolkit().getScreenSize().height / 2 - getSize().height / 2;
        setLocation(x, y);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowIconified(WindowEvent e) {
                setVisible(false);
            }
        });

        initializeFx(fxmlPath);
    }

    private void initializeFx(String fxmlPath) {
        JFXPanel jfxPanel = new JFXPanel();
        add(jfxPanel);

        Platform.runLater(() -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(fxmlPath));
                jfxPanel.setScene(new Scene(loader.load()));
            } catch (IOException e) {
                e.printStackTrace(); // TODO Error
            }
        });
    }

    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if (visible) {
            setState(NORMAL);
        }
    }
}
