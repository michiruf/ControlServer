package de.michiruf.control_server.client_java.capture;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 * @author Michael Ruf
 * @since 2016-03-01
 */
@Singleton
public class CaptureFrame extends JFrame {

    @Inject
    public CaptureFrame(KeyCaptureListener keyCaptureListener, MouseCaptureListener mouseCaptureListener) {
        setUndecorated(true);
        setAlwaysOnTop(true);
        setBackground(new Color(0, 0, 0, 0));
        setLocationRelativeTo(null);
        setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
        setContentPane(new TranslucentPane());

        addKeyListener(keyCaptureListener);
        addMouseListener(mouseCaptureListener);
    }

    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);

        if (visible) {
            requestFocus();
        }
    }

    public static class TranslucentPane extends JPanel {

        public TranslucentPane() {
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setColor(new Color(0, 0, 0, 100));
            g2d.fillRect(0, 0, getWidth(), getHeight());
        }
    }
}
