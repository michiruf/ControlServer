package de.michiruf.control_server.client.qualifier;

import javax.inject.Qualifier;
import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author Michael Ruf
 * @since 2016-03-11
 */
@Qualifier
@Retention(RUNTIME)
public @interface ForWebServer {
}
