package controllers;

import play.mvc.Controller;
import views.html.pages.home;

/**
 * @author Michael Ruf
 * @since 2018-02-28
 */
public class TestController extends Controller {

    public play.mvc.Result foo() {
        return ok(home.render());
    }
}
