package de.michiruf.control_server.client.comm;

import de.michiruf.control_server.client.config.ClientConfiguration;
import io.vertx.core.Vertx;

/**
 * @author Michael Ruf
 * @since 2015-11-14
 */
// TODO we need to not send data or listen data if the configuration is not enabled (maybe in better verticle?)
// TODO automatically reconnect!
public class Client {

    private final Vertx vertx;
    private final ClientWebSocketVerticle clientWebSocketVerticle;
    private final ClientConfiguration configuration;

    public Client(Vertx vertx, ClientWebSocketVerticle clientWebSocketVerticle, ClientConfiguration configuration) {
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
                        configuration.getHost(), configuration.getPort()));
            } else if (event.cause() != null) {
                System.err.println(String.format(
                        "[Client] NOT CONNECTED on port %s:%d",
                        configuration.getHost(), configuration.getPort()));
                event.cause().printStackTrace(); // TODO Error
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
            e.printStackTrace(); // TODO Error
        }
    }
}
