package com.alongtheway.action.impl;

import com.alongtheway.action.Action;
import com.alongtheway.config.GameConstants;
import com.alongtheway.domain.GameSession;
import com.alongtheway.domain.world.Character;
import com.alongtheway.domain.world.Location;
import com.alongtheway.parser.Command;

public class AssistAction implements Action {
	private final Command command;

	/**
	 * Creates an assist action for a command.
	 *
	 * @param command the command containing the character
	 */
	public AssistAction(Command command) {
		this.command = command;
	}

	@Override
	public void execute(GameSession session) {
		String target = command.getTarget();
		if (target == null || target.isBlank()) {
			session.respond(GameConstants.Messages.ASSIST_TARGET_REQUIRED);
			return;
		}

		Location location = session.getPlayer().getLocation();
		Character character = location.findCharacter(target);
		if (character == null) {
			session.respond(GameConstants.Messages.ASSIST_CHARACTER_NOT_FOUND);
			return;
		}

		// TODO: Use Narrator to resolve the help.
		session.respond(String.format(GameConstants.Messages.ASSIST_RESPONSE, character.getName()));
	}
}
