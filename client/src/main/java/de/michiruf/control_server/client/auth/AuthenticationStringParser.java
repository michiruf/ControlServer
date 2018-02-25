package de.michiruf.control_server.client.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.michiruf.control_server.client.ErrorHandler;
import de.michiruf.control_server.client.Logger;
import de.michiruf.control_server.common.Authentication;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.IOException;

/**
 * @author Michael Ruf
 * @since 2018-02-25
 */
@Singleton
public class AuthenticationStringParser {

    private final ObjectMapper objectMapper;

    @Inject
    public AuthenticationStringParser(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public Authentication parse(String authenticationString) {
        Logger.log("[Server] AuthenticationStringParser got string authentication: %s", authenticationString);
        try {
            return objectMapper.readValue(authenticationString, Authentication.class);
        } catch (IOException e) {
            ErrorHandler.handle(e);
        }
        return null;
    }
}
