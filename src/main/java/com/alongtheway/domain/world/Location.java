package com.alongtheway.domain.world;

import java.util.ArrayList;
import java.util.List;

import com.alongtheway.domain.narrative.Scenario;
import com.alongtheway.util.TextUtils;

public class Location {

	private final String locationId;
	private String name;
	private String description;
	private List<String> aliases = new ArrayList<>();
	private List<Location> links = new ArrayList<>();
	private List<Character> characters = new ArrayList<>();
	private List<Item> items = new ArrayList<>();
	private List<Scenario> scenarios = new ArrayList<>();

	/**
	 * Creates a location with the supplied descriptive data.
	 *
	 * @param locationId  the location's stable ID
	 * @param name        the location's name
	 * @param description the location's description
	 * @param aliases     alternate names used to identify the location
	 */
	public Location(String locationId, String name, String description, List<String> aliases) {
		this.locationId = locationId;
		this.name = name;
		this.description = description;
		if (aliases != null) this.aliases.addAll(aliases);
	}

	/**
	 * Returns the location's ID.
	 *
	 * @return the location ID
	 */
	public String getLocationId() {
		return locationId;
	}

	/**
	 * Returns location's name.
	 *
	 * @return the location's name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the location's description.
	 *
	 * @return the location's description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Adds a character unless it is NULL or already attached to this location.
	 *
	 * @param character the character to add
	 */
	public void addCharacter(Character character) {
		if (character != null && !characters.contains(character)) {
			this.characters.add(character);
		}
	}

	/**
	 * Finds the first attached character whose name or alias matches the query.
	 *
	 * @param query the character name or alias to search for
	 * @return the matching character
	 */
	public Character findCharacter(String query) {
		return characters.stream()
		                 .filter(character -> character.match(query))
		                 .findFirst()
		                 .orElse(null);
	}

	/**
	 * Adds an item unless it is NULL or already attached to this location.
	 *
	 * @param item the item to add
	 */
	public void addItem(Item item) {
		if (item != null && !items.contains(item)) {
			this.items.add(item);
		}
	}

	/**
	 * Finds the first attached item whose name or alias matches the query.
	 *
	 * @param name the item name or alias to search for
	 * @return the matching item
	 */
	public Item findItem(String name) {
		return items.stream()
		            .filter(item -> item.match(name))
		            .findFirst()
		            .orElse(null);
	}

	/**
	 * Adds a link to another location (one-directional)
	 *
	 * @param adjacency the location to link from this location
	 */
	public void addLink(Location adjacency) {
		if (adjacency != null && adjacency != this && !links.contains(adjacency)) {
			this.links.add(adjacency);
		}
	}

	/**
	 * Adds links to other locations in bulk.
	 *
	 * @param adjacencies the locations to link from this location
	 */
	public void addLinks(List<Location> adjacencies) {
		if (adjacencies == null) {
			return;
		}
		for (Location adjacency : adjacencies) {
			this.addLink(adjacency);
		}
	}

	/**
	 * Removes a direct link to the given location if one exists.
	 *
	 * @param adjacency the linked location to remove
	 */
	public void removeLink(Location adjacency) {
		this.links.remove(adjacency);
	}

	/**
	 * Returns a copy of the locations linked from this location.
	 *
	 * @return an unmodifiable list of outgoing links
	 */
	public List<Location> getLinks() {
		return List.copyOf(links);
	}

	/**
	 * Checks whether this location has a direct link to another location.
	 *
	 * @param otherLocation the location to check
	 * @return {@code true} if a direct link exists
	 */
	public boolean hasLink(Location otherLocation) {
		return this.links.contains(otherLocation);
	}

	/**
	 * Checks whether the query matches the location's name or an alias.
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
