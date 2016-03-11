package de.michiruf.control_server.client.comm;

import de.michiruf.control_server.client.config.ClientConfiguration;
import de.michiruf.control_server.client.convert.EventStringConverter;
import de.michiruf.control_server.client.dispatch.EventDispatcher;
import io.vertx.core.AbstractVerticle;

/**
 * @author Michael Ruf
 * @since 2015-11-24
 */
public class ClientWebSocketVerticle extends AbstractVerticle {

    private final ClientConfiguration configuration;
    private final EventStringConverter converter;
    private final EventDispatcher eventDispatcher;

    public ClientWebSocketVerticle(ClientConfiguration configuration, EventStringConverter converter,
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
