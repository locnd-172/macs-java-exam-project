package com.alongtheway.action.impl;

import com.alongtheway.action.Action;
import com.alongtheway.config.GameConstants;
import com.alongtheway.domain.GameSession;
import com.alongtheway.domain.player.Inventory;
import com.alongtheway.domain.world.Item;
import com.alongtheway.parser.Command;

public class UseAction implements Action {
	private final Command command;

	/**
	 * Creates a use action for a command.
	 *
	 * @param command the command containing item
	 */
	public UseAction(Command command) {
		this.command = command;
	}

	@Override
	public void execute(GameSession session) {
		String target = command.getTarget();
		if (target == null || target.isBlank()) {
			session.respond(GameConstants.Messages.USE_TARGET_REQUIRED);
			return;
		}

		Inventory inventory = session.getPlayer().getInventory();
		Item item = inventory.findItem(target);
		if (item == null) {
			session.respond(GameConstants.Messages.USE_ITEM_NOT_FOUND);
			return;
		}

		// TODO: Use Narrator and the active scenario to resolve the item's effect.
		session.respond(String.format(GameConstants.Messages.USE_RESPONSE, item.getName()));
	}
}
