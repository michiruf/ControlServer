package models.forms;

import models.User;
import play.data.validation.Constraints;

/**
 * @author Michael Ruf
 * @since 2018-03-06
 */
public class ForgotPasswordForm {

    @Constraints.Required
    @Constraints.Email
    public String email;

    public User findModel() {
        //User.find
        return null;
    }
}
