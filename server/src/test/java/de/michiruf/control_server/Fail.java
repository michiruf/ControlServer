package de.michiruf.control_server;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Michael Ruf
 * @since 2015-09-29
 */
public class Fail {

    // TODO Note that if there are failing tests, the IDEA test config in the docs directory
    // of this module, does not go into the IDE JUnit tests
    //@Test
    public void testFail() {
        Assert.assertEquals(true, false);
    }
}
