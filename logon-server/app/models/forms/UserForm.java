package models.forms;

import config.validation.MyConstraints;
import models.User;
import play.data.validation.Constraints;
import play.data.validation.ValidationError;

/**
 * @author Michael Ruf
 * @since 2018-03-05
 */
@Constraints.Validate
public class UserForm implements Constraints.Validatable<ValidationError> {

    @Constraints.Required
    @Constraints.MinLength(6)
    public String username;

    @Constraints.Email
    public String email;

    @Constraints.Required
    @Constraints.MinLength(5)
    public String password;

    @Constraints.Required
    public String passwordRepeat;

    @Constraints.Required
    @MyConstraints.RequireTrue
    @SuppressWarnings("unused") // due to validation
    public boolean tos;

    @Override
    public ValidationError validate() {
        if (password != null && !password.equals(passwordRepeat)) {
            return new ValidationError("passwordRepeat", "error.passwords_not_match");
        }
        if (getCountForUsername() > 0) {
            return new ValidationError("username", "error.username_taken");
        }
        // NOTE This should be disabled later, see comment in UserController.doRegister()
        if (getCountForEmail() > 0) {
            return new ValidationError("email", "error.email_taken");
        }
        // We should not validate the email count since its a data privacy issue
        return null;
    }

    public int getCountForUsername() {
        return User.finder.query().where().eq("username", username).findCount();
    }

    public int getCountForEmail() {
        if (email == null || email.isEmpty()) {
            return 0;
        }
        return User.finder.query().where().eq("email", email).findCount();
    }

    public User createUser() {
        return new User(username, email, password);
    }
}
