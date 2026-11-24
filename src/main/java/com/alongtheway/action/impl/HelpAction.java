package com.alongtheway.action.impl;

import com.alongtheway.action.Action;
import com.alongtheway.config.GameConstants;
import com.alongtheway.domain.GameSession;

/**
 * Tells the player which commands exist.
 */
public class HelpAction implements Action {

	@Override
	public void execute(GameSession session) {
		session.respond(GameConstants.Messages.HELP_COMMANDS);
	}
}
