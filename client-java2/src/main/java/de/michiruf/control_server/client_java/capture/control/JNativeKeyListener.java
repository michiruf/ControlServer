package de.michiruf.control_server.client_java.capture.control;

import de.michiruf.control_server.client.dispatch.EventDispatcher;
import de.michiruf.control_server.client_java.capture.CaptureFrame;
import de.michiruf.control_server.common.Direction;
import de.michiruf.control_server.common.Event;
import de.michiruf.control_server.common.data.KeyData;
import de.michiruf.control_server.common.data.MouseData;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.NativeInputEvent;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseInputListener;
import org.jnativehook.mouse.NativeMouseWheelEvent;
import org.jnativehook.mouse.NativeMouseWheelListener;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.awt.event.KeyEvent;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author Michael Ruf
 * @since 2016-04-02
 */
@Singleton
public class JNativeKeyListener implements ControlListener, NativeKeyListener, NativeMouseInputListener, NativeMouseWheelListener {

    private EventDispatcher dispatcher;
    private int cancelMask;
    private int cancelKey;
    private Runnable cancelAction;

    @Inject
    public JNativeKeyListener(EventDispatcher dispatcher) {
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
        // We do not need this here
    }

    @Override
    public void start() {
        GlobalScreen.setEventDispatcher(new JNativeVoidDispatchService());
        GlobalScreen.addNativeKeyListener(this);
        GlobalScreen.addNativeMouseListener(this);
        GlobalScreen.addNativeMouseMotionListener(this);
        GlobalScreen.addNativeMouseWheelListener(this);
    }

    @Override
    public void stop() {
        try {
            GlobalScreen.unregisterNativeHook();
        } catch (NativeHookException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent e) {
        if (KeyEvent.getKeyText(cancelKey).equals(NativeKeyEvent.getKeyText(e.getKeyCode()))) {
            cancelAction.run();
        } else {
            dispatchKeyEvent(Direction.DOWN, e);
        }
        consume(e);
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent e) {
        dispatchKeyEvent(Direction.UP, e);
        consume(e);
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent e) {
        dispatchKeyEvent(Direction.UP, e);
        consume(e);
    }

    @Override
    public void nativeMousePressed(NativeMouseEvent e) {
        dispatchMouseEvent(Direction.DOWN, e);
        consume(e);
    }

    @Override
    public void nativeMouseReleased(NativeMouseEvent e) {
        dispatchMouseEvent(Direction.UP, e);
        consume(e);
    }

    @Override
    public void nativeMouseClicked(NativeMouseEvent e) {
        dispatchMouseEvent(Direction.UP, e);
        consume(e);
    }

    @Override
    public void nativeMouseMoved(NativeMouseEvent e) {
        dispatchMouseEvent(Direction.UP, e);
        consume(e);
    }

    @Override
    public void nativeMouseDragged(NativeMouseEvent e) {
        dispatchMouseEvent(Direction.DOWN, e);
        consume(e);
    }

    @Override
    public void nativeMouseWheelMoved(NativeMouseWheelEvent e) {
        dispatchMouseEvent(Direction.DOWN, e);
        consume(e);
    }

    private void dispatchKeyEvent(Direction direction, NativeKeyEvent e) {
        dispatcher.dispatch(new Event(
                direction,
                new KeyData(e.getKeyCode()),
                new Date(System.currentTimeMillis())));
    }

    private void dispatchMouseEvent(Direction direction, NativeMouseEvent e) {
        dispatcher.dispatch(new Event(
                direction,
                new MouseData(e.getX(), e.getY(), e.getButton(), MouseData.CoordinateType.ABSOLUTE),
                new Date(System.currentTimeMillis())));
    }

    private void consume(NativeInputEvent e) {
        try {
            Field f = NativeInputEvent.class.getDeclaredField("reserved");
            f.setAccessible(true);
            f.setShort(e, (short) 0x01);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @SuppressWarnings("NullableProblems")
    private static class JNativeVoidDispatchService extends AbstractExecutorService {

        private boolean running = false;

        public JNativeVoidDispatchService() {
            running = true;
        }

        @Override
        public void shutdown() {
            running = false;
        }

        @Override
        public List<Runnable> shutdownNow() {
            running = false;
            return new ArrayList<>(0);
        }

        @Override
        public boolean isShutdown() {
            return !running;
        }

        @Override
        public boolean isTerminated() {
            return !running;
        }

        @Override
        public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
            return true;
        }

        @Override
        public void execute(Runnable r) {
            r.run();
        }
    }
}
