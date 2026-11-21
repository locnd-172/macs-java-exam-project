package com.alongtheway.domain;

import java.util.List;

import com.alongtheway.config.GameConstants;
import com.alongtheway.config.enums.StoryStage;
import com.alongtheway.domain.narrative.Scenario;
import com.alongtheway.domain.player.Player;
import com.alongtheway.domain.world.Location;

public class ScenarioManager {

	private final List<Scenario> scenarios;
	private StoryStage stage = StoryStage.LOST;

	/**
	 * Creates a manager for all the scenarios.
	 *
	 * @param scenarios the scenarios of the story
	 */
	public ScenarioManager(List<Scenario> scenarios) {
		this.scenarios = List.copyOf(scenarios);
	}

	/**
	 * Returns the scenarios of the game.
	 *
	 * @return list of scenarios
	 */
	public List<Scenario> getScenarios() {
		return scenarios;
	}

	/**
	 * Updates the scenarios after the player has done something.
	 *
	 * @param player   the player
	 * @param location the location of the player
	 */
	public void update(Player player, Location location) {
		// TODO
	}

	public boolean recordAction(Player player, String action, AssessmentEngine assessment) {
		// TODO: active scenario record the observations of player's action.
		return false;
	}

	public boolean canFinish(Player player) {
		// TODO
		return false;
	}

	/**
	 * Returns the story stage the player has reached.
	 *
	 * @return the current stage
	 */
	public StoryStage getStage() {
		return stage;
	}

	public String getNarrativeScenarioId(Location location) {
		// TODO
		return GameConstants.Narrative.DEFAULT_SCENARIO_ID;
	}
}
