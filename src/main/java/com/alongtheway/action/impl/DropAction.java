package com.alongtheway.action.impl;

import com.alongtheway.action.Action;
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
	public void execute() {
		// TODO: Implement when Player and GameSession are available.
	}
}
