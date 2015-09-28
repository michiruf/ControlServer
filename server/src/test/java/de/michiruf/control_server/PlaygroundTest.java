package de.michiruf.control_server;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Michael Ruf
 * @since 2015-09-08
 */
public class PlaygroundTest {

    private TestClient client;

    @SuppressWarnings("unused")
    @Before
    public void setUp() {
        ControlServer.main(new String[0]);
        client = new TestClient();
    }

    @Test
    public void dummyTest() {
        assertEquals(true, true);
    }
}
