package de.michiruf.control_server.server.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Michael Ruf
 * @since 2016-04-07
 */
@SuppressWarnings("unused")
@Controller
public class ContentController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String page() {
        return page("home");
    }

    public String page(@PathVariable("page") String page) {
        return "content/" + page;
    }
}
