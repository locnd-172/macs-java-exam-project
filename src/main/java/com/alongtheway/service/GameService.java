package com.alongtheway.service;

import java.util.Locale;

import com.alongtheway.action.Action;
import com.alongtheway.action.ActionFactory;
import com.alongtheway.config.GameConstants;
import com.alongtheway.domain.GameSession;
import com.alongtheway.parser.Command;
import com.alongtheway.parser.CommandParser;
import com.alongtheway.repository.SessionRepository;

public class GameService {

	private final CommandParser parser;
	private final ActionFactory actionFactory;
	private final SessionRepository sessionRepository;
	private final Narrator narrator;

	/**
	 * Creates the service.
	 *
	 * @param parser            turns the player's input into commands
	 * @param actionFactory     turns commands into actions
	 * @param sessionRepository stores and finds saved sessions
	 * @param narrator          composes the messages shown to the player
	 */
	public GameService(CommandParser parser, ActionFactory actionFactory, SessionRepository sessionRepository,
		Narrator narrator) {
		this.parser = parser;
		this.actionFactory = actionFactory;
		this.sessionRepository = sessionRepository;
		this.narrator = narrator;
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
