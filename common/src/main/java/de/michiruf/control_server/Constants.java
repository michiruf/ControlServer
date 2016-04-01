package de.michiruf.control_server;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * @author Michael Ruf
 * @since 2016-04-01
 */
public class Constants {

    public static URI REGISTRATION_URI;
    public static URI FORGOT_PASSWORD_URI;
    public static URI GITHUB_URI;

    static {
        try {
            REGISTRATION_URI = new URL("https://example.com/register").toURI();
            FORGOT_PASSWORD_URI = new URL("https://example.com/forgot-password").toURI();
            GITHUB_URI = new URL("https://github.com/michiruf").toURI();
        } catch (URISyntaxException | MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
