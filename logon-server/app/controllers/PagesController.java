package controllers;

import config.mvc.ControllerBase;
import play.mvc.Result;
import play.twirl.api.Html;

/**
 * @author Michael Ruf
 * @since 2018-03-01
 */
public class PagesController extends ControllerBase {

    public Result index() {
        return show("index");
    }

    public Result show(String page) {
        try {
            Html content = (Html) Class.forName("views.html.pages." + page)
                    .getMethod("render")
                    .invoke(null);
            return ok(content);
        } catch (Exception e) {
            // Do nothing
        }
        return errorPage(notFound());
    }
}
