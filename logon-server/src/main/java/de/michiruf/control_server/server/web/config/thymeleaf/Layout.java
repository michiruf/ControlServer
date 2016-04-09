package de.michiruf.control_server.server.web.config.thymeleaf;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Use this to annotate a controller method or class to set the layout used for template inheritance.
 *
 * @author kolorobot
 * @see <a href="http://blog.codeleak.pl/2013/11/thymeleaf-template-layouts-in-spring.html">Thymeleaf layouts in spring</a>
 * @see <a href="https://github.com/kolorobot/thymeleaf-custom-layout">Github</a>
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Layout {
    String value() default "";
}
