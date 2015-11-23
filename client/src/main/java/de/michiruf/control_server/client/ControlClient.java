package de.michiruf.control_server.client;

import dagger.ObjectGraph;
import de.michiruf.control_server.client.comm.Client;

/**
 * @author Michael Ruf
 * @since 2015-11-14
 */
public class ControlClient {

    public static void main(String[] args) {
        ObjectGraph appGraph = ObjectGraph.create(new ControlClientModule());
        Client client = appGraph.get(Client.class);
        client.connect();
    }
}
