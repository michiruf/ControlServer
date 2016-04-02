package de.michiruf.control_server.client_java.capture.control;

import dagger.Module;
import dagger.Provides;
import de.michiruf.control_server.client.dispatch.EventDispatcher;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

import javax.inject.Singleton;

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
            return new JNativeKeyListener(dispatcher);
        } catch (NativeHookException e) {
            e.printStackTrace();
        }

        return new JavaEventsControlListener(dispatcher);
    }
}
