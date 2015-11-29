package de.michiruf.control_server.client.comm;

import de.michiruf.control_server.client.Configuration;
import de.michiruf.control_server.client.dispatch.EventDispatcher;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;
import io.vertx.core.http.WebSocket;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author Michael Ruf
 * @since 2015-11-24
 */
@Singleton
public class WebSocketVerticle extends AbstractVerticle {

    private final Configuration configuration;
    private final EventDispatcher eventDispatcher;

    @Inject
    public WebSocketVerticle(Configuration configuration, EventDispatcher eventDispatcher) {
        super();
        this.configuration = configuration;
        this.eventDispatcher = eventDispatcher;
    }

    @Override
    public void start() throws Exception {
        super.start();

        Handler<WebSocket> webSocketHandler = handler -> {
            eventDispatcher.registerListener(() -> {
                handler.writeFinalTextFrame(""); // TODO
            });
            handler.handler(event -> System.out.println(new String(event.getBytes())));
            handler.closeHandler(event -> handler.close());
        };

        vertx.createHttpClient().websocket(
                configuration.getPort(),
                configuration.getHost(),
                "",
                webSocketHandler);
    }
}
