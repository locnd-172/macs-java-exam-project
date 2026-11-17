package com.alongtheway.action;

import com.alongtheway.action.impl.DropAction;
import com.alongtheway.action.impl.ExamineAction;
import com.alongtheway.action.impl.MoveAction;
import com.alongtheway.action.impl.TalkAction;
import com.alongtheway.parser.Command;

public class ActionFactory {
	/**
	 * Creates an action for the command's verb.
	 *
	 * @param command the command to turn into an action
	 * @return a new action, or {@code null} for a missing or unsupported command
	 */
	public Action create(Command command) {
		if (command == null || command.getVerb() == null) return null;
		return switch (command.getVerb()) {
			case MOVE -> new MoveAction(command);
			case EXAMINE -> new ExamineAction(command);
			case DROP -> new DropAction(command);
			case TALK -> new TalkAction(command);
			default -> null;
		};
	}
}
