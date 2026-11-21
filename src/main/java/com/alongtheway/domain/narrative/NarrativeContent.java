package com.alongtheway.domain.narrative;

import com.alongtheway.config.GameConstants;
import com.alongtheway.domain.world.Character;
import com.alongtheway.domain.world.Item;
import com.alongtheway.domain.world.Location;

public class NarrativeContent {

	public String message(String scenarioId, Location location, Character character, Item item, String topic) {
		// TODO: look up message by scenario
		return GameConstants.Messages.GENERIC_NARRATION;
	}
}
