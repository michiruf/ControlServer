package config.twirl;

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
public class TestFieldConstructor implements FieldConstructor {

    private static final Symbol HELP = Symbol.apply("$help");
    private static final Symbol HELP_CLASS = Symbol.apply("$help_class");

    private static final Pattern FILTER_HTML_ARGS_START_PATTERN = Pattern.compile("^[a-zA-Z](.*?)$");

    @Override
    public Html apply(FieldElements element) {
        StringBuilder htmlBuilder = new StringBuilder();
        boolean hasHelp = element.args().contains(HELP);

        if (element.label() != null) {
            htmlBuilder.append(buildLabel(element));
        }
        htmlBuilder.append(buildInput(element, hasHelp));
        if (hasHelp) {
            htmlBuilder.append(buildHelp(element));
        }

        return Html.apply(htmlBuilder.toString());
    }

    private String buildLabel(FieldElements element) {
        return "<label for=\"" + element.id() + "\">" + element.label() + "</label>\n";
    }

    private String buildInput(FieldElements element, boolean hasHelp) {
        StringBuilder b = new StringBuilder();
        b.append("<input");

        appendAttribute(b, "type", FormHelper.getType(element));
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
        if (element.field().value() != null && element.field().value().isDefined()) {
            appendAttribute(b, "value", element.field().value().get());
        }
        if (hasHelp) {
            appendAttribute(b, "aria-describedby", element.id(), "Help");
        }

        b.append(">\n");
        return b.toString();
    }

    private String buildHelp(FieldElements element) {
        StringBuilder b = new StringBuilder();
        b.append("<small");

        appendAttribute(b, "id", element.id(), "Help");
        if (element.args().contains(HELP_CLASS)) {
            appendAttribute(b, "class", element.args().get(HELP_CLASS).get());
        }

        b.append(">").append(element.args().get(HELP).get()).append("</small>\n");
        return b.toString();
    }

    private static void appendAttribute(StringBuilder builder, String attribute, Object... value) {
        builder.append(" ");
        builder.append(attribute);
        builder.append("=\"");
        Arrays.stream(value).forEach(builder::append);
        builder.append("\"");
    }

    /*
@if(element.args.contains('$help)) {
    <small id="@{
        element.id
    }Help" class="@element.args.getOrElse('$help_class, "")">
    @element.args.getOrElse('$help, "")
    </small>
}

<span class="errors">@element.errors.mkString(", ")</span>
<span class="help">@element.infos.mkString(", ")</span>
     */
}
