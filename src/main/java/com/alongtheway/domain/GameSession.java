package com.alongtheway.domain;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.alongtheway.config.enums.StoryStage;
import com.alongtheway.domain.assessment.AssessmentReport;
import com.alongtheway.domain.player.Player;
import com.alongtheway.domain.world.World;

public class GameSession {

	private UUID sessionId;
	private World world;
	private Player player;
	private ScenarioManager scenarioManager;
	private AssessmentEngine assessmentEngine;
	private StoryStage storyStage = StoryStage.LOST;
	private AssessmentReport assessmentReport;
	private LocalDateTime startedAt;
	private LocalDateTime endedAt;
	private LocalDateTime savedAt;

	private String message;

	public GameSession(World world, Player player) {
		this(world, player, new ScenarioManager(List.of()), new AssessmentEngine(List.of()));
	}

	/**
	 * Creates a session.
	 *
	 * @param world            the world being played
	 * @param player           the player
	 * @param scenarioManager  tracks the story's progress
	 * @param assessmentEngine collects the observations and builds the report
	 */
	public GameSession(
		World world,
		Player player,
		ScenarioManager scenarioManager,
		AssessmentEngine assessmentEngine
	) {
		this.sessionId = UUID.randomUUID();
		this.world = world;
		this.player = player;
		this.scenarioManager = scenarioManager;
		this.assessmentEngine = assessmentEngine;
		this.startedAt = LocalDateTime.now();
	}

	public UUID getSessionId() {
		return sessionId;
	}

	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public ScenarioManager getScenarioManager() {
		return scenarioManager;
	}

	public AssessmentEngine getAssessmentEngine() {
		return assessmentEngine;
	}

	public StoryStage getStoryStage() {
		return storyStage;
	}

	public AssessmentReport getAssessmentReport() {
		return assessmentReport;
	}

	public boolean isActive() {
		return endedAt == null;
	}

	public void endGame(AssessmentReport report) {
		// TODO.
		this.endedAt = LocalDateTime.now();
	}

	public void respond(String message) {
		if (message != null && !message.isBlank()) {
			this.message = message;
		}
	}

	public String takeMessage() {
		String result = message;
		message = null;
		return result;
	}
}
