package de.michiruf.control_server.client.control;

import dagger.Module;
import dagger.Provides;
import de.michiruf.control_server.client.ErrorHandler;

import javax.inject.Singleton;
import java.awt.AWTException;
import java.awt.Robot;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Michael Ruf
 * @since 2015-09-28
 */
@Module(
        injects = {
                KeyControlExecutor.class,
                MouseControlExecutor.class,
                Robot.class
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
            ErrorHandler.handle(e);
        }
        return null;
    }

    @SuppressWarnings("unused")
    @Provides
    @Singleton
    public List<ControlExecutor> provideControlExecutors(KeyControlExecutor keyControlExecutor,
                                                         MouseControlExecutor mouseControlExecutor) {
        List<ControlExecutor> all = new ArrayList<>();
        all.add(keyControlExecutor);
        all.add(mouseControlExecutor);
        return all;
    }
}
