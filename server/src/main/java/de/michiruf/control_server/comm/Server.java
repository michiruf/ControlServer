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
    private final WebSocketVerticle webSocketVerticle;
    private final Configuration configuration;

    @Inject
    public Server(Vertx vertx, WebSocketVerticle webSocketVerticle, Configuration configuration) {
        this.vertx = vertx;
        this.webSocketVerticle = webSocketVerticle;
        this.configuration = configuration;
    }

    @SuppressWarnings("ThrowableResultOfMethodCallIgnored")
    public void start() {
        vertx.deployVerticle(webSocketVerticle, event -> {
            if (event.succeeded()) {
                System.out.println(String.format(
                        "[Server] STARTED on port %d", configuration.getPort()));
            } else if (event.cause() != null) {
                event.cause().printStackTrace();
            }
        });
    }

    public boolean isRunning() {
        return webSocketVerticle.deploymentID() != null;
    }

    public void stop() {
        try {
            webSocketVerticle.stop();
            System.out.println("[Server] STOPPED");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
