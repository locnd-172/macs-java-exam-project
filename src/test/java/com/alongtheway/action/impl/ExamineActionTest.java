package com.alongtheway.action.impl;

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

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExamineActionTest {
	private World world;
	private Location bookshop;
	private Player player;

	private Item book;
	private Item photo;

	private ActionFactory factory;
	private CommandParser parser;
	private GameSession session;

	@BeforeEach
	void setUp() {
		world = new World();

		bookshop = new Location("garden", "garden", "garden description", List.of());
		world.addLocations(List.of(bookshop));

		book = new Item("old book", List.of("book"));
		photo = new Item("photo", List.of("picture"));

		bookshop.addItem(book);
		bookshop.addItem(photo);

		player = new Player("player01", "Test Player", bookshop, new Journal());

		session = new GameSession(world, player);
		factory = new ActionFactory();
		parser = new CommandParser();
	}

	@Test
	void testExamine() {
		factory.create(parser.parse("look at the photo")).execute(session);
		assertEquals(
			String.format(GameConstants.Messages.EXAMINE_RESPONSE, photo.getName()),
			session.takeMessage()
		);

		factory.create(parser.parse("read the book")).execute(session);
		assertEquals(
			String.format(GameConstants.Messages.EXAMINE_RESPONSE, book.getName()),
			session.takeMessage()
		);
	}

	@Test
	void testExamineItemNotFound() {
		factory.create(parser.parse("view the bag")).execute(session);
		assertEquals(GameConstants.Messages.EXAMINE_ITEM_NOT_FOUND, session.takeMessage());

		factory.create(parser.parse("inspect")).execute(session);
		assertEquals(GameConstants.Messages.EXAMINE_TARGET_REQUIRED, session.takeMessage());
	}
}
