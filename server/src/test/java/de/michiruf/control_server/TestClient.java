package de.michiruf.control_server;

import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.http.WebSocket;


/**
 * @author Michael Ruf
 * @since 2015-09-28
 */
public class TestClient {

    private final Handler<WebSocket> webSocketHandler;

    public TestClient() {
        webSocketHandler = event -> System.out.println(event.toString());
        Vertx.vertx().createHttpClient().websocket(Configuration.DEFAULT.getPort(), "localhost", "/", webSocketHandler);
    }

    public Handler<WebSocket> getWebSocketHandler() {
        return webSocketHandler;
    }
}
