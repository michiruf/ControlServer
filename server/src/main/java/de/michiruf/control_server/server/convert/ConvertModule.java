package de.michiruf.control_server.server.convert;

import com.fasterxml.jackson.databind.ObjectMapper;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

/**
 * @author Michael Ruf
 * @since 2015-11-30
 */
@Module(
        injects = {
                StringEventParser.class
        },
        library = true
)
public class ConvertModule {

    @SuppressWarnings("unused")
    @Provides
    @Singleton
    public ObjectMapper provideObjectMapper() {
        return new ObjectMapper();
    }
}
