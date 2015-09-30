package de.michiruf.control_server.robot;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author Michael Ruf
 * @since 2015-09-30
 */
@Singleton
public class StringEventParser {

    @Inject
    public StringEventParser() {
    }

    public Event parse(String eventString) {
        System.out.println(String.format(
                "[Server] StringEventParser got string event: %s", eventString));

        // TODO do this next
        return null;
    }
}
