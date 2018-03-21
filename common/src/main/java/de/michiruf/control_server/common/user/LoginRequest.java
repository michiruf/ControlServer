package de.michiruf.control_server.common.user;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Michael Ruf
 * @since 2018-02-26
 */
public class LoginRequest {

    @JsonProperty("username")
    private String username;

    @JsonProperty("password")
    private String encryptedPassword;

    @SuppressWarnings("unused") // for jackson
    protected LoginRequest() {
    }

    public LoginRequest(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    public LoginRequest(String username, String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    public String getUsername() {
        return username;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }
}
