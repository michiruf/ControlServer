package de.michiruf.control_server.comm;

import de.michiruf.control_server.Configuration;
import de.michiruf.control_server.robot.EventHandler;
import io.vertx.core.AbstractVerticle;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author Michael Ruf
 * @since 2015-08-28
 */
@Singleton
public class WebSocketVerticle extends AbstractVerticle {

    private final EventHandler eventHandler;
    private final Configuration configuration;

    @Inject
    public WebSocketVerticle(EventHandler eventHandler, Configuration configuration) {
        super();
        this.eventHandler = eventHandler;
        this.configuration = configuration;
    }

    @Override
    public void start() throws Exception {
        super.start();
        vertx.createHttpServer().websocketHandler(handler -> {
            handler.handler(event -> eventHandler.handleStringEvent(new String(event.getBytes())));
            handler.closeHandler(event -> handler.close());
        }).listen(configuration.getPort());
    }
}
