package de.michiruf.control_server.client.comm;

import de.michiruf.control_server.client.config.WebServerClientConfiguration;
import de.michiruf.control_server.client.event.EventDispatcher;
import de.michiruf.control_server.client.event.EventExecutionHandler;
import de.michiruf.control_server.client.event.EventStringConverter;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.WebSocket;

/**
 * @author Michael Ruf
 * @since 2015-11-24
 */
public class DirectConnectionClientVerticle extends AbstractVerticle {

    private final WebServerClientConfiguration configuration;
    private final EventStringConverter converter;
    private final EventDispatcher eventDispatcher;
    private final EventExecutionHandler eventExecutionHandler;

    public DirectConnectionClientVerticle(WebServerClientConfiguration configuration,
                                          EventStringConverter converter,
                                          EventDispatcher eventDispatcher,
                                          EventExecutionHandler eventExecutionHandler) {
        super();
        this.configuration = configuration;
        this.converter = converter;
        this.eventDispatcher = eventDispatcher;
        this.eventExecutionHandler = eventExecutionHandler;
    }

    @Override
    public void start() throws Exception {
        super.start();
        vertx.createHttpClient().websocket(
                configuration.getPort(),
                configuration.getHost(),
                "",
                this::handleSocket);
    }

    private void handleSocket(WebSocket socket) {
        // TODO Authentication

        // Register the listener for dispatched events to be sent
        eventDispatcher.registerListener(event -> {
            if (configuration.isSendControlsEnabled()) {
                socket.writeFinalTextFrame(converter.convert(event));
            }
        });
        // Register the handler for incoming messages
        socket.handler(event -> {
            if (configuration.isControlListeningEnabled()) {
                eventExecutionHandler.handleStringEvent(new String(event.getBytes()));
            }
        });
        socket.closeHandler(event -> socket.close());
    }
}
