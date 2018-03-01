package config.mvc;

import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.StatusHeader;
import views.html.error;

/**
 * @author Michael Ruf
 * @since 2018-03-01
 */
public class ControllerBase extends Controller {

    public Result errorPage(StatusHeader status) {
        return errorPage(status.status());
    }

    public Result errorPage(int httpError) {
        return status(httpError, error.render(httpError));
    }
}
