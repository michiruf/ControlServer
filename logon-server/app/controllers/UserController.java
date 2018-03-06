package controllers;

import config.mvc.ControllerBase;
import models.forms.ForgotPasswordForm;
import models.forms.UserForm;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Result;
import views.html.user.forgot_password;
import views.html.user.register;
import views.html.user.register_done;

import javax.inject.Inject;

/**
 * @author Michael Ruf
 * @since 2018-03-05
 */
public class UserController extends ControllerBase {

    private final Form<UserForm> userForm;
    private final Form<ForgotPasswordForm> forgotPasswordForm;

    @Inject
    public UserController(FormFactory formFactory) {
        userForm = formFactory.form(UserForm.class);
        forgotPasswordForm = formFactory.form(ForgotPasswordForm.class);
    }

    public Result register() {
        return ok(register.render(userForm));
    }

    public Result doRegister() {
        Form<UserForm> data = this.userForm.bindFromRequest();
        if (data.hasErrors()) {
            return badRequest(register.render(data));
        }

        UserForm userForm = data.get();
        // Only save the user when the email not yet exists
        // By this we avoid brute forcing existing emails
        if (userForm.getCountForEmail() == 0) {
            userForm.createUser().save();
        }

        return redirect(routes.UserController.doneRegister());
    }

    public Result doneRegister() {
        return ok(register_done.render());
    }

    public Result forgotPassword() {
        return ok(forgot_password.render(forgotPasswordForm));
    }

    public Result doForgotPassword() {
        Form<ForgotPasswordForm> data = forgotPasswordForm.bindFromRequest();
        if (data.hasErrors()) {
            return badRequest(forgot_password.render(data));
        }

        String email = data.get().email;
        // TODO Error when submitting

        return ok(email); // TODO
    }
}
