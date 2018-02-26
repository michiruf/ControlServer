package de.michiruf.control_server.client_java.config;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Michael Ruf
 * @since 2018-02-25
 */
public class JavaClientConfiguration {

    @JsonProperty
    private JavaClientDirectConnectionClientConfiguration directConnectionClient;
    @JsonProperty
    private JavaClientDirectConnectionServerConfiguration directConnectionServer;
    @JsonProperty
    private JavaClientWebServerClientConfiguration webServerClient;

    public JavaClientConfiguration reset() {
        directConnectionClient = new JavaClientDirectConnectionClientConfiguration();
        directConnectionServer = new JavaClientDirectConnectionServerConfiguration();
        webServerClient = new JavaClientWebServerClientConfiguration();
        return this;
    }

    public JavaClientDirectConnectionClientConfiguration getDirectConnectionClient() {
        return directConnectionClient;
    }

    public JavaClientDirectConnectionServerConfiguration getDirectConnectionServer() {
        return directConnectionServer;
    }

    public JavaClientWebServerClientConfiguration getWebServerClient() {
        return webServerClient;
    }
}
