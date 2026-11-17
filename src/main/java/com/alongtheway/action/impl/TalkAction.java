package com.alongtheway.action.impl;

import com.alongtheway.action.Action;
import com.alongtheway.config.GameConstants;
import com.alongtheway.domain.GameSession;
import com.alongtheway.domain.world.Character;
import com.alongtheway.domain.world.Location;
import com.alongtheway.parser.Command;

public class TalkAction implements Action {
	private final Command command;

	/**
	 * Creates a talk action for a command.
	 *
	 * @param command the command
	 */
	public TalkAction(Command command) {
		this.command = command;
	}

	@Override
	public void execute(GameSession session) {
		String target = command.getTarget();
		if (target == null || target.isBlank()) {
			session.respond(GameConstants.Messages.TALK_TARGET_REQUIRED);
			return;
		}

		String[] parts = target.trim().split(GameConstants.Commands.TALK_TOPIC_SEPARATOR, 2);
		
		String characterQuery = parts[0].trim();
		String topic = parts.length > 1 ? parts[1].trim() : GameConstants.Text.EMPTY;

		Character character = null;
		if (!characterQuery.isBlank()) {
			Location location = session.getPlayer().getLocation();
			character = location.findCharacter(characterQuery);
			if (character == null) {
				session.respond(GameConstants.Messages.TALK_CHARACTER_NOT_FOUND);
				return;
			}
		}

		// TODO: Use Narrator to compose a response from the current context.
		String response;
		if (character == null && topic.isBlank()) {
			response = GameConstants.Messages.TALK_GENERAL_RESPONSE;
		} else if (character == null) {
			response = String.format(GameConstants.Messages.TALK_RESPONSE_WITH_TOPIC_NO_CHARACTER, topic);
		} else if (topic.isBlank()) {
			response = String.format(GameConstants.Messages.TALK_RESPONSE, character.getName());
		} else {
			response = String.format(GameConstants.Messages.TALK_RESPONSE_WITH_TOPIC, character.getName(), topic);
		}
		session.respond(response);
	}
}
