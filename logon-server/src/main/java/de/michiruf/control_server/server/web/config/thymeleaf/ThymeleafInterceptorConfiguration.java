package de.michiruf.control_server.server.web.config.thymeleaf;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author kolorobot
 * @see <a href="http://blog.codeleak.pl/2013/11/thymeleaf-template-layouts-in-spring.html">Thymeleaf layouts in spring</a>
 * @see <a href="https://github.com/kolorobot/thymeleaf-custom-layout">Github</a>
 */
@SuppressWarnings("unused")
@Configuration
public class ThymeleafInterceptorConfiguration extends WebMvcConfigurationSupport {

    private static final String DEFAULT_LAYOUT = "layout";
    private static final String DEFAULT_VIEW_ATTRIBUTE_NAME = "view";

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ThymeleafLayoutInterceptor());
    }
}
