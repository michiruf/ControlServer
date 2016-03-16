package de.michiruf.control_server.client.control;

import dagger.Module;
import dagger.Provides;
import de.michiruf.control_server.client.control.executor.ExecutorModule;

import javax.inject.Singleton;
import java.awt.AWTException;
import java.awt.Robot;

/**
 * @author Michael Ruf
 * @since 2015-09-28
 */
@Module(
        includes = {
                ExecutorModule.class
        },
        injects = {
                EventHandler.class
        },
        library = true,
        complete = false
)
public class ControlModule {

    @SuppressWarnings("unused")
    @Provides
    @Singleton
    public Robot provideRobot() {
        try {
            return new Robot();
        } catch (AWTException e) {
            e.printStackTrace(); // TODO Error
        }
        return null;
    }
}
