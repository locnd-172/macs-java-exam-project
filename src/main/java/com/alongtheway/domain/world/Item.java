package com.alongtheway.domain.world;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.alongtheway.util.TextUtils;

public class Item {

	private final String itemId;
	private String name;
	private String description;
	private List<String> aliases = new ArrayList<>();

	/**
	 * Creates an item with UUID as item's ID.
	 *
	 * @param name    the item's name
	 * @param aliases alternate names used to identify the item
	 */
	public Item(String name, List<String> aliases) {
		this(UUID.randomUUID().toString(), name, aliases);
	}

	/**
	 * Creates an item.
	 *
	 * @param itemId  the item's ID
	 * @param name    the item's name
	 * @param aliases alternate names used to identify the item
	 */
	public Item(String itemId, String name, List<String> aliases) {
		this.itemId = itemId;
		this.name = name;
		if (aliases != null) this.aliases.addAll(aliases);
	}

	/**
	 * Gets the item's ID.
	 *
	 * @return the item's ID
	 */
	public String getItemId() {
		return itemId;
	}

	/**
	 * Gets the item's description.
	 *
	 * @return the description, or null if none has been assigned
	 */
	public String examine() {
		return this.description;
	}

	/**
	 * Gets the item's name.
	 *
	 * @return the item's name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the item's name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the item's description.
	 *
	 * @return the description, or null if none has been assigned
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the item's description.
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
