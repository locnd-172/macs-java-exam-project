package com.alongtheway.service;

import com.alongtheway.domain.GameSession;
import com.alongtheway.domain.narrative.NarrativeContent;
import com.alongtheway.domain.world.Character;
import com.alongtheway.domain.world.Item;
import com.alongtheway.domain.world.Location;

public class Narrator {

	private final NarrativeContent data;

	/**
	 * Creates a narrator that tells the story from the given content.
	 *
	 * @param data the narration
	 */
	public Narrator(NarrativeContent data) {
		this.data = data;
	}

	/**
	 * Narrates what is happening at the player's current location.
	 * Shows message based on the character, item and topic given.
	 *
	 * @param session   the session being played
	 * @param character the character involved, or null
	 * @param item      the item involved, or null
	 * @param topic     what is asked or done, or null
	 * @return the message to show, never null
	 */
	public String compose(GameSession session, Character character, Item item, String topic) {
		Location location = session.getPlayer().getLocation();
		return data.message(location, character, item, topic);
	}
}
