package de.michiruf.control_server.server;

import dagger.Module;
import dagger.ObjectGraph;
import dagger.Provides;
import de.michiruf.control_server.server.comm.Server;
import de.michiruf.control_server.server.config.Configuration;
import de.michiruf.control_server.server.config.DefaultConfiguration;

import javax.inject.Singleton;

/**
 * @author Michael Ruf
 * @since 2015-08-28
 */
public class ControlServer {

    // TODO the main method should not exist in the server module, but the server ui module!
    // (which also shell provide the configuration)
    public static void main(String[] args) {
        ObjectGraph appGraph = ObjectGraph.create(new ControlServerRunModule());
        Server server = appGraph.get(Server.class);
        server.start();
    }

    @Module(
            includes = {
                    ControlServerModule.class
            }
    )
    public static class ControlServerRunModule {
        
        @SuppressWarnings("unused")
        @Provides
        @Singleton
        public Configuration provideConfiguration() {
            return new DefaultConfiguration(12345);
        }
    }
}
