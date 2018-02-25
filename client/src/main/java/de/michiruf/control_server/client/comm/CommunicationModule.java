package de.michiruf.control_server.client.comm;

import dagger.Module;
import dagger.Provides;
import de.michiruf.control_server.client.qualifier.ForDirectConnection;
import de.michiruf.control_server.client.qualifier.ForWebServer;
import io.vertx.core.Vertx;

import javax.inject.Singleton;

/**
 * @author Michael Ruf
 * @since 2015-11-14
 */
@Module(
        injects = {
                DirectConnectionClientVerticle.class,
                DirectConnectionServer.class,
                DirectConnectionServerVerticle.class,
                WebServerClientHttpOptions.class,
                WebServerClientVerticle.class
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

    @SuppressWarnings("unused")
    @Provides
    @Singleton
    @ForDirectConnection
    public Client provideDirectConnectionClient(
            Vertx vertx,
            DirectConnectionClientVerticle verticle) {
        return new Client(vertx, verticle);
    }

    @SuppressWarnings("unused")
    @Provides
    @Singleton
    @ForWebServer
    public Client provideWebServerClient(
            Vertx vertx,
            WebServerClientVerticle verticle) {
        return new Client(vertx, verticle);
    }
}
