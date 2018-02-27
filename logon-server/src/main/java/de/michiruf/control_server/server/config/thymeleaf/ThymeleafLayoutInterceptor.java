package de.michiruf.control_server.server.config.thymeleaf;

import org.springframework.util.Assert;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author kolorobot
 * @see <a href="http://blog.codeleak.pl/2013/11/thymeleaf-template-layouts-in-spring.html">Thymeleaf layouts in spring</a>
 * @see <a href="https://github.com/kolorobot/thymeleaf-custom-layout">Github</a>
 */
public class ThymeleafLayoutInterceptor extends HandlerInterceptorAdapter {

    private static final String DEFAULT_VIEW_ATTRIBUTE_NAME = "view";

    private final String defaultLayout;
    private String viewAttributeName = DEFAULT_VIEW_ATTRIBUTE_NAME;

    public ThymeleafLayoutInterceptor(String defaultLayout) {
        this.defaultLayout = defaultLayout;
    }

    public ThymeleafLayoutInterceptor setViewAttributeName(String viewAttributeName) {
        Assert.hasLength(viewAttributeName, "View attribute name must have a length!");
        this.viewAttributeName = viewAttributeName;
        return this;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) throws Exception {
//        if (modelAndView == null || !modelAndView.hasView()) {
//            return;
//        }
//
//        String originalViewName = modelAndView.getViewName();
//        if (isRedirectOrForward(originalViewName)) {
//            return;
//        }
//
//        String layoutName = getLayoutName(handler);
//        if (Layout.NONE.equals(layoutName)) {
//            return;
//        }
//
//        modelAndView.setViewName(layoutName);
//        modelAndView.addObject(this.viewAttributeName, originalViewName);

        if (modelAndView == null || !modelAndView.hasView()) {
            return;
        }
        String originalViewName = modelAndView.getViewName();
        if (isRedirectOrForward(originalViewName)) {
            return;
        }
        String layoutName = getLayoutName(handler);
        modelAndView.setViewName(layoutName);
        modelAndView.addObject(this.viewAttributeName, originalViewName);
    }

    private boolean isRedirectOrForward(String viewName) {
        return viewName.startsWith("redirect:") || viewName.startsWith("forward:");
    }

    private String getLayoutName(Object handler) {
//        if (handler instanceof HandlerMethod) {
//            HandlerMethod handlerMethod = (HandlerMethod) handler;
//            Layout layout = getMethodOrTypeAnnotation(handlerMethod);
//            if (layout != null) {
//                return layout.value();
//            }
//        }
//        return this.defaultLayout;

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Layout layout = getMethodOrTypeAnnotation(handlerMethod);
        if (layout == null) {
            return this.defaultLayout;
        } else {
            return layout.value();
        }
    }

    private static Layout getMethodOrTypeAnnotation(HandlerMethod handlerMethod) {
        Layout layout = handlerMethod.getMethodAnnotation(Layout.class);
        if (layout == null) {
            return handlerMethod.getBeanType().getAnnotation(Layout.class);
        }
        return layout;
    }
}
