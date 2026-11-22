package com.alongtheway.domain.narrative;

import com.alongtheway.config.GameConstants;
import com.alongtheway.domain.world.Character;
import com.alongtheway.domain.world.Item;
import com.alongtheway.domain.world.Location;

import java.util.Map;


public class NarrativeContent {

    private final Map<String, Map<String, String>> locationResponses;
    private final Map<String, Map<String, String>> characterResponses;
    private final Map<String, Map<String, String>> itemResponses;

    /**
     * Creates a narration without any text.
     */
    public NarrativeContent() {
        this(Map.of(), Map.of(), Map.of());
    }

    /**
     * Creates a narration.
     *
     * @param locationResponses  the responses of each location
     * @param characterResponses the responses of each character
     * @param itemResponses      the responses of each item
     */
    public NarrativeContent(
        Map<String, Map<String, String>> locationResponses,
        Map<String, Map<String, String>> characterResponses,
        Map<String, Map<String, String>> itemResponses
    ) {
        this.locationResponses = locationResponses;
        this.characterResponses = characterResponses;
        this.itemResponses = itemResponses;
    }

    public String message(String scenarioId, Location location, Character character, Item item, String topic) {
        return GameConstants.Messages.GENERIC_NARRATION;
    }
}
