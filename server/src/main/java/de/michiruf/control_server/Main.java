package de.michiruf.control_server;

import de.michiruf.control_server.comm.ServerVerticle;
import io.vertx.core.Vertx;

/**
 * @author Michael Ruf
 * @since 2015-08-28
 */
public class Main {

    public static final int PORT = 12345;

    @SuppressWarnings("ThrowableResultOfMethodCallIgnored")
    public static void main(String[] args) {
        Vertx.vertx().deployVerticle(new ServerVerticle(PORT), event -> {
            if (event.succeeded()) {
                System.out.println("Server started on port " + PORT);
            } else if (event.cause() != null) {
                event.cause().printStackTrace();
            }
        });
    }
}
