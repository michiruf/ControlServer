package de.michiruf.control_server.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import dagger.Module;
import dagger.ObjectGraph;
import de.michiruf.control_server.common.ExampleEvents;
import de.michiruf.control_server.server.comm.Server;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.WebSocketStream;
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
    protected Configuration configuration;
    @Inject
    protected Server server;
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
        server.stop();
    }

    private WebSocketStream createStream() {
        return vertx.createHttpClient().websocketStream(configuration.getPort(), "127.0.0.1", "/");
    }

    @Test
    public void testClient(TestContext context) {
        WebSocketStream stream = createStream();
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
        WebSocketStream stream = createStream();
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

    @Module(
            injects = ControlServerTest.class,
            includes = ControlServerModule.class,
            overrides = true
    )
    public static class TestModule {
    }
}
