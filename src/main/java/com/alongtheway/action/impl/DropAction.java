package com.alongtheway.action.impl;

import com.alongtheway.action.Action;
import com.alongtheway.config.GameConstants;
import com.alongtheway.domain.GameSession;
import com.alongtheway.domain.player.Player;
import com.alongtheway.domain.world.Item;
import com.alongtheway.domain.world.Location;
import com.alongtheway.parser.Command;
public class DropAction implements Action {
	private final Command command;

	/**
	 * Creates a drop action for a command.
	 *
	 * @param command the command containing the item to drop
	 */
	public DropAction(Command command) {
		this.command = command;
	}

	@Override
	public void execute(GameSession session) {

		String target = command.getTarget();

		if (target == null || target.isBlank()) {
			session.respond(GameConstants.Messages.DROP_TARGET_REQUIRED);
			return;
		}

		Player player = session.getPlayer();
		Location location = player.getLocation();

		// Find the item in the player's inventory.
		Item item = player.getInventory().findItem(target);

		if (item == null) {
			session.respond(GameConstants.Messages.DROP_ITEM_NOT_FOUND);
			return;
		}

		// Remove the item from the player's inventory.
		Item droppedItem = player.dropItem(item.getItemId());

		if (droppedItem == null) {
			session.respond(GameConstants.Messages.DROP_ITEM_NOT_FOUND);
			return;
		}

		// Add the item to the current location.
		location.addItem(droppedItem);

		String response = String.format(
			GameConstants.Messages.DROP_SUCCESS,
			droppedItem.getName()
		);

		session.respond(response);
	}
}
