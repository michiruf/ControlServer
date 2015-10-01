package de.michiruf.control_server.server;

import dagger.ObjectGraph;
import de.michiruf.control_server.server.comm.Server;

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
