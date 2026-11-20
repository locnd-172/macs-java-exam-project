package com.alongtheway.domain.world;

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
	 * Returns a copy of this world's locations by ID.
	 *
	 * @return an unmodifiable map of locations
	 */
	public Map<String, Location> getLocations() {
		return Map.copyOf(locations);
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
	 * Returns a copy of the links for a location in this world.
	 * Returns an empty list when the argument is null or is not in this world.
	 *
	 * @param location the location whose links should be queried
	 * @return an unmodifiable list of links, or an empty list
	 */
	public List<Location> getLinks(Location location) {
		if (location == null || locations.get(location.getLocationId()) != location) {
			return List.of();
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
		return locations.values()
		                .stream()
		                .filter(location -> location.match(query))
		                .findFirst()
		                .orElse(null);
	}
}
