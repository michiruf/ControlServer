package models;

import config.validation.MyConstraints;
import play.data.validation.Constraints;

/**
 * @author Michael Ruf
 * @since 2018-03-05
 */
public class User {

    @Constraints.Required
    @Constraints.MinLength(6)
    private String username;

    @Constraints.Email
    private String email;

    @Constraints.Required
    @Constraints.MinLength(6)
    private String password;

    @Constraints.Required
    @Constraints.MinLength(6)
    private String passwordRepeat;

    @Constraints.Required
    @MyConstraints.RequireTrue
    private boolean tos;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordRepeat() {
        return passwordRepeat;
    }

    public void setPasswordRepeat(String passwordRepeat) {
        this.passwordRepeat = passwordRepeat;
    }

    public boolean isTos() {
        return tos;
    }

    public void setTos(boolean tos) {
        this.tos = tos;
    }
}
