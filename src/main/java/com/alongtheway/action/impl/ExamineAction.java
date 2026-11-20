package com.alongtheway.action.impl;

import com.alongtheway.action.Action;
import com.alongtheway.config.GameConstants;
import com.alongtheway.domain.GameSession;
import com.alongtheway.domain.player.Inventory;
import com.alongtheway.domain.world.Character;
import com.alongtheway.domain.world.Item;
import com.alongtheway.domain.world.Location;
import com.alongtheway.parser.Command;
import com.alongtheway.util.TextUtils;

public class ExamineAction implements Action {
	private final Command command;

	/**
	 * Creates an examine action for a command.
	 *
	 * @param command the command containing the target
	 */
	public ExamineAction(Command command) {
		this.command = command;
	}

	@Override
	public void execute(GameSession session) {
		String target = command.getTarget();
		if (target == null || target.isBlank()) {
			session.respond(GameConstants.Messages.EXAMINE_TARGET_REQUIRED);
			return;
		}

		Location location = session.getPlayer().getLocation();
		if (GameConstants.Commands.EXAMINE_LOCATION_TARGETS.contains(TextUtils.normalize(target))) {
			respondDescription(session, location.getDescription());
			return;
		}

		// examine item in a location
		Item item = location.findItem(target);
		if (item != null) {
			respondDescription(session, item.examine());
			return;
		}

		// examine item in player's inventory
		Inventory inventory = session.getPlayer().getInventory();
		item = inventory.findItem(target);
		if (item != null) {
			respondDescription(session, item.examine());
			return;
		}

		// examine a character
		Character character = location.findCharacter(target);
		if (character != null) {
			respondDescription(session, character.getDescription());
			return;
		}

		session.respond(GameConstants.Messages.EXAMINE_TARGET_NOT_FOUND);
	}

	private void respondDescription(GameSession session, String description) {
		if (description == null || description.isBlank()) {
			session.respond(GameConstants.Messages.EXAMINE_DESCRIPTION_UNAVAILABLE);
			return;
		}

		session.respond(description);
	}
}
