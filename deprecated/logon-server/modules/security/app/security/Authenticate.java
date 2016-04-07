package security;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Michael Ruf
 * @since 2016-03-10
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface Authenticate {

    /**
     * Used to name methods where the authentication process shell be skipped when using as a controller annotation.
     *
     * @return Name of methods the authentication shell not be checked
     */
    String[] unless() default {};

    /**
     * If you prefer to implement a user profile or state you can set this value to handle it in your implementation
     * of the authentication process.
     *
     * @return Authentication profile required
     */
    String[] profiles() default {};

    /**
     * If you prefer to implement a user level required to perform tasks you can set this to handle it in your
     * implementation of the authentication process.
     *
     * @return Authentication level required
     */
    int level() default 0;
}