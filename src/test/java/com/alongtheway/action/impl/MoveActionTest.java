package com.alongtheway.action.impl;

import com.alongtheway.action.Action;
import com.alongtheway.action.ActionFactory;
import com.alongtheway.config.GameConstants;
import com.alongtheway.domain.GameSession;
import com.alongtheway.domain.narrative.Journal;
import com.alongtheway.domain.player.Player;
import com.alongtheway.domain.world.Direction;
import com.alongtheway.domain.world.Location;
import com.alongtheway.domain.world.World;
import com.alongtheway.parser.Command;
import com.alongtheway.parser.CommandParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

public class MoveActionTest {

	private World world;
	private Location square;
	private Location cafe;
	private Location bookshop;
	private Location garden;
	private Location entrance;
	private Player player;
	private Journal journal;

	private ActionFactory actionFactory;
	private CommandParser parser;
	private GameSession session;

	@BeforeEach
	void setUp() {
		world = new World();

		entrance = new Location("entrance", "village entrance", "village entrance description", List.of());
		square = new Location("village-square", "village square", "village square description", List.of("square"));
		cafe = new Location("cafe", "café", "café description", List.of());
		bookshop = new Location("old-bookshop", "old bookshop", "old bookshop description", List.of());
		garden = new Location("garden", "garden", "garden description", List.of());

		entrance.addLink(Direction.NORTH, square);
		square.addLink(Direction.EAST, cafe);
		cafe.addLink(Direction.EAST, bookshop);
		cafe.addLink(Direction.NORTH, garden);

		world.addLocations(List.of(entrance, square, cafe, bookshop, garden));

		journal = new Journal();
		player = new Player("player01", "Test Player", entrance, journal);

		actionFactory = new ActionFactory();
		parser = new CommandParser();
		session = new GameSession(world, player);
	}

	private void go(String input) {
		actionFactory.create(parser.parse(input)).execute(session);
	}

	@Test
	void testMoveToLinkedLocation() {
		Command command = parser.parse("head to the square");
		Action action = actionFactory.create(command);
		action.execute(session);

		assertSame(square, player.getLocation());
		assertEquals(
			String.format(GameConstants.Messages.MOVE_ARRIVAL, square.getName()),
			session.takeMessage()
		);
	}

	@Test
	void testMoveToUnlinkedLocation() {
		Action action = actionFactory.create(parser.parse("visit the garden"));
		action.execute(session);

		assertSame(entrance, player.getLocation());
		assertEquals(GameConstants.Messages.MOVE_BLOCKED, session.takeMessage());
	}

	@Test
	void testMoveToCurrentLocation() {
		Action action = actionFactory.create(parser.parse("go village entrance"));
		action.execute(session);

		assertSame(entrance, player.getLocation());
		assertEquals(GameConstants.Messages.MOVE_ALREADY_THERE, session.takeMessage());
	}

	@Test
	void testMoveByDirection() {
		// direction with an exit
		go("go north");
		assertSame(square, player.getLocation());
		assertEquals(
			String.format(GameConstants.Messages.MOVE_ARRIVAL, square.getName()),
			session.takeMessage()
		);

		// filler words are ignored
		go("walk to the east");
		assertSame(cafe, player.getLocation());

		// direction without an exit
		go("go south");
		assertSame(cafe, player.getLocation());
		assertEquals(GameConstants.Messages.MOVE_UNKNOWN, session.takeMessage());

		// missing target
		go("go");
		assertSame(cafe, player.getLocation());
		assertEquals(GameConstants.Messages.MOVE_UNKNOWN, session.takeMessage());
	}

	@Test
	void testMoveToUnknownLocation() {
		Action action = actionFactory.create(parser.parse("go lighthouse"));
		action.execute(session);

		assertSame(entrance, player.getLocation());
		assertEquals(GameConstants.Messages.MOVE_UNKNOWN, session.takeMessage());
	}
}
