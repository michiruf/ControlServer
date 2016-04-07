package de.michiruf.control_server.server.web.controller;

import org.springframework.stereotype.Controller;

/**
 * @author Michael Ruf
 * @since 2016-04-07
 */
@SuppressWarnings("unused")
@Controller
public class ContentController {

    public String page(String page) {
        return page;
    }
}
