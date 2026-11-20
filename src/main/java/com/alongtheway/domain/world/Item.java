package com.alongtheway.domain.world;

import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

import com.alongtheway.config.GameConstants;
import com.alongtheway.util.TextUtils;

public class Item {

	private final UUID itemId;
	private String name;
	private String description;
	private final List<String> aliases;

	/**
	 * Creates an item.
	 *
	 * @param name    the item's name
	 * @param aliases alternate names used to identify the item
	 */
	public Item(String name, List<String> aliases) {
		this.itemId = UUID.randomUUID();
		this.name = name;
		this.aliases = aliases;
	}

	/**
	 * Returns the unique ID of the item.
	 *
	 * @return the item's ID
	 */
	public UUID getItemId() {
		return itemId;
	}


	/**
	 * Returns the item's description for examination.
	 *
	 * @return the description, or NULL if none has been assigned
	 */
	public String examine() {
		return this.description;
	}

	/**
	 * Returns the item's display name.
	 *
	 * @return the item's name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Changes the item's name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns the item's current description.
	 *
	 * @return the description, or NULL if none has been assigned
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Changes the item's description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Matches the query against the item's name or aliases at word level,
	 * ignoring case and normalized punctuation and whitespace.
	 *
	 * @param query the text to match
	 * @return true if the name or an alias matches
	 */
	public boolean matches(String query) {
		String tQuery = TextUtils.normalize(query);
		if (tQuery.isBlank()) return false;
		if (matchesName(tQuery, name)) return true;
		return aliases.stream().anyMatch(alias -> matchesName(tQuery, alias));
	}

	/**
	 * Checks a normalized query against one name or alias.
	 *
	 * @param query  the normalized query
	 * @param source the name or alias being checked
	 * @return true if either value contains the other
	 */
	private boolean matchesName(String query, String source) {
		String tSource = TextUtils.normalize(source);
		if (tSource.isBlank()) return false;

		return containsWords(query, tSource) || containsWords(tSource, query);
	}

	private boolean containsWords(String text, String words) {
		String boundary = GameConstants.Text.WORD_BOUNDARY_REGEX;
		String pattern = boundary + Pattern.quote(words) + boundary;
		return Pattern.compile(pattern).matcher(text).find();
	}
}
