package com.alongtheway.action.impl;

import com.alongtheway.action.Action;
import com.alongtheway.config.GameConstants;
import com.alongtheway.domain.GameSession;
import com.alongtheway.domain.world.Character;
import com.alongtheway.domain.world.Location;
import com.alongtheway.parser.Command;

public class HelpAction implements Action {
	private final Command command;

	/**
	 * Creates a help action for a command.
	 *
	 * @param command the command containing the character
	 */
	public HelpAction(Command command) {
		this.command = command;
	}

	@Override
	public void execute(GameSession session) {
		String target = command.getTarget();
		if (target == null || target.isBlank()) {
			session.respond(GameConstants.Messages.HELP_TARGET_REQUIRED);
			return;
		}

		Location location = session.getPlayer().getLocation();
		Character character = location.findCharacter(target);
		if (character == null) {
			session.respond(GameConstants.Messages.HELP_CHARACTER_NOT_FOUND);
			return;
		}

		// TODO: Use Narrator and the active scenario to resolve the help.
		session.respond(String.format(GameConstants.Messages.HELP_RESPONSE, character.getName()));
	}
}
