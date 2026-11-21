package com.alongtheway.domain.narrative;

import com.alongtheway.config.enums.ScenarioGroup;
import com.alongtheway.domain.assessment.Observation;
import com.alongtheway.domain.player.Player;
import com.alongtheway.domain.world.Location;

import java.util.List;
import java.util.Map;

public class Scenario {

	private final String scenarioId;
	private final String title;
	private final List<Location> locations;
	private final ScenarioGroup group;
	private final Map<String, List<Observation>> observations;
	private boolean active;

	/**
	 * Creates a scenario.
	 *
	 * @param scenarioId   the scenario's ID
	 * @param title        the scenario's title
	 * @param locations    the locations of this scenario
	 * @param group        the kind of experience of this scenario
	 * @param observations what the player's actions record in this scenario, by action
	 */
	public Scenario(
		String scenarioId,
		String title,
		List<Location> locations,
		ScenarioGroup group,
		Map<String, List<Observation>> observations
	) {
		this.scenarioId = scenarioId;
		this.title = title;
		this.locations = List.copyOf(locations);
		this.group = group;
		this.observations = Map.copyOf(observations);
	}

	public String getScenarioId() {
		return scenarioId;
	}

	public String getTitle() {
		return title;
	}

	public List<Location> getLocations() {
		return locations;
	}

	public ScenarioGroup getGroup() {
		return group;
	}

	public boolean isActive() {
		return active;
	}

	public boolean canStart(Player player) {
		for (Location location : locations) {
			if (location == player.getLocation()) return true;
		}
		return false;
	}

	public void start(Player player) {
		// TODO
	}

	public void complete() {
		// TODO
	}
}
