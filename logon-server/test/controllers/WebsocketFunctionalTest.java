package controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import de.michiruf.control_server.common.user.LoginRequest;
import io.vertx.core.Vertx;
import io.vertx.core.http.WebSocket;
import io.vertx.core.streams.ReadStream;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import play.test.WithServer;

/**
 * @author Michael Ruf
 * @since 2015-09-08
 */
@RunWith(VertxUnitRunner.class)
public class WebsocketFunctionalTest extends WithServer {

    private Vertx vertx;
    private ObjectMapper objectMapper;

    @Before
    public void setUp() {
        objectMapper = new ObjectMapper();
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);

        vertx = Vertx.vertx();
    }

    @After
    public void tearDown() {
        vertx.close();
    }

    private ReadStream<WebSocket> createStream() {
        return vertx.createHttpClient()
                .websocketStream(testServer.port(), "localhost", "/ws");
    }

    @Test
    public void testConnect(TestContext context) {
        Async async = context.async();
        createStream()
                .exceptionHandler(context::fail)
                .handler(websocket -> {
                    try {
                        testConnectSetupWebsocket(context, async, websocket);
                    } catch (Exception e) {
                        context.fail(e);
                    }
                });
        async.await();
    }

    private void testConnectSetupWebsocket(TestContext context, Async async, WebSocket websocket) throws Exception {
        websocket
                .exceptionHandler(context::fail)
                .handler(System.out::println)
                .closeHandler(event -> {
                    System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
                    async.complete();
                });

        // TODO Necessary?
        // Periodically send ping messages (there is also a pongHandler on the websocket object)
        //vertx.setPeriodic(10000, timerId -> {
        //    websocket.writePing(Buffer.buffer("ping"));
        //});

        String t = objectMapper.writeValueAsString(new LoginRequest("bobbi", "123456"));
        websocket.writeTextMessage(t);
    }
}
