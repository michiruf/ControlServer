package de.michiruf.control_server.client.comm;

import de.michiruf.control_server.client.ErrorHandler;
import de.michiruf.control_server.client.Logger;
import de.michiruf.control_server.client.config.DirectConnectionServerConfiguration;
import io.vertx.core.Vertx;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author Michael Ruf
 * @since 2015-09-28
 */
@Singleton
public class DirectConnectionServer {

    private final Vertx vertx;
    private final DirectConnectionServerVerticle verticle;
    private final DirectConnectionServerConfiguration configuration;

    @Inject
    public DirectConnectionServer(Vertx vertx,
                                  DirectConnectionServerVerticle verticle,
                                  DirectConnectionServerConfiguration configuration) {
        this.vertx = vertx;
        this.verticle = verticle;
        this.configuration = configuration;
    }

    public void startIfAutoStartEnabled() {
        if (configuration.isHostAutoStartEnabled()) {
            start();
        }
    }

    public void start() {
        vertx.deployVerticle(verticle, event -> {
            if (event.succeeded()) {
                Logger.log("[Server] STARTED on port %d", configuration.getHostPort());
            } else if (event.cause() != null) {
                Logger.log("[Server] NOT STARTED on port %d", configuration.getHostPort());
                ErrorHandler.handle(event.cause());
            }
        });
    }

    public boolean isRunning() {
        // TODO this gives always true after first start
        return verticle.deploymentID() != null;
    }

    public void stop() {
        try {
            verticle.stop();
            Logger.log("[Server] STOPPED");
        } catch (Exception e) {
            Logger.log("[Server] NOT STOPPED");
            ErrorHandler.handle(e);
        }
    }
}
