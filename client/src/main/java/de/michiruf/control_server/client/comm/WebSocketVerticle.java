package de.michiruf.control_server.client.comm;

import de.michiruf.control_server.server.Configuration;
import de.michiruf.control_server.server.robot.EventHandler;
import io.vertx.core.AbstractVerticle;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author Michael Ruf
 * @since 2015-11-24
 */
@Singleton
public class WebSocketVerticle extends AbstractVerticle {

//    private final Configuration configuration;

    @Inject
    public WebSocketVerticle(Configuration configuration) {
        super();
        this.configuration = configuration;
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
