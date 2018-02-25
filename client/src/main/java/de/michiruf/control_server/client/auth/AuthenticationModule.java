package de.michiruf.control_server.client.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import dagger.Module;
import dagger.Provides;
import de.michiruf.control_server.client.qualifier.ForDirectConnection;
import de.michiruf.control_server.client.qualifier.ForWebServer;

import javax.inject.Singleton;

/**
 * @author Michael Ruf
 * @since 2018-02-25
 */
@Module(
        library = true,
        complete = false
)
public class AuthenticationModule {

    @SuppressWarnings("unused")
    @Provides
    @Singleton
    @ForWebServer
    public AuthenticationProvider provideWebServerAuthenticationProvider(ObjectMapper objectMapper) {
        return new WebServerAuthenticationProvider();
    }

    @SuppressWarnings("unused")
    @Provides
    @Singleton
    @ForDirectConnection
    public AuthenticationProvider provideDirectConnectionAuthenticationProvider() {
        return new DirectConnectionAuthenticationProvider();
    }
}
