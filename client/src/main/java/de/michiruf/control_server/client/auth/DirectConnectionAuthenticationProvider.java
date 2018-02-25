package de.michiruf.control_server.client.auth;

import de.michiruf.control_server.common.Authentication;

/**
 * @author Michael Ruf
 * @since 2018-02-25
 */
public class DirectConnectionAuthenticationProvider implements AuthenticationProvider {

    @Override
    public boolean isValid(String authenticationString) {
        // TODO
        return false;
    }

    @Override
    public boolean isValid(Authentication authentication) {
        // TODO
        return false;
    }
}
