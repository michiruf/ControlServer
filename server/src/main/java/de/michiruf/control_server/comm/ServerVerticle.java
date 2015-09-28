package de.michiruf.control_server.comm;

import de.michiruf.control_server.Configuration;
import io.vertx.core.AbstractVerticle;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author Michael Ruf
 * @since 2015-08-28
 */
@Singleton
public class ServerVerticle extends AbstractVerticle {

    private final Configuration configuration;

    @Inject
    public ServerVerticle(Configuration configuration) {
        super();
        this.configuration = configuration;
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
        }).listen(configuration.getPort());
    }
}
