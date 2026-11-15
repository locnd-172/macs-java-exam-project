package com.alongtheway.action.impl;

import com.alongtheway.action.Action;
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
	public void execute() {
		// TODO: Implement when Player and GameSession are available.
	}
}
