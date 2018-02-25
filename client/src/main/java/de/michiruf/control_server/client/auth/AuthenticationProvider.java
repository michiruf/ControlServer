package de.michiruf.control_server.client.auth;

import de.michiruf.control_server.common.Authentication;

/**
 * @author Michael Ruf
 * @since 2018-02-25
 */
public interface AuthenticationProvider {

    boolean isValid(String authenticationString);

    boolean isValid(Authentication authentication);
}
