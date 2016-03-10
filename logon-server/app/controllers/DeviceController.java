package controllers;

import play.mvc.Controller;
import play.mvc.With;
import security.Authenticate;
import security.Security;

/**
 * @author Michael Ruf
 * @since 2016-03-10
 */
@SuppressWarnings("unused")
@With(Security.class)
@Authenticate
public class DeviceController extends Controller {

    public static void test() {
        error("Hello");
    }
}
