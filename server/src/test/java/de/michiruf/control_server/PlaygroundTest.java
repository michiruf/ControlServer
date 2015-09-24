package de.michiruf.control_server;

import io.vertx.core.Vertx;
import junit.framework.TestCase;

/**
 * @author Michael Ruf
 * @since 2015-09-08
 */
public class PlaygroundTest extends TestCase {

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        Main.main(new String[0]);
    }

    public void testClient() {
        Vertx.vertx().createHttpClient().websocket(Main.PORT, "localhost", "", event -> {
            event.writeFinalTextFrame("Hello");
        });
    }
}
