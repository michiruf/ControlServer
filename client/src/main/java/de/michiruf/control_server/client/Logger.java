package de.michiruf.control_server.client;

/**
 * @author Michael Ruf
 * @since 2018-02-25
 */
public class Logger {

    public static void log(String format, Object... args) {
        // TODO
        System.out.println(String.format(format, args));
    }
}
