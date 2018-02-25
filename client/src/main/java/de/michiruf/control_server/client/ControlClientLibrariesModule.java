package de.michiruf.control_server.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

/**
 * @author Michael Ruf
 * @since 2018-02-25
 */
@Module(
        library = true
)
public class ControlClientLibrariesModule {

    @SuppressWarnings("unused")
    @Provides
    @Singleton
    public ObjectMapper provideObjectMapper() {
        return new ObjectMapper();
    }
}
