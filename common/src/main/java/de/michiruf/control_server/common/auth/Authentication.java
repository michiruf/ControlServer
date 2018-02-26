package de.michiruf.control_server.common.auth;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Michael Ruf
 * @since 2018-02-25
 */
public class Authentication {

    @JsonProperty("password")
    private String encryptedPassword;

    @SuppressWarnings("unused") // for jackson
    protected Authentication() {
    }

    public Authentication(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }
}
