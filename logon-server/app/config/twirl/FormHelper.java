package config.twirl;

import play.api.templates.PlayMagic;
import play.twirl.api.Html;
import scala.Predef;
import scala.Symbol;
import scala.collection.JavaConverters;
import scala.collection.convert.Wrappers;
import views.html.helper.FieldElements;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author Michael Ruf
 * @since 2018-03-05
 */
public class FormHelper {

    private static final Pattern NEWLINE_PATTERN = Pattern.compile("[\\r\\n]+");
    private static final String NEWLINE_REPLACEMENT = " ";

    private static final Pattern SPACE_PATTERN = Pattern.compile(" +");
    private static final String SPACE_REPLACEMENT = " ";

    private static final Pattern TYPE_PARSER = Pattern.compile("(.*)type=\"(.+?)\"(.*)");
    private static final String TYPE_REPLACEMENT = "$2";

    private static final Pattern FILTER_HTML_ARGS_START_PATTERN = Pattern.compile("^[a-zA-Z](.*?)$");

    public static String replaceNewlines(String input) {
        return NEWLINE_PATTERN.matcher(input).replaceAll(NEWLINE_REPLACEMENT);
    }

    public static String replaceMultipleSpaces(String input) {
        return SPACE_PATTERN.matcher(input).replaceAll(SPACE_REPLACEMENT);
    }

    public static String replaceNewlinesAndMultipleSpaces(String input) {
        return replaceMultipleSpaces(replaceNewlines(input));
    }

    public static String getType(FieldElements elements) {
        String elementsString = elements.toString();
        String noNewlineElementsString = replaceNewlines(elementsString);
        return TYPE_PARSER.matcher(noNewlineElementsString).replaceAll(TYPE_REPLACEMENT);
    }

    @SuppressWarnings("unchecked")
    public static String toHtmlArgsWithFilter(Object mapWrapper) {
        // NOTE For any strange reason, the template does not want to have the method get the wrapper directly...
        // Since this should not change quickly and no help exists there, just cast it
        Wrappers.MapWrapper<Symbol, Object> map = (Wrappers.MapWrapper<Symbol, Object>) mapWrapper;
        Map<Symbol, Object> result = new HashMap<>(map);

        // Remove elements that start not with an alphabetic character
        map.forEach((symbol, anyVal) -> {
            if (!FILTER_HTML_ARGS_START_PATTERN.matcher(symbol.name()).matches()) {
                result.remove(symbol);
            }
        });

        Html html = PlayMagic.toHtmlArgs(toScalaMap(result));
        return html.body();
    }

    private static <A, B> scala.collection.immutable.Map<A, B> toScalaMap(Map<A, B> m) {
        return JavaConverters.mapAsScalaMapConverter(m).asScala().toMap(
                Predef.$conforms()
        );
    }
}
