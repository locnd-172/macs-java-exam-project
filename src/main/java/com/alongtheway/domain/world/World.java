package com.alongtheway.domain.world;

import java.util.ArrayList;
import java.util.List;

public class World {
	private final List<Location> locations = new ArrayList<>();
	private Location startingLocation;

	/**
	 * Adds a location unless it is NULL or already present.
	 *
	 * @param location the location to add
	 */
	public void addLocation(Location location) {
		if (location != null && !locations.contains(location)) {
			locations.add(location);
		}
	}

	/**
	 * Returns this world's mutable location list.
	 * Changes to the returned list directly affect this world.
	 *
	 * @return this world's location list
	 */
	public List<Location> getLocations() {
		return this.locations;
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
		if (location == null || !locations.contains(location)) {
			return new ArrayList<>();
		}
		return location.getLinks();
	}
}
