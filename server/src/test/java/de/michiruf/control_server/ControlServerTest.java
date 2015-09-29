package de.michiruf.control_server;

import dagger.Module;
import dagger.ObjectGraph;
import de.michiruf.control_server.comm.Server;
import io.vertx.core.Vertx;
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

    @Before
    public void setUp() {
        ObjectGraph.create(new TestModule()).inject(this);
        server.start();
    }

    @After
    public void tearDown() {
        server.stop();
    }

    @Test
    public void testClient(TestContext context) {
        Async async = context.async();
        WebSocketStream stream = vertx.createHttpClient().websocketStream(
                configuration.getPort(), "127.0.0.1", "/");
        stream.exceptionHandler(err -> {
            System.out.println("ExceptionHandler got event");
            context.fail(err.getMessage());
        });
        stream.handler(resp -> {
            System.out.println("Handler got response");
            resp.handler(event -> {
                System.out.println("Handler got event");
                System.out.println(event.length());
            });
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
