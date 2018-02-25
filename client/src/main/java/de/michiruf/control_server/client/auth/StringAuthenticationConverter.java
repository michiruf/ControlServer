package de.michiruf.control_server.client.auth;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.michiruf.control_server.client.ErrorHandler;
import de.michiruf.control_server.client.Logger;
import de.michiruf.control_server.common.Authentication;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author Michael Ruf
 * @since 2018-02-25
 */
@Singleton
public class StringAuthenticationConverter {

    private final ObjectMapper objectMapper;

    @Inject
    public StringAuthenticationConverter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public String convert(Authentication authentication) {
        Logger.log("[Client] StringAuthenticationConverter got event: %s", authentication);
        try {
            return objectMapper.writeValueAsString(authentication);
        } catch (JsonProcessingException e) {
            ErrorHandler.handle(e);
        }
        return null;
    }
}
