package controllers;

import play.mvc.Controller;

/**
 * @author Michael Ruf
 * @since 2016-03-09
 */
@SuppressWarnings("unused")
public class UserController extends Controller {

    public static void doLoginJson(String username, String password) {

    }

    public static void register() {
        render();
    }

    public static void doRegister(String username, String password, String mail) {

    }
}
