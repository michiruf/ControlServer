package de.michiruf.control_server.client.dispatch;

import dagger.Module;

/**
 * @author Michael Ruf
 * @since 2015-11-29
 */
@Module(
        injects = {
                EventDispatcher.class
        },
        library = true
)
public class DispatchModule {
}
