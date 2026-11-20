package com.alongtheway.action.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.alongtheway.action.ActionFactory;
import com.alongtheway.config.GameConstants;
import com.alongtheway.domain.GameSession;
import com.alongtheway.domain.narrative.Journal;
import com.alongtheway.domain.player.Player;
import com.alongtheway.domain.world.Character;
import com.alongtheway.domain.world.Location;
import com.alongtheway.domain.world.World;
import com.alongtheway.parser.CommandParser;

class HelpActionTest {
	private World world;

	private Location garden;
	private Location cafe;

	private Player player;

	private Character thomas;


	private ActionFactory factory;
	private CommandParser parser;
	private GameSession session;


	@BeforeEach
	void setUp() {
		world = new World();

		garden = new Location("garden", "garden", "garden", List.of());
		cafe = new Location("cafe", "cafe", "cafe", List.of());

		thomas = new Character("Thomas", "gardener", List.of("gardener"));

		garden.addCharacter(thomas);
		cafe.addCharacter(new Character("Mara", "cafe owner", List.of("barista")));

		world.addLocations(List.of(garden, cafe));
		player = new Player("player01", "Test Player", garden, new Journal());

		factory = new ActionFactory();
		parser = new CommandParser();

		session = new GameSession(world, player);
	}

	@Test
	void testHelpCharacter() {
		factory.create(parser.parse("help to the gardener")).execute(session);
		assertEquals(
			String.format(GameConstants.Messages.HELP_RESPONSE, thomas.getName()),
			session.takeMessage()
		);
	}

	@Test
	void testUnknownCharacter() {
		factory.create(parser.parse("help Mara")).execute(session);
		assertEquals(GameConstants.Messages.HELP_CHARACTER_NOT_FOUND, session.takeMessage());
	}

	@Test
	void testMissingTarget() {
		factory.create(parser.parse("help")).execute(session);
		assertEquals(GameConstants.Messages.HELP_TARGET_REQUIRED, session.takeMessage());
	}
}
