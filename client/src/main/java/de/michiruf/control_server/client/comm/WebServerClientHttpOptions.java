package de.michiruf.control_server.client.comm;

import de.michiruf.control_server.client.ErrorHandler;
import io.vertx.core.buffer.impl.BufferImpl;
import io.vertx.core.http.HttpClientOptions;
import io.vertx.core.net.PemTrustOptions;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.stream.Collectors;

/**
 * @author Michael Ruf
 * @since 2018-02-25
 */
@Singleton
public class WebServerClientHttpOptions extends HttpClientOptions {

    @Inject
    public WebServerClientHttpOptions() {
        try {
            URL certResource = getClass().getClassLoader().getResource("certificates/dev-server.crt");
            String certString = new BufferedReader(new InputStreamReader(certResource.openStream()))
                    .lines()
                    .collect(Collectors.joining("\n"));
            setSsl(true);
            setPemTrustOptions(new PemTrustOptions().addCertValue(
                    new BufferImpl().appendString(certString)));
        } catch (Exception e) {
            ErrorHandler.handle(e);
            throw new IllegalStateException("Web-Server certificate file could not be loaded!", e);
        }
    }
}
