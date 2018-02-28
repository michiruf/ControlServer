package de.michiruf.control_server.server.config.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * @author Michael Ruf
 * @since 2016-04-07
 */
@Controller
@Configuration
@EnableWebSocket
public class WebSocketConfiguration implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(createHandler(), "/ws");
    }

    @Bean
    public WebSocketHandler createHandler() {
        return new WebSocketHandler();
    }

//    /**
//     * For Tomcat, WildFly, and Glassfish
//     * @see <a href="https://docs.spring.io/spring/docs/4.0.1.RELEASE/spring-framework-reference/html/websocket.html">Found here</a>
//     */
//    @Bean
//    public WebSocketContainerFactoryBean createWebSocketContainer() {
//        WebSocketContainerFactoryBean container = new WebSocketContainerFactoryBean();
//        container.setMaxTextMessageBufferSize(8192);
//        container.setMaxBinaryMessageBufferSize(8192);
//        return container;
//    }

//    @Bean
//    public DefaultHandshakeHandler handshakeHandler() {
//        return new DefaultHandshakeHandler();
//    }

//    /**
//     * For Jetty
//     */
//    @Bean
//    public DefaultHandshakeHandler handshakeHandler() {
//        WebSocketPolicy policy = new WebSocketPolicy(WebSocketBehavior.SERVER);
//        policy.setInputBufferSize(8192);
//        policy.setIdleTimeout(600000);
//        return new DefaultHandshakeHandler(
//                new JettyRequestUpgradeStrategy(new WebSocketServerFactory(policy)));
//    }
}
