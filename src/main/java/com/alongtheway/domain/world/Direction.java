package com.alongtheway.domain.world;

import java.util.Set;

import com.alongtheway.config.GameConstants;
import com.alongtheway.util.TextUtils;

public enum Direction {
	NORTH("north", "n"),
	SOUTH("south", "s"),
	EAST("east", "e"),
	WEST("west", "w"),
	NORTHEAST("northeast", "north east", "ne"),
	NORTHWEST("northwest", "north west", "nw"),
	SOUTHEAST("southeast", "south east", "se"),
	SOUTHWEST("southwest", "south west", "sw");

	private final Set<String> names;

	Direction(String... names) {
		this.names = Set.of(names);
	}

	/**
	 * Returns the opposite direction.
	 *
	 * @return the opposite direction
	 */
	public Direction opposite() {
		return switch (this) {
			case NORTH -> SOUTH;
			case SOUTH -> NORTH;
			case EAST -> WEST;
			case WEST -> EAST;
			case NORTHEAST -> SOUTHWEST;
			case SOUTHWEST -> NORTHEAST;
			case NORTHWEST -> SOUTHEAST;
			case SOUTHEAST -> NORTHWEST;
		};
	}

	/**
	 * Finds the direction named by the text, ignoring filler words like "to" and "the".
	 * "to the North-East" gives {@link #NORTHEAST}.
	 *
	 * @param text what the player typed after the verb
	 * @return the direction, or null if the text is not only a direction
	 */
	public static Direction fromText(String text) {
		String cleaned = TextUtils.normalize(text)
							   .replaceAll(GameConstants.Commands.DIRECTION_FILLER_REGEX, GameConstants.Text.EMPTY)
							   .trim();

		for (Direction direction : values()) {
			if (direction.names.contains(cleaned)) {
				return direction;
			}
		}
		return null;
	}
}
