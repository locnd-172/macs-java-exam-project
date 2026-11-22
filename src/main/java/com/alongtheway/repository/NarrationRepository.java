package com.alongtheway.repository;

import com.alongtheway.config.GameConstants;
import com.alongtheway.domain.narrative.NarrativeContent;

import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.alongtheway.repository.JsonUtils.text;
import static com.alongtheway.repository.JsonUtils.textMap;

public class NarrationRepository {

    private static final String LOCATIONS  = "locations";
    private static final String CHARACTERS = "characters";
    private static final String ITEMS      = "items";
    private static final String ID         = "id";
    private static final String RESPONSES  = "responses";

    private final JsonNode root;

    /**
     * Reads the world file which also contains the responses.
     *
     * @throws IOException if the file is missing or is not valid JSON
     */
    public NarrationRepository() throws IOException {
        this.root = JsonUtils.read(GameConstants.Resources.WORLD);
    }

    /**
     * Builds the narration (responses based on location, character and item).
     *
     * @return the narration
     */
    public NarrativeContent load() {
        Map<String, Map<String, String>> locations = new LinkedHashMap<>();
        Map<String, Map<String, String>> characters = new LinkedHashMap<>();
        Map<String, Map<String, String>> items = new LinkedHashMap<>();

        for (JsonNode locationNode : root.path(LOCATIONS)) {
            readResponses(locationNode, locations);

            for (JsonNode characterNode : locationNode.path(CHARACTERS)) {
                readResponses(characterNode, characters);
            }

            for (JsonNode itemNode : locationNode.path(ITEMS)) {
                readResponses(itemNode, items);
            }
        }
        return new NarrativeContent(locations, characters, items);
    }

    /**
     * Stores the responses of an entry with its ID.
     *
     * @param node   the entry of a location, character or item
     * @param target response object of the entry
     */
    private void readResponses(JsonNode node, Map<String, Map<String, String>> target) {
        target.put(text(node, ID), textMap(node.path(RESPONSES)));
    }
}
