package de.michiruf.control_server;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Michael Ruf
 * @since 2015-09-29
 */
public class TestTest {

    @Test
    public void testSuccessTest() {
        assertEquals(true, true);
    }

    @Test(expected = AssertionError.class)
    public void testFailingTest() {
        assertEquals(false, true);
    }
}
