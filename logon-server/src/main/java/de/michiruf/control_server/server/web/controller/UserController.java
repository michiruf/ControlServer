package de.michiruf.control_server.server.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Michael Ruf
 * @since 2016-04-07
 */
@SuppressWarnings("unused")
@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public void register() {
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public void doRegister() {
    }

    @RequestMapping(value = "/forgot-password", method = RequestMethod.GET)
    public void forgotPassword() {
    }

    @RequestMapping(value = "/forgot-password", method = RequestMethod.POST)
    public void doForgotPassword() {
    }
}
