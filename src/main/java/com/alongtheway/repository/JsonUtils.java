package com.alongtheway.repository;

import com.alongtheway.config.GameConstants;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

final class JsonUtils {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private JsonUtils() {
    }

    /**
     * Reads a JSON file from the classpath.
     *
     * @param resource the path of the file
     * @return the content of the file
     * @throws IOException if the file does not exist or is not valid JSON
     */
    static JsonNode read(String resource) throws IOException {
        InputStream stream = JsonUtils.class.getResourceAsStream(resource);
        if (stream == null) {
            throw new IOException(
                String.format(GameConstants.Messages.ERROR_RESOURCE_NOT_FOUND, resource)
            );
        }
        return MAPPER.readTree(stream);
    }

    /**
     * Reads a text field.
     *
     * @param node  the JSON object
     * @param field the name of the field
     * @return the text value or an empty string
     */
    static String text(JsonNode node, String field) {
        return node.path(field).asText();
    }

    /**
     * Reads a JSON array of strings.
     *
     * @param array the JSON array
     * @return the string elements or an empty list
     */
    static List<String> texts(JsonNode array) {
        List<String> result = new ArrayList<>();
        for (JsonNode element : array) {
            result.add(element.asText());
        }
        return result;
    }

    /**
     * Reads a JSON object whose values are strings.
     *
     * @param object the JSON object
     * @return the entries of the file
     */
    static Map<String, String> textMap(JsonNode object) {
        Map<String, String> result = new LinkedHashMap<>();

        for (Map.Entry<String, JsonNode> field : object.properties()) {
            result.put(
                field.getKey(),
                field.getValue().asText()
            );
        }
        return result;
    }
}
