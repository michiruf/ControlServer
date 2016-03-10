package security;

import play.mvc.ActionInvoker;
import play.mvc.Before;
import play.mvc.Controller;
import play.mvc.Http;
import play.utils.Java;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author Michael Ruf
 * @since 2016-03-10
 */
public class Security extends Controller {

    @SuppressWarnings("unused")
    @Before
    public static void checkAccess() throws Throwable {
        Authenticate auth = getActionAnnotation(Authenticate.class);
        if (auth == null) {
            auth = getControllerInheritedAnnotation(Authenticate.class);
        }

        if (auth != null) {
            Method m = (Method) ActionInvoker.getActionMethod(Http.Request.current().action)[1];
            if (!Arrays.asList(auth.unless()).contains(m.getName())) {
                check(auth);
            }
        }
    }

    private static void check(Authenticate auth) throws Throwable {
        Handler.invoke("authenticate", auth);
    }

    public static class Handler extends Controller {

        @SuppressWarnings("unused")
        static boolean authenticate(Authenticate authenticate) {
            return false;
        }

        private static Object invoke(String m, Object... args) throws Throwable {
            try {
                return Java.invokeChildOrStatic(Security.class, m, args);
            } catch (InvocationTargetException e) {
                throw e.getTargetException();
            }
        }
    }
}
