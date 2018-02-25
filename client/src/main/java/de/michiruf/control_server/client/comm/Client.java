package de.michiruf.control_server.client.comm;

import de.michiruf.control_server.client.ErrorHandler;
import de.michiruf.control_server.client.Logger;
import de.michiruf.control_server.client.config.WebServerClientConfiguration;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;

/**
 * @author Michael Ruf
 * @since 2015-11-14
 */
// TODO automatically reconnect!
public class Client {

    private final Vertx vertx;
    private final AbstractVerticle verticle;

    public Client(Vertx vertx, AbstractVerticle verticle) {
        this.vertx = vertx;
        this.verticle = verticle;
    }

    public void connect() {
        vertx.deployVerticle(verticle, event -> {
            // TODO Logging with different option classes impossible now
//            if (event.succeeded()) {
//                Logger.log("[Client] CONNECTED on %s:%d",
//                        configuration.getHost(), configuration.getPort());
//            } else if (event.cause() != null) {
//                Logger.log(
//                        "[Client] NOT CONNECTED on port %s:%d",
//                        configuration.getHost(), configuration.getPort());
//                ErrorHandler.handle(event.cause());
//            }
        });
    }

    public boolean isConnected() {
        // TODO this gives always true after first start
        return verticle.deploymentID() != null;
    }

    public void disconnect() {
        try {
            verticle.stop();
            Logger.log("[Client] DISCONNECTED");
        } catch (Exception e) {
            Logger.log("[Client] NOT DISCONNECTED");
            ErrorHandler.handle(e);
        }
    }
}
