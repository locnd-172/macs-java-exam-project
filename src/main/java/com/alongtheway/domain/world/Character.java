package com.alongtheway.domain.world;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.alongtheway.util.TextUtils;

public class Character {

	private final String characterId;
	private String name;
	private String description;
	private List<String> aliases = new ArrayList<>();

	/**
	 * Creates a character with UUID as ID.
	 *
	 * @param name        the character's name
	 * @param description the character's description
	 * @param aliases     alternate names used to identify the character
	 */
	public Character(String name, String description, List<String> aliases) {
		this(UUID.randomUUID().toString(), name, description, aliases);
	}

	/**
	 * Creates a character.
	 *
	 * @param characterId the character's stable ID
	 * @param name        the character's name
	 * @param description the character's description
	 * @param aliases     alternate names used to identify the character
	 */
	public Character(String characterId, String name, String description, List<String> aliases) {
		this.characterId = characterId;
		this.name = name;
		this.description = description;
		if (aliases != null) {
			this.aliases.addAll(aliases);
		}
	}

	/**
	 * Gets the character's ID.
	 *
	 * @return the character ID
	 */
	public String getCharacterId() {
		return characterId;
	}

	/**
	 * Gets the character's name.
	 *
	 * @return the character's name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the character's description.
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
