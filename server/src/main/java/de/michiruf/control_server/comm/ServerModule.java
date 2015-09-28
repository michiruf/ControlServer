package de.michiruf.control_server.comm;

import dagger.Module;
import dagger.Provides;
import io.vertx.core.Vertx;

import javax.inject.Singleton;

/**
 * @author Michael Ruf
 * @since 2015-09-28
 */
@Module(
        injects = {
                Server.class,
                ServerVerticle.class
        },
        library = true,
        complete = false
)
public class ServerModule {

    @SuppressWarnings("unused")
    @Provides
    @Singleton
    public Vertx provideVertx() {
        return Vertx.vertx();
    }
}
