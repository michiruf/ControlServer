package controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import de.michiruf.control_server.common.user.DeviceRequest;
import de.michiruf.control_server.common.user.DeviceResult;
import de.michiruf.control_server.common.user.LoginRequest;
import de.michiruf.control_server.common.user.LoginResult;
import io.vertx.core.Vertx;
import io.vertx.core.http.WebSocket;
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

    @Test
    public void testWebsocket(TestContext context) {
        Async async = context.async();
        vertx.createHttpClient()
                .websocketStream(testServer.port(), "localhost", "/ws")
                .exceptionHandler(context::fail)
                .handler(websocket -> {
                    try {
                        testSetupWebsocket(context, async, websocket);
                    } catch (Exception e) {
                        context.fail(e);
                    }
                });
        async.await();
    }

    private void testSetupWebsocket(TestContext context, Async async, WebSocket websocket) throws Exception {
        websocket
                .exceptionHandler(context::fail)
                .closeHandler(event -> async.complete());

        // Start the first test call
        testLoginRequest(context, websocket);
    }

    private void testLoginRequest(TestContext context, WebSocket websocket) throws Exception {
        websocket.handler(message -> {
            try {
                LoginResult result = objectMapper.readValue(message.toString(), LoginResult.class);
                context.assertNotNull(result);
                context.assertTrue(result.isSuccess());
            } catch (Exception e) {
                context.fail(e);
            }

            // Start the next test call
            try {
                testDeviceRequest(context, websocket);
            } catch (Exception e) {
                context.fail(e);
            }
        });
        websocket.writeTextMessage(objectMapper.writeValueAsString(new LoginRequest("bobbi", "123456")));
    }

    private void testDeviceRequest(TestContext context, WebSocket websocket) throws Exception {
        websocket.handler(message -> {
            try {
                DeviceResult result = objectMapper.readValue(message.toString(), DeviceResult.class);
                context.assertNotNull(result);
                // TODO More
            } catch (Exception e) {
                context.fail(e);
            }

            // Disconnect after everything was tested
            testDisconnect(websocket);
        });
        websocket.writeTextMessage(objectMapper.writeValueAsString(new DeviceRequest()));
    }

    private void testDisconnect(WebSocket websocket) {
        // At last close the websocket to get the test done
        websocket.close();
    }
}
