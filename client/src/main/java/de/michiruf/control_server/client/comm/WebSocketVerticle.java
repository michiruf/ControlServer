package de.michiruf.control_server.client.comm;

import de.michiruf.control_server.client.Configuration;
import de.michiruf.control_server.client.convert.EventStringConverter;
import de.michiruf.control_server.client.dispatch.EventDispatcher;
import io.vertx.core.AbstractVerticle;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author Michael Ruf
 * @since 2015-11-24
 */
@Singleton
public class WebSocketVerticle extends AbstractVerticle {

    private final Configuration configuration;
    private final EventStringConverter converter;
    private final EventDispatcher eventDispatcher;

    @Inject
    public WebSocketVerticle(Configuration configuration, EventStringConverter converter,
                             EventDispatcher eventDispatcher) {
        super();
        this.configuration = configuration;
        this.converter = converter;
        this.eventDispatcher = eventDispatcher;
    }

    @Override
    public void start() throws Exception {
        super.start();
        vertx.createHttpClient().websocket(
                configuration.getPort(),
                configuration.getHost(),
                "",
                handler -> {
                    eventDispatcher.registerListener(event ->
                            handler.writeFinalTextFrame(converter.convert(event)));
                    handler.handler(event -> System.out.println(new String(event.getBytes())));
                    handler.closeHandler(event -> handler.close());
                });
    }
}
