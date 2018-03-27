package models.forms;

import models.User;
import play.data.validation.Constraints;

/**
 * @author Michael Ruf
 * @since 2018-03-27
 */
@Constraints.Validate
public class AuthenticateForm {

    @Constraints.Required
    @Constraints.MinLength(6)
    public String username;

    @Constraints.Required
    @Constraints.MinLength(5)
    public String password;

    public User findUser() {
        return User.finder.query()
                .where()
                .eq("username", username)
                .and()
                .eq("password", password)
                .findOne();
    }
}
