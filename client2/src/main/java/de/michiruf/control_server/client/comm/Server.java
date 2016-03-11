package de.michiruf.control_server.client.comm;

import de.michiruf.control_server.client.config.Configuration;
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
    private final ServerWebSocketVerticle serverWebSocketVerticle;
    private final Configuration configuration;

    @Inject
    public Server(Vertx vertx, ServerWebSocketVerticle serverWebSocketVerticle, Configuration configuration) {
        this.vertx = vertx;
        this.serverWebSocketVerticle = serverWebSocketVerticle;
        this.configuration = configuration;
    }

    @SuppressWarnings("ThrowableResultOfMethodCallIgnored")
    public void start() {
        vertx.deployVerticle(serverWebSocketVerticle, event -> {
            if (event.succeeded()) {
                System.out.println(String.format(
                        "[Server] STARTED on port %d", configuration.getHostPort()));
            } else if (event.cause() != null) {
                System.err.println(String.format(
                        "[Server] NOT STARTED on port %d", configuration.getHostPort()));
                event.cause().printStackTrace();
            }
        });
    }

    public boolean isRunning() {
        return serverWebSocketVerticle.deploymentID() != null;
    }

    public void stop() {
        try {
            serverWebSocketVerticle.stop();
            System.out.println("[Server] STOPPED");
        } catch (Exception e) {
            System.err.println("[Server] NOT STOPPED");
            e.printStackTrace();
        }
    }
}
