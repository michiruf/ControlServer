package de.michiruf.control_server.client;

import dagger.Module;
import de.michiruf.control_server.client.comm.ClientModule;

/**
 * @author Michael Ruf
 * @since 2015-11-14
 */
@Module(
        includes = {
                ClientModule.class//,
                // TODO? // DispatcherModule.class
        }
)
public class ControlClientModule {
}
