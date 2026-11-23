package com.alongtheway;

import java.util.Scanner;

import com.alongtheway.service.GameService;

public class GameConsole {

	private final Scanner input;
	private final GameService gameService;

	public GameConsole(Scanner input, GameService gameService) {
		this.input = input;
		this.gameService = gameService;
	}

	public void start() {
	}
}
