package com.alongtheway;

import java.util.Scanner;

import com.alongtheway.service.GameService;

public class Game {

	private final Scanner input;
	private final GameService gameService;

	public Game(Scanner input, GameService gameService) {
		this.input = input;
		this.gameService = gameService;
	}

	public void start() {
	}
}
