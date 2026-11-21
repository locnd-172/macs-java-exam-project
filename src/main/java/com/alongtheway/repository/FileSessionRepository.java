package com.alongtheway.repository;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import com.alongtheway.domain.GameSession;

public class FileSessionRepository implements SessionRepository {

	private Path directory;

	public FileSessionRepository(Path directory) {
		this.directory = directory;
	}

	public Path getDirectory() {
		return directory;
	}

	@Override
	public void save(GameSession session) throws IOException {
		// TODO: write the session to a file
	}

	@Override
	public GameSession load(String sessionId) throws IOException {
		// TODO: read the session back from file.
		throw new UnsupportedOperationException("");
	}

	@Override
	public List<GameSession> listByPlayer(String playerName) throws IOException {
		// TODO: find the saves of the player.
		throw new UnsupportedOperationException("");
	}
}
