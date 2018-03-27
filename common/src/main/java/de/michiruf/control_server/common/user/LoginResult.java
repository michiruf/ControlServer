package de.michiruf.control_server.common.user;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * @author Michael Ruf
 * @since 2018-02-26
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
@Deprecated
public class LoginResult {

    private boolean success;

    @SuppressWarnings("unused") // for jackson
    protected LoginResult() {
    }

    public LoginResult(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }
}
