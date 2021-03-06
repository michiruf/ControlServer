package de.michiruf.control_server.server;

import dagger.Module;
import de.michiruf.control_server.server.comm.ServerModule;
import de.michiruf.control_server.server.config.ConfigurationModule;
import de.michiruf.control_server.server.convert.ConvertModule;
import de.michiruf.control_server.server.robot.RobotModule;

/**
 * @author Michael Ruf
 * @since 2015-09-28
 */
@Module(
        includes = {
                ServerModule.class,
                ConfigurationModule.class,
                ConvertModule.class,
                RobotModule.class
        },
        library = true,
        complete = false
)
public class ControlServerModule {
}
