package com.alongtheway.domain.world;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.alongtheway.domain.narrative.Scenario;

public class Location {

	private final UUID locationId;
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
	 * @param name the location's name
	 * @param description the location's description
	 * @param aliases alternate names used to identify the location
	 */
	public Location(String name, String description, List<String> aliases) {
		this.locationId = UUID.randomUUID();
		this.name = name;
		this.description = description;
		if (aliases != null) this.aliases.addAll(aliases);
	}

	/**
	 * Adds a character unless it is NULL or already attached to this location.
	 *
	 * @param character the character to add
	 */
	public void addCharacter(Character character) {
		if (character != null && !characters.contains(character))
			this.characters.add(character);
	}

	/**
	 * Finds the first attached character whose name or alias matches the query.
	 *
	 * @param name the character name or alias to search for
	 * @return the matching character
	 */
	public Character findCharacter(String name) {
		for (Character character : this.characters) {
			if (character.find(name)) {
				return character;
			}
		}
		return null;
	}

	/**
	 * Adds an item unless it is NULL or already attached to this location.
	 *
	 * @param item the item to add
	 */
	public void addItem(Item item) {
		if (item != null && !items.contains(item))
			this.items.add(item);
	}

	/**
	 * Finds the first attached item whose name or alias matches the query.
	 *
	 * @param name the item name or alias to search for
	 * @return the matching item
	 */
	public Item findItem(String name) {
		for (Item item : items) {
			if (item.matches(name)) return item;
		}
		return null;
	}

	/**
	 * Adds a link to another location (one-directional)
	 *
	 * @param adjacency the location to link from this location
	 */
	public void addLink(Location adjacency) {
		if (adjacency != null && adjacency != this && !links.contains(adjacency))
			this.links.add(adjacency);
	}

	/**
	 * Adds links to other locations in bulk.
	 *
	 * @param adjacencies the locations to link from this location
	 */
	public void addLinks(List<Location> adjacencies) {
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
	 * Returns the list of locations linked from this location.
	 *
	 * @return this location's outgoing links
	 */
	public List<Location> getLinks() {
		return this.links;
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

}
