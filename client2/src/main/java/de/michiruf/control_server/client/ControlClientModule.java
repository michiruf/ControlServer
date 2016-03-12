package de.michiruf.control_server.client;

import dagger.Module;
import de.michiruf.control_server.client.comm.CommunicationModule;
import de.michiruf.control_server.client.config.ConfigurationModule;
import de.michiruf.control_server.client.control.ControlModule;
import de.michiruf.control_server.client.convert.ConvertModule;

/**
 * @author Michael Ruf
 * @since 2015-11-14
 */
@Module(
        includes = {
                CommunicationModule.class,
                ConfigurationModule.class,
                ControlModule.class,
                ConvertModule.class
        },
        complete = false,
        library = true
)
public class ControlClientModule {
}
