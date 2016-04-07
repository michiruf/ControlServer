package de.michiruf.control_server.server.robot;

import dagger.Module;
import dagger.Provides;
import de.michiruf.control_server.server.robot.controls.ExecutorModule;

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
public class RobotModule {

    @SuppressWarnings("unused")
    @Provides
    @Singleton
    public Robot provideRobot() {
        try {
            return new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        return null;
    }
}
