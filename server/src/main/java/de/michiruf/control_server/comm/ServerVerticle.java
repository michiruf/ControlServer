package de.michiruf.control_server.comm;

import io.vertx.core.AbstractVerticle;

/**
 * @author Michael Ruf
 * @since 2015-08-28
 */
public class ServerVerticle extends AbstractVerticle {

    private final int port;

    public ServerVerticle(int port) {
        super();
        this.port = port;
    }

    @Override
    public void start() throws Exception {
        super.start();
        vertx.createHttpServer().websocketHandler(handler -> {
            System.out.println(handler.textHandlerID());

            handler.handler(event -> {
                // TODO more?!
                System.out.println(event + "");
            });
            handler.closeHandler(event -> handler.close());
        }).listen(port);
    }
}
