package de.michiruf.control_server.client_java.capture.control;

import de.michiruf.control_server.client.event.EventDispatcher;
import de.michiruf.control_server.client_java.capture.CaptureFrame;
import de.michiruf.control_server.common.event.Direction;
import de.michiruf.control_server.common.event.Event;
import de.michiruf.control_server.common.event.KeyData;
import de.michiruf.control_server.common.event.MouseData;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeInputEvent;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseInputListener;
import org.jnativehook.mouse.NativeMouseWheelEvent;
import org.jnativehook.mouse.NativeMouseWheelListener;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author Michael Ruf
 * @see <a href="https://github.com/kwhat/jnativehook/wiki/ConsumingEvents">Github wiki</a>
 * @since 2016-04-02
 */
@Singleton
public class JNativeHookKeyListener implements ControlListener, NativeKeyListener, NativeMouseInputListener, NativeMouseWheelListener {

    private EventDispatcher dispatcher;
    private int cancelMask;
    private int cancelKey;
    private Runnable cancelAction;

    private int startMousePositionX;
    private int startMousePositionY;

    @Inject
    public JNativeHookKeyListener(EventDispatcher dispatcher) {
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

        Point p = MouseInfo.getPointerInfo().getLocation();
        startMousePositionX = (int) p.getX();
        startMousePositionY = (int) p.getY();
    }

    @Override
    public void stop() {
        GlobalScreen.removeNativeKeyListener(this);
        GlobalScreen.removeNativeMouseListener(this);
        GlobalScreen.removeNativeMouseMotionListener(this);
        GlobalScreen.removeNativeMouseWheelListener(this);
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent e) {
        int keyCode = resolveKeyCodeForNativeKeyCode(e.getKeyCode());
        if (cancelKey == keyCode) {
            cancelAction.run();
        } else {
            dispatchKeyEvent(Direction.DOWN, keyCode);
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
        dispatchKeyEvent(direction, e.getKeyCode());
    }

    private void dispatchKeyEvent(Direction direction, int keyCode) {
        dispatcher.dispatch(new Event(
                direction,
                new KeyData(keyCode),
                new Date(System.currentTimeMillis())));
    }

    private void dispatchMouseEvent(Direction direction, NativeMouseEvent e) {
        dispatcher.dispatch(new Event(
                direction,
                new MouseData(startMousePositionX - e.getX(), startMousePositionY - e.getY(),
                        e.getButton(), MouseData.CoordinateType.RELATIVE),
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

    private int resolveKeyCodeForNativeKeyCode(int nativeKeyCode) {
        return resolveKeyCodeForFieldName(resolveKeyFieldNameForNativeKeyCode(nativeKeyCode));
    }

    private String resolveKeyFieldNameForNativeKeyCode(int nativeKeyCode) {
        for (Field f : NativeKeyEvent.class.getDeclaredFields()) {
            if (Modifier.isStatic(f.getModifiers()) && Modifier.isPublic(f.getModifiers())) {
                if (!f.getName().startsWith("VC_")) {
                    continue;
                }
                try {
                    if (f.getInt(null) == nativeKeyCode) {
                        return f.getName().replace("VC_", "VK_");
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    private int resolveKeyCodeForFieldName(String fieldName) {
        if (fieldName != null) {
            for (Field f : KeyEvent.class.getDeclaredFields()) {
                if (Modifier.isStatic(f.getModifiers()) && Modifier.isPublic(f.getModifiers())) {
                    if (fieldName.equals(f.getName())) {
                        try {
                            return f.getInt(null);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return KeyEvent.VK_UNDEFINED;
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
            // This is essential to be not asynchronous!
            r.run();
        }
    }
}
