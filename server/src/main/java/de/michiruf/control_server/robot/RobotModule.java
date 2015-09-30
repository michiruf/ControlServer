package de.michiruf.control_server.robot;

import dagger.Module;
import dagger.Provides;
import de.michiruf.control_server.robot.controls.ExecutorModule;

import java.awt.AWTException;
import java.awt.Robot;

/**
 * @author Michael Ruf
 * @since 2015-09-28
 */
@Module(
        injects = {
                EventHandler.class,
                StringEventParser.class
        },
        includes = ExecutorModule.class,
        library = true
)
public class RobotModule {

    @SuppressWarnings("unused")
    @Provides
    public Robot provideRobot() {
        try {
            return new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        return null;
    }
}
