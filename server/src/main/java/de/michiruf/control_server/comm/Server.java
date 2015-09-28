package de.michiruf.control_server.comm;

import de.michiruf.control_server.Configuration;
import io.vertx.core.Vertx;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author Michael Ruf
 * @since 2015-09-28
 */
@Singleton
public class Server {

    private final Vertx vertx;
    private final ServerVerticle serverVerticle;
    private final Configuration configuration;

    @Inject
    public Server(Vertx vertx, ServerVerticle serverVerticle, Configuration configuration) {
        this.vertx = vertx;
        this.serverVerticle = serverVerticle;
        this.configuration = configuration;
    }

    @SuppressWarnings("ThrowableResultOfMethodCallIgnored")
    public void start() {
        vertx.deployVerticle(serverVerticle, event -> {
            if (event.succeeded()) {
                System.out.println("Server started on port " + configuration.getPort());
            } else if (event.cause() != null) {
                event.cause().printStackTrace();
            }
        });
    }
}
