package de.michiruf.control_server.client;

import dagger.Module;
import de.michiruf.control_server.client.comm.CommunicationModule;
import de.michiruf.control_server.client.config.ConfigurationModule;
import de.michiruf.control_server.client.convert.ConvertModule;
import de.michiruf.control_server.client.dispatch.DispatchModule;
import de.michiruf.control_server.client.robot.RobotModule;

/**
 * @author Michael Ruf
 * @since 2015-11-14
 */
@Module(
        includes = {
                CommunicationModule.class,
                ConfigurationModule.class,
                ConvertModule.class,
                DispatchModule.class,
                RobotModule.class
        },
        complete = false,
        library = true
)
public class ControlClientModule {
}
