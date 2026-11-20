package com.alongtheway.domain.world;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.alongtheway.util.TextUtils;

public class Item {

	private final UUID itemId;
	private String name;
	private String description;
	private List<String> aliases = new ArrayList<>();

	/**
	 * Creates an item.
	 *
	 * @param name    the item's name
	 * @param aliases alternate names used to identify the item
	 */
	public Item(String name, List<String> aliases) {
		this.itemId = UUID.randomUUID();
		this.name = name;
		if (aliases != null) this.aliases.addAll(aliases);
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
	 * @return the description, or null if none has been assigned
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
	 * @return the description, or null if none has been assigned
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
	 * Matches the query against the item's name or aliases.
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
