package config.mvc;

import play.mvc.Result;
import play.mvc.Results;

/**
 * @author Michael Ruf
 * @since 2018-03-01
 */
public class CatchAllController extends ControllerBase {

    // TODO May implement a catch all route?

    // # Call any controller
    // GET     /*controller/*action        config.mvc.CatchAllController.invoke(controller: String, action: String)
    // POST    /*controller/*action        config.mvc.CatchAllController.invoke(controller: String, action: String)

    public Result invoke(String controller, String method) {
        return Results.TODO;
    }
}
