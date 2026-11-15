package com.alongtheway.action.impl;

import com.alongtheway.action.Action;
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
	public void execute() {
		// TODO: Implement when Player and GameSession are available.
	}
}
