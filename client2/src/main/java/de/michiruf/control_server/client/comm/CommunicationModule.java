package de.michiruf.control_server.client.comm;

import dagger.Module;
import dagger.Provides;
import io.vertx.core.Vertx;

import javax.inject.Singleton;

/**
 * @author Michael Ruf
 * @since 2015-11-14
 */
@Module(
        injects = {
                Client.class,
                ClientWebSocketVerticle.class,
                Server.class,
                ServerWebSocketVerticle.class
        },
        library = true,
        complete = false
)
public class CommunicationModule {

    @SuppressWarnings("unused")
    @Provides
    @Singleton
    public Vertx provideVertx() {
        return Vertx.vertx();
    }
}
