package de.michiruf.control_server.client.comm;

import de.michiruf.control_server.client.config.ServerConfiguration;
import de.michiruf.control_server.client.event.EventExecutionHandler;
import io.vertx.core.AbstractVerticle;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author Michael Ruf
 * @since 2015-08-28
 */
@Singleton
public class ServerWebSocketVerticle extends AbstractVerticle {

    private final EventExecutionHandler eventExecutionHandler;
    private final ServerConfiguration configuration;

    @Inject
    public ServerWebSocketVerticle(EventExecutionHandler eventExecutionHandler, ServerConfiguration configuration) {
        super();
        this.eventExecutionHandler = eventExecutionHandler;
        this.configuration = configuration;
    }

    @Override
    public void start() throws Exception {
        super.start();
        vertx.createHttpServer().websocketHandler(handler -> {
            handler.handler(event -> eventExecutionHandler.handleStringEvent(new String(event.getBytes())));
            handler.closeHandler(event -> handler.close());
        }).listen(configuration.getHostPort());
    }
}
