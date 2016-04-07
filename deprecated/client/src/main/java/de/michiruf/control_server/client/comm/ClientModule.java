package de.michiruf.control_server.client.comm;

import dagger.Module;
import dagger.Provides;
import de.michiruf.control_server.client.convert.EventStringConverter;
import io.vertx.core.Vertx;

import javax.inject.Singleton;

/**
 * @author Michael Ruf
 * @since 2015-11-14
 */
@Module(
        injects = {
                Client.class,
                WebSocketVerticle.class
        },
        library = true,
        complete = false
)
public class ClientModule {

    @SuppressWarnings("unused")
    @Provides
    @Singleton
    public Vertx provideVertx() {
        return Vertx.vertx();
    }
}
