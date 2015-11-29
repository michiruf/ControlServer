package de.michiruf.control_server.client.comm;

import de.michiruf.control_server.client.Configuration;
import io.vertx.core.Vertx;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author Michael Ruf
 * @since 2015-11-14
 */
@Singleton
public class Client {

    private final Vertx vertx;
    private final WebSocketVerticle webSocketVerticle;
    private final Configuration configuration;

    @Inject
    public Client(Vertx vertx, WebSocketVerticle webSocketVerticle, Configuration configuration) {
        this.vertx = vertx;
        this.webSocketVerticle = webSocketVerticle;
        this.configuration = configuration;
    }

    @SuppressWarnings("ThrowableResultOfMethodCallIgnored")
    public void connect() {
        vertx.deployVerticle(webSocketVerticle, event -> {
            if (event.succeeded()) {
                System.out.println(String.format(
                        "[Client] CONNECTED on %s:%d",
                        configuration.getHost(), configuration.getPort()));
            } else if (event.cause() != null) {
                System.err.println(String.format(
                        "[Client] NOT CONNECTED on port %s:%d",
                        configuration.getHost(), configuration.getPort()));
                event.cause().printStackTrace();
            }
        });
    }

    public boolean isConnected() {
        return webSocketVerticle.deploymentID() != null;
    }

    public void disconnect() {
        try {
            webSocketVerticle.stop();
            System.out.println("[Client] DISCONNECTED");
        } catch (Exception e) {
            System.err.println("[Client] NOT DISCONNECTED");
            e.printStackTrace();
        }
    }
}
