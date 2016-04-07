package de.michiruf.control_server.client.event;

import com.fasterxml.jackson.databind.ObjectMapper;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

/**
 * @author Michael Ruf
 * @since 2015-09-28
 */
@Module(
        injects = {
                EventExecutionHandler.class,
                EventDispatcher.class,
                EventStringConverter.class,
                StringEventParser.class
        },
        library = true,
        complete = false
)
public class EventModule {

    @SuppressWarnings("unused")
    @Provides
    @Singleton
    public ObjectMapper provideObjectMapper() {
        return new ObjectMapper();
    }
}
