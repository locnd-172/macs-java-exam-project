
package com.alongtheway.action.impl;

import com.alongtheway.action.Action;
import com.alongtheway.config.GameConstants;
import com.alongtheway.domain.GameSession;
import com.alongtheway.domain.player.Player;
import com.alongtheway.domain.world.Item;
import com.alongtheway.domain.world.Location;
import com.alongtheway.parser.Command;

public class TakeAction implements Action {

	private final Command command;

	/**
	 * Creates a take action for a command.
	 *
	 * @param command the command containing the item to pick up
	 */
	public TakeAction(Command command) {
		this.command = command;
	}

	@Override
	public void execute(GameSession session) {

		String target = command.getTarget();

		if (target == null || target.isBlank()) {
			session.respond(GameConstants.Messages.TAKE_TARGET_REQUIRED);
			return;
		}

		Player player = session.getPlayer();
		Location location = player.getLocation();

		// Find the item in the current location.
		Item item = location.findItem(target);

		if (item == null) {
			session.respond(GameConstants.Messages.TAKE_ITEM_NOT_FOUND);
			return;
		}

		// Add the item to the player's inventory.
		if (!player.takeItem(item)) {
			session.respond(GameConstants.Messages.TAKE_ITEM_NOT_FOUND);
			return;
		}

		// Remove the item from the current location.
		location.removeItem(item);

		String response = String.format(
			GameConstants.Messages.TAKE_SUCCESS,
			item.getName()
		);

		session.respond(response);
	}
}
