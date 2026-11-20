package com.alongtheway.action.impl;

import com.alongtheway.action.ActionFactory;
import com.alongtheway.config.GameConstants;
import com.alongtheway.domain.GameSession;
import com.alongtheway.domain.narrative.Journal;
import com.alongtheway.domain.player.Player;
import com.alongtheway.domain.world.Character;
import com.alongtheway.domain.world.Item;
import com.alongtheway.domain.world.Location;
import com.alongtheway.domain.world.World;
import com.alongtheway.parser.CommandParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExamineActionTest {
	private World world;
	private Location bookshop;
	private Player player;

	private Item book;
	private Item photo;
	private Item journal;
	private Character ada;

	private ActionFactory factory;
	private CommandParser parser;
	private GameSession session;

	@BeforeEach
	void setUp() {
		world = new World();

		bookshop = new Location("bookshop", "bookshop", "A cozy bookshop filled with old books.", List.of());
		world.addLocations(List.of(bookshop));

		book = new Item("old book", List.of("book"));
		photo = new Item("photo", List.of("picture"));
		journal = new Item("journal", List.of("notebook"));

		ada = new Character("Ada", "The bookseller is arranging the shelves.", List.of("bookseller"));

		book.setDescription("Its pages are marked with notes.");
		photo.setDescription("A picture shows the old village bridge.");
		journal.setDescription("Precious memories.");

		bookshop.addItem(book);
		bookshop.addItem(photo);
		bookshop.addCharacter(ada);

		player = new Player("player01", "Test Player", bookshop, new Journal());
		player.takeItem(journal);

		session = new GameSession(world, player);
		factory = new ActionFactory();
		parser = new CommandParser();
	}

	@Test
	void testExamineLocationItem() {
		factory.create(parser.parse("look at the photo")).execute(session);
		assertEquals(photo.examine(), session.takeMessage());

		factory.create(parser.parse("read the book")).execute(session);
		assertEquals(book.examine(), session.takeMessage());
	}

	@Test
	void testExamineInventoryItem() {
		factory.create(parser.parse("inspect notebook")).execute(session);
		assertEquals(journal.examine(), session.takeMessage());
	}

	@Test
	void testExamineCharacter() {
		factory.create(parser.parse("look at the bookseller")).execute(session);
		assertEquals(ada.getDescription(), session.takeMessage());
	}

	@Test
	void testExamineLocation() {
		factory.create(parser.parse("look around")).execute(session);
		assertEquals(bookshop.getDescription(), session.takeMessage());
	}

	@Test
	void testMissingDescription() {
		bookshop.addItem(new Item("note", List.of()));
		factory.create(parser.parse("inspect note")).execute(session);
		assertEquals(GameConstants.Messages.EXAMINE_DESCRIPTION_UNAVAILABLE, session.takeMessage());
	}

	@Test
	void testTargetNotFound() {
		factory.create(parser.parse("inspect")).execute(session);
		assertEquals(GameConstants.Messages.EXAMINE_TARGET_REQUIRED, session.takeMessage());

		factory.create(parser.parse("view the bag")).execute(session);
		assertEquals(GameConstants.Messages.EXAMINE_TARGET_NOT_FOUND, session.takeMessage());
	}
}
