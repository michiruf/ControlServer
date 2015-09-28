package de.michiruf.control_server;

import dagger.ObjectGraph;
import de.michiruf.control_server.comm.Server;
import io.vertx.core.Vertx;

/**
 * @author Michael Ruf
 * @since 2015-08-28
 */
public class ControlServer {

    public static void main(String[] args) {
        ObjectGraph appGraph = ObjectGraph.create(new ControlServerModule());
        Server server = appGraph.get(Server.class);
        server.start();
    }
}
