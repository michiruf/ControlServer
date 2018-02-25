package de.michiruf.control_server.client.comm;

import de.michiruf.control_server.client.ErrorHandler;
import de.michiruf.control_server.client.config.WebServerClientConfiguration;
import de.michiruf.control_server.client.event.EventDispatcher;
import de.michiruf.control_server.client.event.EventExecutionHandler;
import de.michiruf.control_server.client.event.EventStringConverter;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.WebSocket;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author Michael Ruf
 * @since 2015-11-24
 */
@Singleton
public class WebServerClientVerticle extends AbstractVerticle {

    private final WebServerClientConfiguration configuration;
    private final WebServerClientHttpOptions options;
    private final EventStringConverter converter;
    private final EventDispatcher eventDispatcher;
    private final EventExecutionHandler eventExecutionHandler;

    @Inject
    public WebServerClientVerticle(WebServerClientConfiguration configuration,
                                   WebServerClientHttpOptions options,
                                   EventStringConverter converter,
                                   EventDispatcher eventDispatcher,
                                   EventExecutionHandler eventExecutionHandler) {
        super();
        this.configuration = configuration;
        this.options = options;
        this.converter = converter;
        this.eventDispatcher = eventDispatcher;
        this.eventExecutionHandler = eventExecutionHandler;
    }

    @Override
    public void start() throws Exception {
        super.start();
        vertx.createHttpClient(options).websocket(
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

        socket.exceptionHandler(ErrorHandler::handle);
        socket.closeHandler(event -> socket.close());
    }
}
