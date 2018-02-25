package de.michiruf.control_server.client.auth;

import dagger.Module;

/**
 * @author Michael Ruf
 * @since 2018-02-25
 */
@Module(
        injects = {
                AuthenticationBuilder.class,
                AuthenticationStringParser.class,
                AuthenticationValidator.class,
                StringAuthenticationConverter.class
        },
        library = true,
        complete = false
)
public class AuthenticationModule {
}
