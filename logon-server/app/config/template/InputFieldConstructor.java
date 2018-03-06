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
public class InputFieldConstructor extends BaseConstructor {

    // NOTE Infos are not implemented yet
    // -> <span class="help">@element.infos.mkString(", ")</span>

    private static final Pattern NEWLINE_PATTERN = Pattern.compile("[\\r\\n]+");
    private static final String NEWLINE_REPLACEMENT = " ";
    private static final Pattern TYPE_PARSER = Pattern.compile("(.*)type=\"(.+?)\"(.*)");
    private static final String TYPE_REPLACEMENT = "$2";

    private String typeOverride;

    public InputFieldConstructor() {
    }

    public InputFieldConstructor(String typeOverride) {
        this.typeOverride = typeOverride;
    }

    @Override
    public Html apply(FieldElements element) {
        StringBuilder htmlBuilder = new StringBuilder();
        boolean hasHelp = element.args().contains(HELP);

        if (element.args().contains(LABEL)) {
            htmlBuilder.append(buildLabel(element));
        }
        htmlBuilder.append(buildInput(element, hasHelp));
        if (hasHelp) {
            htmlBuilder.append(buildHelp(element));
        }
        if (element.hasErrors()) {
            htmlBuilder.append(buildError(element));
        }

        return Html.apply(htmlBuilder.toString());
    }

    private String buildInput(FieldElements element, boolean hasHelp) {
        StringBuilder b = new StringBuilder();
        b.append("<input");

        appendAttribute(b, "type", typeOverride != null ? typeOverride : getType(element));
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

    private static String getType(FieldElements elements) {
        String elementsString = elements.toString();
        String noNewlineElementsString = NEWLINE_PATTERN.matcher(elementsString).replaceAll(NEWLINE_REPLACEMENT);
        return TYPE_PARSER.matcher(noNewlineElementsString).replaceAll(TYPE_REPLACEMENT);
    }
}
