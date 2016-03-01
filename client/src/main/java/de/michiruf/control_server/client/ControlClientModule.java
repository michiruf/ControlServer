package de.michiruf.control_server.client;

import dagger.Module;
import dagger.Provides;
import de.michiruf.control_server.client.comm.ClientModule;
import de.michiruf.control_server.client.config.Configuration;
import de.michiruf.control_server.client.convert.ConvertModule;
import de.michiruf.control_server.client.dispatch.DispatchModule;

import javax.inject.Singleton;

/**
 * @author Michael Ruf
 * @since 2015-11-14
 */
@Module(
        includes = {
                ClientModule.class,
                ConvertModule.class,
                DispatchModule.class
        },
        complete = false
)
public class ControlClientModule {
}
