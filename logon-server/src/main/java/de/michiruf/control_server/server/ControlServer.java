package de.michiruf.control_server.server;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Michael Ruf
 * @since 2016-04-06
 */
@Configuration
@ComponentScan
public class ControlServer {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ControlServer.class);
    }
}
