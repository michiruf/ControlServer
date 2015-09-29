package de.michiruf.control_server;

import dagger.Module;
import dagger.ObjectGraph;
import de.michiruf.control_server.comm.Server;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpClientRequest;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static org.junit.Assert.*;

/**
 * @author Michael Ruf
 * @since 2015-09-08
 */
@RunWith(VertxUnitRunner.class)
public class PlaygroundTest {

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
    public void testSuccessTest() {
        assertEquals(true, true);
    }

    @Test(expected = AssertionError.class)
    public void testFailingTest() {
        assertEquals(false, true);
    }

    @Test
    public void testClient(TestContext context) {
        // TODO for now this is only an example copied from their docs
        Async async = context.async();
        HttpClientRequest req = vertx.createHttpClient().get(configuration.getPort(),
                "localhost", "/");
        req.exceptionHandler(err -> context.fail(err.getMessage()));
        req.handler(resp -> {
            context.assertEquals(200, resp.statusCode());
            async.complete();
        });
        req.end();
    }

    @Module(
            injects = PlaygroundTest.class,
            includes = ControlServerModule.class,
            overrides = true
    )
    public static class TestModule {
    }
}
