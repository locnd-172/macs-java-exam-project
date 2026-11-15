package com.alongtheway.parser;

import com.alongtheway.config.GameConstants;

public enum CommandVerb {
	MOVE,
	EXAMINE,
	DROP,
	UNKNOWN;

	/**
	 * Finds the command verb associated with an input token.
	 *
	 * @param token the first word of the command
	 * @return the matching verb, or #UNKNOWN if the token is not detected
	 */
	public static CommandVerb fromInputToken(String token) {
		if (token == null) return UNKNOWN;
		if (GameConstants.Commands.MOVE_TOKENS.contains(token)) return MOVE;
		if (GameConstants.Commands.EXAMINE_TOKENS.contains(token)) return EXAMINE;
		if (GameConstants.Commands.DROP_TOKENS.contains(token)) return DROP;
		return UNKNOWN;
	}
}
