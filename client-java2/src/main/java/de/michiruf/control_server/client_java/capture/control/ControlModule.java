package de.michiruf.control_server.client_java.capture.control;

import dagger.Module;
import dagger.Provides;
import de.michiruf.control_server.client.event.EventDispatcher;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

import javax.inject.Singleton;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Michael Ruf
 * @since 2016-04-02
 */
@Module(
        injects = {
                ControlListener.class
        },
        library = true,
        complete = false
)
public class ControlModule {

    @SuppressWarnings("unused")
    @Provides
    @Singleton
    public ControlListener provideControlListener(EventDispatcher dispatcher) {
        try {
            GlobalScreen.registerNativeHook();
            Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
            logger.setLevel(Level.WARNING);
            logger.setUseParentHandlers(false);
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                try {
                    GlobalScreen.unregisterNativeHook();
                } catch (NativeHookException e) {
                    // Do nothing
                }
            }));
            return new JNativeHookKeyListener(dispatcher);
        } catch (NativeHookException e) {
            e.printStackTrace();
        }

        return new JavaEventsControlListener(dispatcher);
    }
}
