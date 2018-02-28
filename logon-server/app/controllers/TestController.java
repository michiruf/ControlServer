package controllers;

import play.mvc.Controller;
import play.twirl.api.Content;

/**
 * @author Michael Ruf
 * @since 2018-02-28
 */
public class TestController extends Controller {

    public play.mvc.Result foo() {
//        Content html = views.html.layout.render();
//        return ok(html.body());
        return ok("Test");
    }
}
