package controllers;

import play.mvc.Controller;

/**
 * @author Michael Ruf
 * @since 2016-03-09
 */
@SuppressWarnings("unused")
public class Application extends Controller {

    public static void page(String id) {
        render("pages/" + id + ".html");
    }
}