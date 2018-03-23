package controllers;

import org.junit.Test;
import org.slf4j.Logger;
import play.shaded.ahc.org.asynchttpclient.AsyncHttpClient;
import play.shaded.ahc.org.asynchttpclient.DefaultAsyncHttpClient;
import play.shaded.ahc.org.asynchttpclient.DefaultAsyncHttpClientConfig;
import play.shaded.ahc.org.asynchttpclient.ListenableFuture;
import play.shaded.ahc.org.asynchttpclient.ws.WebSocket;
import play.shaded.ahc.org.asynchttpclient.ws.WebSocketTextListener;
import play.shaded.ahc.org.asynchttpclient.ws.WebSocketUpgradeHandler;
import play.test.WithServer;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;

import static org.awaitility.Awaitility.await;

/**
 * @author Michael Ruf
 * @since 2018-03-23
 */
public class WebsocketFunctionalTest extends WithServer {

    // TODO This is way to complicated for a simple test....

    @SuppressWarnings("Duplicates") // TODO Remove
    @Test
    public void testCompleteWorkflow() throws Exception {
        String testServerUrl = "http://localhost:" + this.testServer.port() + "/";

        // Create the client
        DefaultAsyncHttpClientConfig config = new DefaultAsyncHttpClientConfig.Builder()
                .setMaxRequestRetry(0)
                .build();
        DefaultAsyncHttpClient httpClient = new DefaultAsyncHttpClient(config);
        WebSocket
        WebsocketClient client = new WebsocketClient(httpClient);

        try {
            LoggingWebsocketTextListener listener = new LoggingWebsocketTextListener(message -> {
            });
            CompletableFuture<WebSocket> completionStage = client.start(testServerUrl, listener);

            httpClient

            await().until(completionStage::isDone);
            await().until(() -> completionStage.get().isOpen());
        } finally {
            httpClient.close();
        }
    }

    public static class WebsocketClient {

        private AsyncHttpClient client;

        public WebsocketClient(AsyncHttpClient c) {
            this.client = c;
        }

        public CompletableFuture<WebSocket> start(String url, WebSocketTextListener listener) throws ExecutionException, InterruptedException {
            WebSocketUpgradeHandler handler = new WebSocketUpgradeHandler.Builder()
                    .addWebSocketListener(listener)
                    .build();
            ListenableFuture<WebSocket> future = client.prepareGet(url).execute(handler);
            return future.toCompletableFuture();
        }

        public void send(Object message) {
        }
    }

    private static class LoggingWebsocketTextListener implements WebSocketTextListener {
        private final Consumer<String> onMessageCallback;

        public LoggingWebsocketTextListener(Consumer<String> onMessageCallback) {
            this.onMessageCallback = onMessageCallback;
        }

        private Logger logger = org.slf4j.LoggerFactory.getLogger(controllers.WebSocketClient.LoggingListener.class);

        private Throwable throwableFound = null;

        public Throwable getThrowable() {
            return throwableFound;
        }

        public void onOpen(WebSocket websocket) {
            //logger.info("onClose: ");
            //websocket.sendMessage("hello");
        }

        public void onClose(WebSocket websocket) {
            //logger.info("onClose: ");
        }

        public void onError(Throwable t) {
            //logger.error("onError: ", t);
            throwableFound = t;
        }

        @Override
        public void onMessage(String s) {
            //logger.info("onMessage: s = " + s);
            onMessageCallback.accept(s);
        }
    }
}
