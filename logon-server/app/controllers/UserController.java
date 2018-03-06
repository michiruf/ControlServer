package controllers;

import config.mvc.ControllerBase;
import models.User;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Result;
import views.html.user.forgot_password;
import views.html.user.register;

import javax.inject.Inject;

/**
 * @author Michael Ruf
 * @since 2018-03-05
 */
public class UserController extends ControllerBase {

    private final Form<User> userForm;

    @Inject
    public UserController(FormFactory formFactory) {
        userForm = formFactory.form(User.class);
    }

    public Result register() {
        return ok(register.render(userForm));
    }

    public Result doRegister() {
        Form<User> data = userForm.bindFromRequest();
        if (data.hasErrors()) {
            return badRequest(register.render(data));
        }

        User user = userForm.get();
        return ok("Got user " + user);
    }

    public Result forgotPassword() {
        return ok(forgot_password.render());
    }

    public Result doForgotPassword() {
        return ok(); // TODO
    }
}
