package de.michiruf.control_server.client.control;

import dagger.Module;
import de.michiruf.control_server.client.control.executor.ExecutorModule;

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
}
