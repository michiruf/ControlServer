package de.michiruf.control_server.client.comm;

import de.michiruf.control_server.client.Configuration;
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
    private final Handler<WebSocket> handler;

    @Inject
    public WebSocketVerticle(Configuration configuration) {
        super();
        this.configuration = configuration;
        handler = new Handler<WebSocket>() {
            @Override
            public void handle(WebSocket event) {
                // TODO bullshit
            }
        };
    }

    @Override
    public void start() throws Exception {
        super.start();
        vertx.createHttpClient().websocket("url", handler -> {
            handler.write(); // TODO
        });


//        vertx.createHttpServer().websocketHandler(handler -> {
//            handler.handler(event -> eventHandler.handleStringEvent(new String(event.getBytes())));
//            handler.closeHandler(event -> handler.close());
//        }).listen(configuration.getPort());
    }
}
