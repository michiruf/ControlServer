package de.michiruf.control_server.common.auth;

/**
 * @author Michael Ruf
 * @since 2018-02-25
 */
public class AuthenticationResult {

    private final boolean success;

    public AuthenticationResult(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }
}
