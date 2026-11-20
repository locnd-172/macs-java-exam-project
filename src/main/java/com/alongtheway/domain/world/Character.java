package com.alongtheway.domain.world;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.alongtheway.util.TextUtils;

public class Character {

	private final UUID characterId;
	private String name;
	private String description;
	private List<String> aliases = new ArrayList<>();

	/**
	 * Creates a character.
	 *
	 * @param name        the character's name
	 * @param description the character's description
	 * @param aliases     alternate names used to identify the character
	 */
	public Character(String name, String description, List<String> aliases) {
		this.characterId = UUID.randomUUID();
		this.name = name;
		this.description = description;
		if (aliases != null) this.aliases = aliases;
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
	 * Matches the query against the character's name or aliases.
	 *
	 * @param query the text to match
	 * @return true if the name or an alias matches
	 */
	public boolean match(String query) {
		List<String> candidates = new ArrayList<>();
		candidates.add(name);
		candidates.addAll(aliases);
		return TextUtils.match(query, candidates);
	}

}
