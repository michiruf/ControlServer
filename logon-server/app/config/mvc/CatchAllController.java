package config.mvc;

import play.mvc.Result;

/**
 * @author Michael Ruf
 * @since 2018-03-01
 */
public class CatchAllController extends ControllerBase {

    public Result invoke(String controller, String method) {
        return ok(); // TODO
    }
}
