package config.template;

import play.twirl.api.Html;
import scala.Symbol;
import views.html.helper.FieldConstructor;
import views.html.helper.FieldElements;

import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * @author Michael Ruf
 * @since 2018-03-06
 */
public class CheckboxFieldConstructor extends BaseConstructor {

    @Override
    public Html apply(FieldElements element) {
        StringBuilder htmlBuilder = new StringBuilder();

        htmlBuilder.append(buildInput(element));
        if (element.args().contains(LABEL)) {
            htmlBuilder.append(buildLabel(element));
        }
        if (element.hasErrors()) {
            htmlBuilder.append(buildError(element));
        }

        return Html.apply(htmlBuilder.toString());
    }

    private String buildInput(FieldElements element) {
        StringBuilder b = new StringBuilder();
        b.append("<input");

        appendAttribute(b, "type", "checkbox");
        appendAttribute(b, "name", element.name());
        if (element.id() != null) {
            appendAttribute(b, "id", element.id());
        }
        element.args()
                .filterKeys(s -> FILTER_HTML_ARGS_START_PATTERN.matcher(s.name()).matches())
                .foreach(t -> {
                    appendAttribute(b, t._1().name(), t._2());
                    return true;
                });
        if (element.field().value() != null && !element.field().value().isEmpty() &&
                element.field().value().get().equals("on")) {
            appendAttribute(b, "checked", "checked");
        }

        b.append(">\n");
        return b.toString();
    }
}
