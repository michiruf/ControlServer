package de.michiruf.control_server.client.comm;

import de.michiruf.control_server.client.ErrorHandler;
import io.vertx.core.buffer.impl.BufferImpl;
import io.vertx.core.http.HttpClientOptions;
import io.vertx.core.net.PemTrustOptions;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import java.io.BufferedReader;
import java.io.IOException;
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
    public WebServerClientHttpOptions(@Named("WebServerCertificatePath") String path) {
        try {
            setSsl(true);
            setPemTrustOptions(new PemTrustOptions().addCertValue(
                    new BufferImpl().appendString(loadCertificate(path))));
        } catch (Exception e) {
            ErrorHandler.handle(e);
            throw new IllegalStateException("Web-Server certificate file could not be loaded!", e);
        }
    }

    private String loadCertificate(String filePath) throws IOException {
        URL certResource = getClass().getClassLoader().getResource(filePath);
        if (certResource == null) {
            throw new IllegalStateException("Certificate file not fount (" + filePath + ")");
        }
        return new BufferedReader(new InputStreamReader(certResource.openStream()))
                .lines()
                .collect(Collectors.joining("\n"));
    }
}
