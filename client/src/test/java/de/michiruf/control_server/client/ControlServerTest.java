package de.michiruf.control_server.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import dagger.Module;
import dagger.ObjectGraph;
import de.michiruf.control_server.client.comm.DirectConnectionServer;
import de.michiruf.control_server.client.config.DirectConnectionServerConfiguration;
import de.michiruf.control_server.common.ExampleEvents;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.WebSocket;
import io.vertx.core.streams.ReadStream;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

/**
 * @author Michael Ruf
 * @since 2015-09-08
 */
@RunWith(VertxUnitRunner.class)
public class ControlServerTest {

    @Inject
    protected Vertx vertx;
    @Inject
    protected DirectConnectionServerConfiguration configuration;
    @Inject
    protected DirectConnectionServer server;
    @Inject
    protected ObjectMapper objectMapper;

    @Before
    public void setUp() {
        ObjectGraph.create(new TestModule()).inject(this);
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        server.start();
    }

    @After
    public void tearDown() {
        if (server.isRunning()) {
            server.stop();
        }
    }

    private ReadStream<WebSocket> createStream() {
        return vertx.createHttpClient().websocketStream(configuration.getHostPort(), "127.0.0.1", "/");
    }

    @Test
    public void testClient(TestContext context) {
        ReadStream<WebSocket> stream = createStream();
        stream.exceptionHandler(err -> {
            System.out.println("[Test] ExceptionHandler got event");
            context.fail(err.getMessage());
        });
        stream.handler(webSocketHandler -> {
            System.out.println("[Test] Handler got connection");
        });
    }

    @Test
    public void testKeyEvent(TestContext context) {
        Async async = context.async();
        ReadStream<WebSocket> stream = createStream();
        stream.handler(webSocketHandler -> {
            webSocketHandler.handler(event -> {
                String eventLog = String.format("[Test] Handler got event: %s",
                        event.getString(0, event.length()));
                System.out.println(eventLog);
            });

            String msg = ExampleEvents.keyEventString(objectMapper);
            System.out.println(String.format("[Test] Sending message %s", msg));
            webSocketHandler.write(Buffer.buffer(msg));
            async.complete();
        });
    }

    @Test
    public void testMouseEvent(TestContext context) {
        Async async = context.async();
        ReadStream<WebSocket> stream = createStream();
        stream.handler(webSocketHandler -> {
            webSocketHandler.handler(event -> {
                String eventLog = String.format("[Test] Handler got event: %s",
                        event.getString(0, event.length()));
                System.out.println(eventLog);
            });

            String msg = ExampleEvents.mouseEventString(objectMapper);
            System.out.println(String.format("[Test] Sending message %s", msg));
            webSocketHandler.write(Buffer.buffer(msg));
            async.complete();
        });
    }

    @Module(
            injects = ControlServerTest.class,
            includes = {
                    ControlClientModule.class,
                    ConfigurationModule.class
            },
            overrides = true
    )
    public static class TestModule {
    }
}
