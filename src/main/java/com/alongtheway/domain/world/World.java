package com.alongtheway.domain.world;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class World {
	private final Map<String, Location> locations = new LinkedHashMap<>();
	private Location startingLocation;

	/**
	 * Adds a location unless it is null or its ID is already present.
	 *
	 * @param location the location to add
	 */
	public void addLocation(Location location) {
		if (location != null) {
			locations.putIfAbsent(location.getLocationId(), location);
		}
	}

	public void addLocations(List<Location> locations) {
		if (locations == null || locations.isEmpty()) return;

		for (Location location : locations) {
			addLocation(location);
		}
	}

	/**
	 * Returns this world's mutable map of locations by ID.
	 * Changes to the returned map directly affect this world.
	 *
	 * @return this world's location map
	 */
	public Map<String, Location> getLocations() {
		return this.locations;
	}

	/**
	 * Finds a location by its ID.
	 *
	 * @param locationId the location ID
	 * @return the location, or null if its ID is not present
	 */
	public Location getLocationById(String locationId) {
		return locations.get(locationId);
	}

	/**
	 * Returns the starting location.
	 *
	 * @return the starting location
	 */
	public Location getStartingLocation() {
		return startingLocation;
	}

	/**
	 * Assigns the starting location for this world.
	 *
	 * @param startingLocation the location where a session should begin
	 */
	public void setStartingLocation(Location startingLocation) {
		this.startingLocation = startingLocation;
	}

	/**
	 * Returns the mutable link list for a location in this world.
	 * Returns a new empty list when the argument is {@code null} or is not in this world.
	 *
	 * @param location the location whose links should be queried
	 * @return the location's internal link list, or an empty list
	 */
	public List<Location> getLinks(Location location) {
		if (location == null || locations.get(location.getLocationId()) != location) {
			return new ArrayList<>();
		}
		return location.getLinks();
	}

	/**
	 * Finds the first location whose name or alias matches the query.
	 *
	 * @param query the location name or alias to search for
	 * @return the matching location, or null if none matches
	 */
	public Location findLocation(String query) {
		for (Location location : locations.values()) {
			if (location.match(query)) {
				return location;
			}
		}
		return null;
	}
}
