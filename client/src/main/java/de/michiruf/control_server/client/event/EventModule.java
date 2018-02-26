package de.michiruf.control_server.client.event;

import dagger.Module;

/**
 * @author Michael Ruf
 * @since 2015-09-28
 */
@Module(
        injects = {
                EventDispatcher.class,
                EventExecutionHandler.class,
                EventStringConverter.class,
                StringEventParser.class
        },
        library = true,
        complete = false
)
public class EventModule {
}
