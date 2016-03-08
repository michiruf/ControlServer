package de.michiruf.control_server.client_java.ui;

import de.michiruf.control_server.client_java.config.JavaClientConfiguration;

import javax.inject.Inject;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Toolkit;

/**
 * @author Michael Ruf
 * @since 2016-03-01
 */
public class SettingsFrame extends JFrame {

    private JavaClientConfiguration configuration;

    @Inject
    public SettingsFrame(JavaClientConfiguration configuration) {
        this.configuration = configuration;

        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setSize(300, 500);
        int x = Toolkit.getDefaultToolkit().getScreenSize().width / 2 - getSize().width / 2;
        int y = Toolkit.getDefaultToolkit().getScreenSize().height / 2 - getSize().height / 2;
        setLocation(x, y);

        drawElements();
    }

    private void drawElements() {
        //setLayout();

        JLabel hostLabel = new JLabel("Host");
//        hostLabel.set
    }
}
