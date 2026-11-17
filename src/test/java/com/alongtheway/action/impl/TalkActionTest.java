package com.alongtheway.action.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Locale;

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

class TalkActionTest {
	private GameSession session;
	private ActionFactory factory;
	private CommandParser parser;

	@BeforeEach
	void setUp() {
		World world = new World();

		Location bookshop = new Location("bookshop", "bookshop", "quiet shop", List.of());
		Location cafe = new Location("cafe", "cafe", "small cafe", List.of());
		bookshop.addCharacter(new Character("Jules", "visitor", List.of("local")));
		bookshop.addCharacter(new Character("Ada", "bookseller", List.of("bookshop owner")));
		bookshop.addCharacter(new Character("Tom", "visitor", List.of()));
		cafe.addCharacter(new Character("Mara", "cafe owner", List.of("barista")));

		world.addLocations(List.of(bookshop, cafe));

		Player player = new Player("player01", "Test Player", bookshop, new Journal());

		session = new GameSession(world, player);
		factory = new ActionFactory();
		parser = new CommandParser();
	}

	@Test
	void testTalkToCharacter() {
		factory.create(parser.parse("talk to Ada")).execute(session);
		assertEquals(
			String.format(GameConstants.Messages.TALK_RESPONSE, "Ada"),
			session.takeMessage()
		);

		factory.create(parser.parse("speak with bookshop owner")).execute(session);
		assertEquals(
			String.format(GameConstants.Messages.TALK_RESPONSE, "Ada"),
			session.takeMessage()
		);
	}


	@Test
	void testAskAboutTopic() {
		factory.create(parser.parse("ask Ada about the journal")).execute(session);
		assertEquals(
			String.format(GameConstants.Messages.TALK_RESPONSE_WITH_TOPIC, "Ada", "the journal"),
			session.takeMessage()
		);

		factory.create(parser.parse("ask about the journal")).execute(session);
		assertEquals(
			String.format(GameConstants.Messages.TALK_RESPONSE_WITH_TOPIC_NO_CHARACTER, "the journal"),
			session.takeMessage()
		);


		factory.create(parser.parse("ask about a letter to Ada")).execute(session);
		assertEquals(
			String.format(GameConstants.Messages.TALK_RESPONSE_WITH_TOPIC_NO_CHARACTER, "a letter to Ada"),
			session.takeMessage()
		);
	}


	@Test
	void testAskNoTopic() {
		factory.create(parser.parse("ask about")).execute(session);
		assertEquals(GameConstants.Messages.TALK_GENERAL_RESPONSE, session.takeMessage());
	}

	@Test
	void testTopicIsCharacter() {
		factory.create(parser.parse("ask Ada about Jules")).execute(session);
		assertEquals(
			String.format(GameConstants.Messages.TALK_RESPONSE_WITH_TOPIC, "Ada", "Jules"),
			session.takeMessage()
		);
	}

	@Test
	void testUnknownCharacter() {
		factory.create(parser.parse("talk to Bob")).execute(session);
		assertEquals(GameConstants.Messages.TALK_CHARACTER_NOT_FOUND, session.takeMessage());
	}

}
