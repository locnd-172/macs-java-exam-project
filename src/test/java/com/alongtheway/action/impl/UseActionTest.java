package com.alongtheway.action.impl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.alongtheway.action.ActionFactory;
import com.alongtheway.config.GameConstants;
import com.alongtheway.domain.GameSession;
import com.alongtheway.domain.narrative.Journal;
import com.alongtheway.domain.player.Player;
import com.alongtheway.domain.world.Item;
import com.alongtheway.domain.world.Location;
import com.alongtheway.domain.world.World;
import com.alongtheway.parser.CommandParser;

class UseActionTest {
	private GameSession session;
	private ActionFactory factory;
	private CommandParser parser;
	private Item photograph;
	private Item camera;

	@BeforeEach
	void setUp() {
		World world = new World();
		Location bridge = new Location("bridge", "bridge", "old bridge", List.of());
		world.addLocations(List.of(bridge));

		Player player = new Player("player01", "Test Player", bridge, new Journal());
		photograph = new Item("old photograph", List.of("picture", "photo"));
		camera = new Item("camera", List.of("camera"));

		player.takeItem(photograph);
		player.takeItem(camera);

		session = new GameSession(world, player);
		factory = new ActionFactory();
		parser = new CommandParser();
	}

	@Test
	void testUseItem() {
		factory.create(parser.parse("use camera")).execute(session);
		assertEquals(
			String.format(GameConstants.Messages.USE_RESPONSE, camera.getName()),
			session.takeMessage()
		);

		factory.create(parser.parse("try photo")).execute(session);
		assertEquals(
			String.format(GameConstants.Messages.USE_RESPONSE, photograph.getName()),
			session.takeMessage()
		);

		assertTrue(session.getPlayer().getInventory().contains(camera));
		assertTrue(session.getPlayer().getInventory().contains(photograph));
	}

	@Test
	void testMissingItem() {
		factory.create(parser.parse("use journal")).execute(session);
		assertEquals(GameConstants.Messages.USE_ITEM_NOT_FOUND, session.takeMessage());
	}

	@Test
	void testMissingTarget() {
		factory.create(parser.parse("use")).execute(session);
		assertEquals(GameConstants.Messages.USE_TARGET_REQUIRED, session.takeMessage());
	}
}
