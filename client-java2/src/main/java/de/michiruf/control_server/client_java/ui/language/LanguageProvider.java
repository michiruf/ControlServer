package de.michiruf.control_server.client_java.ui.language;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

/**
 * @author Michael Ruf
 * @since 2016-04-01
 */
@Singleton
public class LanguageProvider {

    private final HashMap<String, String> strings;

    @Inject
    public LanguageProvider(@Named("stringsXml") String stringsXml) {
        strings = new HashMap<>();
        loadStrings(stringsXml);
    }

    private void loadStrings(String stringsXml) {
        try {
            URL stringsUrl = getClass().getClassLoader().getResource(stringsXml);
            if (stringsUrl == null) {
                throw new IOException(stringsXml + " not found.");
            }
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = builder.parse(stringsUrl.openStream());

            NodeList rootChildren = document.getDocumentElement().getChildNodes();
            for (int i = 0; i < rootChildren.getLength(); i++) {
                Node node = rootChildren.item(i);
                if (node.getNodeType() == Node.TEXT_NODE) {
                    continue;
                } else if (!"string".equals(node.getNodeName())) {
                    throw new SAXException("The document must contain only <string> tags!");
                }

                String key = node.getAttributes().getNamedItem("name").getNodeValue();
                String value = prepareValue(node.getTextContent());
                strings.put(key, value);
            }

        } catch (IOException | ParserConfigurationException | SAXException e) {
            e.printStackTrace(); // TODO Error!
        }
    }

    private String prepareValue(String value) {
        return value
                .replaceAll("\\s+", " ")
                .replaceAll("\\\\n", "\n")
                .trim();
    }

    public HashMap<String, String> getStrings() {
        return strings;
    }

    public String getString(String key) {
        return strings.get(key);
    }
}
