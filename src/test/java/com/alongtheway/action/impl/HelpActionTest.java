package com.alongtheway.action.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.alongtheway.action.ActionFactory;
import com.alongtheway.config.GameConstants;
import com.alongtheway.domain.GameSession;
import com.alongtheway.domain.narrative.Journal;
import com.alongtheway.domain.player.Player;
import com.alongtheway.domain.world.Location;
import com.alongtheway.domain.world.World;
import com.alongtheway.parser.CommandParser;

class HelpActionTest {

	@Test
	void testListsCommands() {
		Location square = new Location("square", "village square", null, List.of());
		World world = new World();
		world.addLocation(square);
		GameSession session = new GameSession(world, new Player("player01", "Test Player", square, new Journal()));

		new ActionFactory().create(new CommandParser().parse("help")).execute(session);

		assertEquals(GameConstants.Messages.HELP_COMMANDS, session.takeMessage());
	}
}
