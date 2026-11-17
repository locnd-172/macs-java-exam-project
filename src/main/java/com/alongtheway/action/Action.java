package com.alongtheway.action;

import com.alongtheway.domain.GameSession;

public interface Action {
	/**
	 * Executes the action.
	 */
	void execute(GameSession session);
}
