package de.michiruf.control_server.server;

import dagger.Module;
import dagger.Provides;
import de.michiruf.control_server.server.comm.ServerModule;
import de.michiruf.control_server.server.robot.RobotModule;

import javax.inject.Singleton;

/**
 * @author Michael Ruf
 * @since 2015-09-28
 */
@Module(
        includes = {
                ServerModule.class,
                RobotModule.class
        }
)
public class ControlServerModule {

    @SuppressWarnings("unused")
    @Provides
    @Singleton
    public Configuration provideDefaultConfiguration() {
        return Configuration.DEFAULT;
    }
}
