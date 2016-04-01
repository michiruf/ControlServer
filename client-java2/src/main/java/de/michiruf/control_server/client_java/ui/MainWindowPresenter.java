package de.michiruf.control_server.client_java.ui;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
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

    private boolean initialized = false;
    private boolean visibleRequest = false;

    @Inject
    public MainWindowPresenter(@Named("mainFxml") String fxmlPath) {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

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
        setContentPane(jfxPanel);

        Platform.runLater(() -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(fxmlPath));
                Scene scene = new Scene(loader.load());
                // NOTE we may can get the root element in the controller by fxml?
                ((MainWindowController) loader.getController()).setRootNode(scene.getRoot());
                jfxPanel.setScene(scene);
                jfxPanel.setSize((int) scene.getWidth(), (int) scene.getHeight());
                pack();
            } catch (IOException e) {
                setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                setContentPane(new JLabel("Error initializing java fx. See logging for more information.") {{
                    setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
                }});
                pack();

                e.printStackTrace(); // TODO Error
            } finally {
                initialized();
            }
        });
    }

    private void initialized() {
        int x = Toolkit.getDefaultToolkit().getScreenSize().width / 2 - getSize().width / 2;
        int y = Toolkit.getDefaultToolkit().getScreenSize().height / 2 - getSize().height / 2;
        setLocation(x, y);

        initialized = true;
        if (visibleRequest) {
            setVisible(true);
        }
    }

    @Override
    public void setVisible(boolean visible) {
        if (!initialized) {
            visibleRequest = true;
            return;
        }

        super.setVisible(visible);
        if (visible) {
            setState(NORMAL);
        }
    }
}
