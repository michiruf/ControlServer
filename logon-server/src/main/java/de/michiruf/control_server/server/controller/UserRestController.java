package de.michiruf.control_server.server.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Michael Ruf
 * @since 2016-04-07
 */
@RestController
@RequestMapping("/api/user")
public class UserRestController {

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public void doLogin() {
    }
}
