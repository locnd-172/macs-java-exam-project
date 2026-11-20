package com.alongtheway.parser;

import java.util.Locale;

import com.alongtheway.config.GameConstants;

public class CommandParser {

	/**
	 * Extract the command verb and target from the player's input.
	 * If the input is null or blank, returns null.
	 *
	 * @param input the player's command
	 * @return the parsed command, or null if input is null or blank
	 */
	public Command parse(String input) {
		if (input == null || input.isBlank()) return null;

		String[] parts = input.trim().split(GameConstants.Text.WHITESPACE_REGEX, 2);

		CommandVerb verb = CommandVerb.fromInputToken(parts[0].toLowerCase(Locale.ROOT));
		String target = parts.length > 1 ? parts[1].trim() : GameConstants.Text.EMPTY;

		return new Command(verb, target);
	}
}
