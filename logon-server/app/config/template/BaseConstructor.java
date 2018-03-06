package config.template;

import scala.Symbol;
import views.html.helper.FieldConstructor;
import views.html.helper.FieldElements;

import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * @author Michael Ruf
 * @since 2018-03-06
 */
public abstract class BaseConstructor implements FieldConstructor {

    protected static final Symbol LABEL = Symbol.apply("$label");
    protected static final Symbol LABEL_CLASS = Symbol.apply("$label_class");
    protected static final Symbol HELP = Symbol.apply("$help");
    protected static final Symbol HELP_CLASS = Symbol.apply("$help_class");
    protected static final Symbol ERROR_CLASS = Symbol.apply("$error_class");

    protected static final Pattern FILTER_HTML_ARGS_START_PATTERN = Pattern.compile("^[a-zA-Z](.*?)$");

    protected String buildLabel(FieldElements element) {
        StringBuilder b = new StringBuilder();
        b.append("<label");

        appendAttribute(b, "for", element.id());
        if (element.args().contains(LABEL_CLASS)) {
            appendAttribute(b, "class", element.args().get(LABEL_CLASS).get());
        }

        b.append(">").append(element.args().get(LABEL).get()).append("</label>\n");
        return b.toString();
    }

    protected String buildHelp(FieldElements element) {
        StringBuilder b = new StringBuilder();
        b.append("<small");

        appendAttribute(b, "id", element.id(), "Help");
        if (element.args().contains(HELP_CLASS)) {
            appendAttribute(b, "class", element.args().get(HELP_CLASS).get());
        }

        b.append(">").append(element.args().get(HELP).get()).append("</small>\n");
        return b.toString();
    }

    protected String buildError(FieldElements element) {
        StringBuilder b = new StringBuilder();
        b.append("<small");

        if (element.args().contains(ERROR_CLASS)) {
            appendAttribute(b, "class", element.args().get(ERROR_CLASS).get());
        }

        b.append(">").append(element.errors().mkString(". ")).append(".").append("</small>\n");
        return b.toString();
    }

    protected static void appendAttribute(StringBuilder builder, String attribute, Object... value) {
        builder.append(" ");
        builder.append(attribute);
        builder.append("=\"");
        Arrays.stream(value).forEach(builder::append);
        builder.append("\"");
    }
}
