package controllers;

import config.mvc.ControllerBase;
import models.User;
import models.forms.ForgotPasswordForm;
import models.forms.UserForm;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Result;
import play.mvc.Results;
import views.html.user.forgot_password;
import views.html.user.forgot_password_done;
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
        // NOTE This should be implemented later
        // Only save the user when the email not yet exists
        // By this we avoid brute forcing existing emails
        // To-do:
        // * Send a confirmation mail when the address is provided
        // * Validation the mail enables account login
        // * A registration with an used address will still throw no error, but send a mail to the address
        //if (userForm.getCountForEmail() == 0) {
        //    userForm.createUser().save();
        //}
        userForm.createUser().save();

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

        User user = data.get().findUser();
        if (user != null) {
            // TODO Send the email
        }

        //return redirect(routes.UserController.doneForgotPassword());
        return Results.TODO;
    }

    public Result doneForgotPassword() {
        return ok(forgot_password_done.render());
    }
}
