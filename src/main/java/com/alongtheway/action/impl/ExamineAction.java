package com.alongtheway.action.impl;

import com.alongtheway.action.Action;
import com.alongtheway.config.GameConstants;
import com.alongtheway.domain.GameSession;
import com.alongtheway.domain.world.Item;
import com.alongtheway.domain.world.Location;
import com.alongtheway.parser.Command;

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
		Item item = location.findItem(target);

		if (item == null) {
			session.respond(GameConstants.Messages.EXAMINE_ITEM_NOT_FOUND);
			return;
		}

		session.respond(String.format(GameConstants.Messages.EXAMINE_RESPONSE, item.getName()));

	}
}
