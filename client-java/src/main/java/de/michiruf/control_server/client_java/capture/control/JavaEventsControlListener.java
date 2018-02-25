package de.michiruf.control_server.client_java.capture.control;

import de.michiruf.control_server.client.event.EventDispatcher;
import de.michiruf.control_server.client_java.capture.CaptureFrame;
import de.michiruf.control_server.common.event.Direction;
import de.michiruf.control_server.common.event.Event;
import de.michiruf.control_server.common.event.KeyData;
import de.michiruf.control_server.common.event.MouseData;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.Arrays;
import java.util.Date;

/**
 * @author Michael Ruf
 * @since 2016-04-02
 */
@Singleton
public class JavaEventsControlListener implements ControlListener {

    private final EventDispatcher dispatcher;
    private int cancelMask;
    private int cancelKey;
    private Runnable cancelAction;
    private CaptureFrame captureFrame;

    @Inject
    public JavaEventsControlListener(EventDispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    @Override
    public void setCancelParameters(int mask, int key, Runnable cancel) {
        cancelMask = mask;
        cancelKey = key;
        cancelAction = cancel;
    }

    @Override
    public void setMayRequiredCaptureFrame(CaptureFrame captureFrame) {
        this.captureFrame = captureFrame;
    }

    @Override
    public void start() {
        captureFrame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.isControlDown() && e.getKeyCode() == cancelKey) {
                    cancelAction.run();
                    e.consume();
                }
            }
        });
        captureFrame.addKeyListener(new KeyCaptureListener());
        captureFrame.addMouseListener(new MouseCaptureListener());
        captureFrame.addMouseMotionListener(new MouseCaptureListener());
        captureFrame.addMouseWheelListener(new MouseCaptureListener());
    }

    @Override
    public void stop() {
        Arrays.asList(captureFrame.getKeyListeners()).forEach(captureFrame::removeKeyListener);
        Arrays.asList(captureFrame.getMouseListeners()).forEach(captureFrame::removeMouseListener);
        Arrays.asList(captureFrame.getMouseMotionListeners()).forEach(captureFrame::removeMouseMotionListener);
        Arrays.asList(captureFrame.getMouseWheelListeners()).forEach(captureFrame::removeMouseWheelListener);
    }

    private void dispatchKeyEvent(Direction direction, KeyEvent e) {
        dispatcher.dispatch(new Event(
                direction,
                new KeyData(e.getKeyCode()),
                new Date(System.currentTimeMillis())));
    }

    private void dispatchMouseEvent(Direction direction, MouseEvent e) {
        dispatcher.dispatch(new Event(
                direction,
                new MouseData(e.getX(), e.getY(), e.getButton(), MouseData.CoordinateType.ABSOLUTE),
                new Date(System.currentTimeMillis())));
    }

    private class KeyCaptureListener extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            System.out.println("keyPressed");
            dispatchKeyEvent(Direction.DOWN, e);
            e.consume();
        }

        @Override
        public void keyReleased(KeyEvent e) {
            System.out.println("keyReleased");
            dispatchKeyEvent(Direction.UP, e);
            e.consume();
        }
    }

    private class MouseCaptureListener extends MouseAdapter {

        @Override
        public void mousePressed(MouseEvent e) {
            System.out.println("mousePressed");
            dispatchMouseEvent(Direction.DOWN, e);
            e.consume();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            System.out.println("mouseReleased");
            dispatchMouseEvent(Direction.UP, e);
            e.consume();
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            System.out.println("mouseMoved");
            dispatchMouseEvent(Direction.UP, e);
            e.consume();
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            System.out.println("mouseDragged");
            dispatchMouseEvent(Direction.DOWN, e);
            e.consume();
        }

        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {
            System.out.println("mouseWheelMoved");
            dispatchMouseEvent(Direction.DOWN, e);
            e.consume();
        }
    }
}
