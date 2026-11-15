package com.alongtheway.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class CommandParserTest {
	@Test
	void testCommand() {
		CommandParser parser = new CommandParser();
		Command command = parser.parse("  Go   Ada about the garden  ");

		assertEquals(CommandVerb.MOVE, command.getVerb());
		assertEquals("Ada about the garden", command.getTarget());

		assertEquals(CommandVerb.MOVE, parser.parse("MOVE square").getVerb());
		assertEquals(CommandVerb.MOVE, parser.parse("Walk to square").getVerb());
		assertEquals(CommandVerb.MOVE, parser.parse("travel square").getVerb());

		assertEquals(CommandVerb.UNKNOWN, parser.parse("ask Ada").getVerb());
		assertEquals(CommandVerb.UNKNOWN, parser.parse("help Ada").getVerb());
	}

	@Test
	void testExamineCommand() {
		CommandParser parser = new CommandParser();
		Command command = parser.parse("  INSPECT old journal  ");

		assertEquals(CommandVerb.EXAMINE, command.getVerb());
		assertEquals("old journal", command.getTarget());

		assertEquals(CommandVerb.EXAMINE, parser.parse("look").getVerb());
		assertEquals("", parser.parse("look").getTarget());

		Command readCommand = parser.parse("read letter");
		assertEquals(CommandVerb.EXAMINE, readCommand.getVerb());
		assertEquals("letter", readCommand.getTarget());
	}

	@Test
	void testDropCommand() {
		CommandParser parser = new CommandParser();
		Command command = parser.parse("DROP old journal");

		assertEquals(CommandVerb.DROP, command.getVerb());
		assertEquals("old journal", command.getTarget());

		assertEquals(CommandVerb.DROP, parser.parse("put journal").getVerb());
		assertEquals(CommandVerb.DROP, parser.parse("throw journal").getVerb());

		assertEquals(CommandVerb.UNKNOWN, parser.parse("write journal").getVerb());
	}

	@Test
	void testEmptyInput() {
		CommandParser parser = new CommandParser();
		assertNull(parser.parse("  "));
		assertNull(parser.parse(null));
	}
}
