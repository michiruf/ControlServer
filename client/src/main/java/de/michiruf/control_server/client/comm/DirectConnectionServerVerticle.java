package de.michiruf.control_server.client.comm;

import de.michiruf.control_server.client.config.DirectConnectionServerConfiguration;
import de.michiruf.control_server.client.event.EventExecutionHandler;
import de.michiruf.control_server.client.qualifier.ForDirectConnection;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.ServerWebSocket;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author Michael Ruf
 * @since 2015-08-28
 */
@Singleton
public class DirectConnectionServerVerticle extends AbstractVerticle {

    private final EventExecutionHandler eventExecutionHandler;
    private final DirectConnectionServerConfiguration configuration;

    private boolean isAuthenticated = false;

    @Inject
    public DirectConnectionServerVerticle(DirectConnectionServerConfiguration configuration,
                                          EventExecutionHandler eventExecutionHandler) {
        super();
        this.eventExecutionHandler = eventExecutionHandler;
        this.configuration = configuration;
    }

    @Override
    public void start() throws Exception {
        super.start();
        vertx.createHttpServer()
                .websocketHandler(this::handleSocket)
                .listen(configuration.getHostPort());
    }

    private void handleSocket(ServerWebSocket socket) {
//        socket.handler(event -> {
//            if (!isAuthenticated) {
//                isAuthenticated |= authenticator.isValid(new String(event.getBytes()));
//                return;
//            }
//            eventExecutionHandler.handleStringEvent(new String(event.getBytes()));
//        });
//        socket.closeHandler(event -> socket.close());
    }
}
