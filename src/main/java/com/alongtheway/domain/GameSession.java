package com.alongtheway.domain;

import com.alongtheway.domain.player.Player;
import com.alongtheway.domain.world.World;

import java.time.LocalDateTime;
import java.util.UUID;

public class GameSession {

	private UUID sessionId;
	private World world;
	private Player player;
	private LocalDateTime startedAt;
	private LocalDateTime endedAt;
	private LocalDateTime savedAt;

	private String message;

	public GameSession(World world, Player player) {
		this.sessionId = UUID.randomUUID();
		this.world = world;
		this.player = player;
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
