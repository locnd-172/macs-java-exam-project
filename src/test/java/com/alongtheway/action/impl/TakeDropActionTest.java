
package com.alongtheway.action.impl;

import com.alongtheway.action.Action;
import com.alongtheway.action.ActionFactory;
import com.alongtheway.config.GameConstants;
import com.alongtheway.domain.GameSession;
import com.alongtheway.domain.narrative.Journal;
import com.alongtheway.domain.player.Player;
import com.alongtheway.domain.world.Item;
import com.alongtheway.domain.world.Location;
import com.alongtheway.domain.world.World;
import com.alongtheway.parser.CommandParser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TakeDropActionTest {

	private World world;
	private Location square;
	private Location cafe;
	private Player player;
	private Journal journal;

	private Item key;
	private Item book;

	private ActionFactory actionFactory;
	private CommandParser parser;
	private GameSession session;

	@BeforeEach
	void setUp() {

		world = new World();

		square = new Location(
			"village-square",
			"village square",
			"village square description",
			List.of("square")
		);

		cafe = new Location(
			"cafe",
			"café",
			"café description",
			List.of()
		);

		square.addLink(cafe);

		world.addLocations(List.of(square, cafe));

		// Create items.
		key = new Item("Old Key", List.of("rusty key", "small key"));
		key.setDescription("An old rusty key.");

		book = new Item("Old Book", List.of("book"));
		book.setDescription("An old book.");

		// Place items in the village square.
		square.addItem(key);
		square.addItem(book);

		journal = new Journal();

		player = new Player(
			"player01",
			"Test Player",
			square,
			journal
		);

		actionFactory = new ActionFactory();
		parser = new CommandParser();

		session = new GameSession(world, player);
	}

	// ---------------- TAKE ACTION TESTS ----------------

	@Test
	void testTakeItemSuccessfully() {

		Action action = actionFactory.create(parser.parse("take old key"));
		action.execute(session);

		assertTrue(player.getInventory().contains(key));
		assertNull(square.findItem("old key"));

		assertEquals(
			String.format(GameConstants.Messages.TAKE_SUCCESS, key.getName()),
			session.takeMessage()
		);
	}

	@Test
	void testTakeItemUsingAlias() {

		Action action = actionFactory.create(parser.parse("take rusty key"));
		action.execute(session);

		assertTrue(player.getInventory().contains(key));
		assertNull(square.findItem("rusty key"));

		assertEquals(
			String.format(GameConstants.Messages.TAKE_SUCCESS, key.getName()),
			session.takeMessage()
		);
	}

	@Test
	void testTakeItemNotAvailableAtLocation() {

		Action action = actionFactory.create(parser.parse("take phone"));
		action.execute(session);

		assertFalse(player.getInventory().contains(key));

		assertEquals(
			GameConstants.Messages.TAKE_ITEM_NOT_FOUND,
			session.takeMessage()
		);
	}

	@Test
	void testTakeWithoutItemName() {

		Action action = actionFactory.create(parser.parse("take"));
		action.execute(session);

		assertTrue(square.findItem("old key") != null);

		assertEquals(
			GameConstants.Messages.TAKE_TARGET_REQUIRED,
			session.takeMessage()
		);
	}

	// ---------------- DROP ACTION TESTS ----------------

	@Test
	void testDropItemSuccessfully() {

		player.takeItem(key);

		Action action = actionFactory.create(parser.parse("drop old key"));
		action.execute(session);

		assertFalse(player.getInventory().contains(key));
		assertSame(key, square.findItem("old key"));

		assertEquals(
			String.format(GameConstants.Messages.DROP_SUCCESS, key.getName()),
			session.takeMessage()
		);
	}

	@Test
	void testDropItemUsingAlias() {

		player.takeItem(key);

		Action action = actionFactory.create(parser.parse("drop rusty key"));
		action.execute(session);

		assertFalse(player.getInventory().contains(key));
		assertSame(key, square.findItem("rusty key"));

		assertEquals(
			String.format(GameConstants.Messages.DROP_SUCCESS, key.getName()),
			session.takeMessage()
		);
	}

	@Test
	void testDropItemNotInInventory() {

		Action action = actionFactory.create(parser.parse("drop phone"));
		action.execute(session);

		assertFalse(player.getInventory().contains(key));

		assertEquals(
			GameConstants.Messages.DROP_ITEM_NOT_FOUND,
			session.takeMessage()
		);
	}

	@Test
	void testDropWithoutItemName() {

		Action action = actionFactory.create(parser.parse("drop"));
		action.execute(session);

		assertEquals(
			GameConstants.Messages.DROP_TARGET_REQUIRED,
			session.takeMessage()
		);
	}

	// ---------------- PICKUP AND DROP INTEGRATION TEST ----------------

	@Test
	void testTakeAndDropSameItem() {

		// First, pick up the key.
		Action takeAction = actionFactory.create(parser.parse("take old key"));
		takeAction.execute(session);

		assertTrue(player.getInventory().contains(key));
		assertNull(square.findItem("old key"));

		session.takeMessage();

		// Then, drop the same key.
		Action dropAction = actionFactory.create(parser.parse("drop old key"));
		dropAction.execute(session);

		assertFalse(player.getInventory().contains(key));
		assertSame(key, square.findItem("old key"));

		assertEquals(
			String.format(GameConstants.Messages.DROP_SUCCESS, key.getName()),
			session.takeMessage()
		);
	}
}
