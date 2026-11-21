
package com.alongtheway.action.impl;

import com.alongtheway.action.Action;
import com.alongtheway.action.ActionFactory;
import com.alongtheway.config.GameConstants;
import com.alongtheway.domain.GameSession;
import com.alongtheway.domain.narrative.Journal;
import com.alongtheway.domain.narrative.JournalEntry;
import com.alongtheway.domain.player.Player;
import com.alongtheway.domain.world.Location;
import com.alongtheway.domain.world.World;
import com.alongtheway.parser.Command;
import com.alongtheway.parser.CommandParser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JournalActionTest {

	private Journal journal;
	private Player player;
	private ActionFactory actionFactory;
	private CommandParser parser;
	private GameSession session;

	@BeforeEach
	void setUp() {
		World world = new World();

		Location location = new Location(
			"entrance",
			"village entrance",
			"village entrance description",
			java.util.List.of()
		);

		journal = new Journal();
		player = new Player("player01", "Test Player", location, journal);

		actionFactory = new ActionFactory();
		parser = new CommandParser();

		session = new GameSession(world, player);
	}

	@Test
	void testDisplayJournalEntries() {

		journal.addEntry(new JournalEntry(
			"Cafe Owner",
			"The old bookshop contains an important secret."
		));

		journal.addEntry(new JournalEntry(
			"Shopkeeper",
			"You should visit the village square."
		));

		Command command = parser.parse("journal");
		Action action = actionFactory.create(command);

		assertNotNull(action);

		action.execute(session);

		String message = session.takeMessage();

		assertTrue(message.contains("Cafe Owner"));
		assertTrue(message.contains("The old bookshop contains an important secret."));
		assertTrue(message.contains("Shopkeeper"));
		assertTrue(message.contains("You should visit the village square."));
	}

	@Test
	void testDisplayEmptyJournal() {

		Command command = parser.parse("journal");
		Action action = actionFactory.create(command);

		assertNotNull(action);

		action.execute(session);

		assertEquals(
			GameConstants.Journal.EMPTY_MESSAGE,
			session.takeMessage()
		);
	}

	@Test
	void testReviewJournalDoesNotRemoveEntries() {

		journal.addEntry(new JournalEntry(
			"Cafe Owner",
			"A discovered memory."
		));

		Command command = parser.parse("journal");
		Action action = actionFactory.create(command);

		action.execute(session);

		assertEquals(1, journal.size());
	}
}
