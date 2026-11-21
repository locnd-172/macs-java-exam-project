package com.alongtheway.repository;

import java.io.IOException;
import java.util.List;

import com.alongtheway.domain.GameSession;

public interface SessionRepository {

	void save(GameSession session) throws IOException;

	GameSession load(String sessionId) throws IOException;

	List<GameSession> listByPlayer(String playerName) throws IOException;
}
