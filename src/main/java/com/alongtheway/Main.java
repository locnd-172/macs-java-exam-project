package com.alongtheway;

import java.nio.file.Path;
import java.util.Scanner;

import com.alongtheway.action.ActionFactory;
import com.alongtheway.config.GameConstants;
import com.alongtheway.domain.narrative.NarrativeContent;
import com.alongtheway.parser.CommandParser;
import com.alongtheway.repository.FileSessionRepository;
import com.alongtheway.repository.SessionRepository;
import com.alongtheway.service.GameService;
import com.alongtheway.service.Narrator;

public class Main {

	public static void main(String[] args) {
		SessionRepository sessionRepository = new FileSessionRepository(
			Path.of(GameConstants.Defaults.SAVE_DIRECTORY)
		);

		Narrator narrator = new Narrator(new NarrativeContent());
		GameService gameService = new GameService(
			new CommandParser(),
			new ActionFactory(),
			sessionRepository,
			narrator
		);

		new Game(new Scanner(System.in), gameService).start();
	}
}
