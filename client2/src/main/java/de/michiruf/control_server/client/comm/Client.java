package de.michiruf.control_server.client.comm;

import de.michiruf.control_server.client.config.Configuration;
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
    private final ClientWebSocketVerticle clientWebSocketVerticle;
    private final Configuration configuration;

    @Inject
    public Client(Vertx vertx, ClientWebSocketVerticle clientWebSocketVerticle, Configuration configuration) {
        this.vertx = vertx;
        this.clientWebSocketVerticle = clientWebSocketVerticle;
        this.configuration = configuration;
    }

    @SuppressWarnings("ThrowableResultOfMethodCallIgnored")
    public void connect() {
        vertx.deployVerticle(clientWebSocketVerticle, event -> {
            if (event.succeeded()) {
                System.out.println(String.format(
                        "[Client] CONNECTED on %s:%d",
                        configuration.getDirectConnectionHost(), configuration.getDirectConnectionPort()));
            } else if (event.cause() != null) {
                System.err.println(String.format(
                        "[Client] NOT CONNECTED on port %s:%d",
                        configuration.getDirectConnectionHost(), configuration.getDirectConnectionPort()));
                event.cause().printStackTrace();
            }
        });
    }

    public boolean isConnected() {
        return clientWebSocketVerticle.deploymentID() != null;
    }

    public void disconnect() {
        try {
            clientWebSocketVerticle.stop();
            System.out.println("[Client] DISCONNECTED");
        } catch (Exception e) {
            System.err.println("[Client] NOT DISCONNECTED");
            e.printStackTrace();
        }
    }
}
