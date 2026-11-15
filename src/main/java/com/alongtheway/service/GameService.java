package com.alongtheway.service;

import com.alongtheway.action.Action;
import com.alongtheway.action.ActionFactory;
import com.alongtheway.parser.Command;
import com.alongtheway.parser.CommandParser;

public class GameService {
	private final CommandParser parser;
	private final ActionFactory actionFactory;


	public GameService(CommandParser parser, ActionFactory actionFactory) {
		this.parser = parser;
		this.actionFactory = actionFactory;
	}

	/**
	 * Parses player input and creates the corresponding action.
	 *
	 * @param input the player's command
	 * @return the created action
	 */
	public Action createAction(String input) {
		Command command = parser.parse(input);
		return actionFactory.create(command);
	}
}
