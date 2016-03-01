package de.michiruf.control_server.client;

import dagger.Module;
import dagger.ObjectGraph;
import de.michiruf.control_server.client.comm.Client;
import de.michiruf.control_server.client.config.Configuration;
import de.michiruf.control_server.client.dispatch.EventDispatcher;
import de.michiruf.control_server.common.ExampleEvents;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
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
 * @since 2015-11-24
 */
@RunWith(VertxUnitRunner.class)
public class ControlClientTest {

    @Inject
    protected Vertx vertx;
    @Inject
    protected Configuration configuration;
    @Inject
    protected Client client;
    @Inject
    protected EventDispatcher eventDispatcher;

    @Before
    public void setUp() {
        ObjectGraph.create(new TestModule()).inject(this);
    }

    @After
    public void tearDown() {
        if (client.isConnected()) {
            client.disconnect();
        }
    }

    @Test
    public void testKeyEvent(TestContext context) {
        Async async = context.async();
        HttpServer server = vertx.createHttpServer().websocketHandler(socketHandler -> socketHandler.handler(handler -> {
            String msg = new String(handler.getBytes());
            System.out.println(String.format("[Test] Server received message: %s", msg));
        })).listen(configuration.getPort());
        client.connect();
        eventDispatcher.dispatch(ExampleEvents.keyEvent());
        client.disconnect();
        server.close();
        async.complete();
    }

    // TODO testMouseEvent()

    @Module(
            injects = ControlClientTest.class,
            includes = ControlClientModule.class,
            overrides = true
    )
    public static class TestModule {
    }
}
