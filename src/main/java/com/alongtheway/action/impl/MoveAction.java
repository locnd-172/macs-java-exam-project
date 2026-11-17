package com.alongtheway.action.impl;

import com.alongtheway.action.Action;
import com.alongtheway.config.GameConstants;
import com.alongtheway.domain.GameSession;
import com.alongtheway.domain.player.Player;
import com.alongtheway.domain.world.Location;
import com.alongtheway.parser.Command;

public class MoveAction implements Action {
	private final Command command;

	/**
	 * Creates a move action for a command.
	 *
	 * @param command the command containing the destination
	 */
	public MoveAction(Command command) {
		this.command = command;
	}

	@Override
	public void execute(GameSession session) {
		String target = command.getTarget();
		System.out.println(target);
		Location destination = session.getWorld().findLocation(target);

		if (destination == null) {
			session.respond(GameConstants.Messages.MOVE_UNKNOWN);
			return;
		}

		Player player = session.getPlayer();
		Location currentLocation = player.getLocation();

		if (currentLocation.equals(destination)) {
			session.respond(GameConstants.Messages.MOVE_ALREADY_THERE);
			return;
		}

		if (!currentLocation.hasLink(destination)) {
			session.respond(GameConstants.Messages.MOVE_BLOCKED);
			return;
		}

		player.setLocation(destination);
		String response = String.format(GameConstants.Messages.MOVE_ARRIVAL, destination.getName());
		session.respond(response);
	}
}
