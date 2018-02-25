package de.michiruf.control_server.client.comm;

import dagger.Module;
import dagger.Provides;
import de.michiruf.control_server.client.config.WebServerClientConfiguration;
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
                DirectConnectionServer.class,
                DirectConnectionServerVerticle.class
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
    public WebServerClientVerticle provideWebServerClientVerticle(
            @ForWebServer WebServerClientConfiguration configuration,
            EventStringConverter converter,
            EventDispatcher eventDispatcher,
            EventExecutionHandler eventExecutionHandler) {
        return new WebServerClientVerticle(configuration, converter, eventDispatcher, eventExecutionHandler);
    }

    @SuppressWarnings("unused")
    @Provides
    @Singleton
    @ForWebServer
    public Client provideWebServerClient(
            Vertx vertx,
            WebServerClientVerticle verticle,
            @ForWebServer WebServerClientConfiguration configuration) {
        return new Client(vertx, verticle, configuration);
    }

    @SuppressWarnings("unused")
    @Provides
    @Singleton
    public DirectConnectionClientVerticle provideDirectConnectionClientVerticle(
            @ForDirectConnection WebServerClientConfiguration configuration,
            EventStringConverter converter,
            EventDispatcher eventDispatcher,
            EventExecutionHandler eventExecutionHandler) {
        return new DirectConnectionClientVerticle(configuration, converter, eventDispatcher, eventExecutionHandler);
    }

    @SuppressWarnings("unused")
    @Provides
    @Singleton
    @ForDirectConnection
    public Client provideDirectConnectionClient(
            Vertx vertx,
            DirectConnectionClientVerticle verticle,
            @ForDirectConnection WebServerClientConfiguration configuration) {
        return new Client(vertx, verticle, configuration);
    }
}
