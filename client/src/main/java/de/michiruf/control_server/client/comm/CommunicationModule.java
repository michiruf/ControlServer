package de.michiruf.control_server.client.comm;

import dagger.Module;
import dagger.Provides;
import de.michiruf.control_server.client.config.ClientConfiguration;
import de.michiruf.control_server.client.event.EventDispatcher;
import de.michiruf.control_server.client.event.EventExecutionHandler;
import de.michiruf.control_server.client.event.EventStringConverter;
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

    @SuppressWarnings("unused")
    @Provides
    @Singleton
    @ForWebServer
    public ClientWebSocketVerticle provideWebServerClientWebSocketVerticle(
            @ForWebServer ClientConfiguration configuration,
            EventStringConverter converter,
            EventDispatcher eventDispatcher,
            EventExecutionHandler eventExecutionHandler) {
        return new ClientWebSocketVerticle(configuration, converter, eventDispatcher, eventExecutionHandler);
    }

    @SuppressWarnings("unused")
    @Provides
    @Singleton
    @ForWebServer
    public Client provideWebServerClient(
            Vertx vertx,
            @ForWebServer ClientWebSocketVerticle clientWebSocketVerticle,
            @ForWebServer ClientConfiguration configuration) {
        return new Client(vertx, clientWebSocketVerticle, configuration);
    }

    @SuppressWarnings("unused")
    @Provides
    @Singleton
    @ForDirectConnection
    public ClientWebSocketVerticle provideDirectConnectionClientWebSocketVerticle(
            @ForDirectConnection ClientConfiguration configuration,
            EventStringConverter converter,
            EventDispatcher eventDispatcher,
            EventExecutionHandler eventExecutionHandler) {
        return new ClientWebSocketVerticle(configuration, converter, eventDispatcher, eventExecutionHandler);
    }

    @SuppressWarnings("unused")
    @Provides
    @Singleton
    @ForDirectConnection
    public Client provideDirectConnectionClient(
            Vertx vertx,
            @ForDirectConnection ClientWebSocketVerticle clientWebSocketVerticle,
            @ForDirectConnection ClientConfiguration configuration) {
        return new Client(vertx, clientWebSocketVerticle, configuration);
    }
}
