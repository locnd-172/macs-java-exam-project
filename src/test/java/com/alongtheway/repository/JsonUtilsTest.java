package com.alongtheway.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

class JsonUtilsTest {

    private JsonNode node;

    @BeforeEach
    void setUp() throws IOException {
        node = new ObjectMapper().readTree("""
            {
              "name": "Emma",
              "aliases": ["owner", "cafe owner"],
              "responses": { "b": "2", "a": "1" }
            }
            """);
    }

    @Test
    void readsFileFromClasspath() throws IOException {
        assertTrue(JsonUtils.read("/narrative/world.json").has("locations"));
    }

    @Test
    void readsText() {
        assertEquals("Emma", JsonUtils.text(node, "name"));
        assertEquals("", JsonUtils.text(node, "missing"));
    }

    @Test
    void readsTexts() {
        assertEquals(List.of("owner", "cafe owner"), JsonUtils.texts(node.path("aliases")));
        assertTrue(JsonUtils.texts(node.path("missing")).isEmpty());
    }

    @Test
    void readsTextMapInFileOrder() {
        assertEquals(List.of("b", "a"), List.copyOf(JsonUtils.textMap(node.path("responses")).keySet()));
        assertTrue(JsonUtils.textMap(node.path("missing")).isEmpty());
    }

    @Test
    void throwsIOExceptionWhenFileIsMissing() {
        IOException error = assertThrows(IOException.class, () -> JsonUtils.read("/narrative/nothing.json"));

        assertTrue(error.getMessage().contains("/narrative/nothing.json"));
    }
}
