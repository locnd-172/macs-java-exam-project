package com.alongtheway.domain.world;

import java.util.List;
import java.util.UUID;

import com.alongtheway.util.TextUtils;

public class Character {

	private final UUID characterId;
	private String name;
	private String description;
	private List<String> aliases;

	/**
	 * Creates a character.
	 *
	 * @param name the character's name
	 * @param description the character's description
	 * @param aliases alternate names used to identify the character
	 */
	public Character(String name, String description, List<String> aliases) {
		this.characterId = UUID.randomUUID();
		this.name = name;
		this.description = description;
		this.aliases = aliases;
	}

	/**
	 * Returns the character's name.
	 *
	 * @return the character's name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the character's description.
	 *
	 * @return the character's description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Checks whether the query partially matches the character's name or an alias,
	 * ignoring case and normalized punctuation and whitespace.
	 *
	 * @param query the text to match
	 * @return {@code true} if the name or an alias matches
	 */
	public boolean find(String query) {
		return matchesName(query) || matchesAlias(query);
	}

	/**
	 * Checks the query against the character's name.
	 *
	 * @param query the normalized or raw text to match
	 * @return {@code true} if the name matches
	 */
	private boolean matchesName(String query) {
		return match(query, this.name);
	}

	/**
	 * Checks the query against the character's aliases.
	 *
	 * @param query the normalized or raw text to match
	 * @return {@code true} if any alias matches
	 */
	private boolean matchesAlias(String query) {
		for (String alias : aliases) {
			if (match(query, alias)) return true;
		}
		return false;
	}

	/**
	 * Normalizes both values and performs a partial match in either direction.
	 *
	 * @param query the text entered by the player
	 * @param source the name or alias being checked
	 * @return true if either normalized value contains the other
	 */
	private boolean match(String query, String source) {
		String tQuery = TextUtils.normalize(query);
		String tSource = TextUtils.normalize(source);
		return !tQuery.isBlank()
			&& !tSource.isBlank()
			&& (tQuery.contains(tSource) || tSource.contains(tQuery)
		);
	}

}
