package com.alongtheway.domain.world;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LocationTest {
	private Location square;
	private Location cafe;
	private Location bookshop;

	@BeforeEach
	void setUp() {
		square = new Location("village-square", "village square", "village square description", List.of());
		cafe = new Location("cafe", "café", "café description", List.of());
		bookshop = new Location("old-bookshop", "old bookshop", "old bookshop description", List.of());
	}

	@Test
	void testLinks() {
		square.addLink(null, cafe);
		square.addLink(Direction.EAST, null);
		square.addLink(Direction.EAST, square);
		square.addLink(Direction.EAST, cafe);
		square.addLink(Direction.NORTH, cafe);

		assertTrue(square.hasLink(cafe));
		assertFalse(square.hasLink(square));
		assertEquals(List.of(cafe), square.getLinks());

		square.removeLink(cafe);
		assertFalse(square.hasLink(cafe));
		assertNull(square.getLink(Direction.EAST));
		assertNull(square.getLink(Direction.NORTH));
	}

	@Test
	void testLinkByDirection() {
		square.addLink(Direction.NORTH, cafe);
		square.addLink(Direction.SOUTHWEST, bookshop);

		assertSame(cafe, square.getLink(Direction.NORTH));
		assertSame(bookshop, square.getLink(Direction.SOUTHWEST));
		assertNull(square.getLink(Direction.EAST));
		assertNull(square.getLink(null));
	}

	@Test
	void testOneDirectionOneLink() {
		square.addLink(Direction.EAST, cafe);
		square.addLink(Direction.EAST, bookshop);

		assertSame(bookshop, square.getLink(Direction.EAST));
		assertFalse(square.hasLink(cafe));
	}

	@Test
	void testCharacterAndItemLookup() {
		Character ada = new Character("Ada", "bookshop owner", List.of("bookseller"));
		Item journal = new Item("village journal", List.of("shared journal"));

		bookshop.addCharacter(ada);
		bookshop.addItem(journal);

		assertSame(ada, bookshop.findCharacter("bookseller"));
		assertSame(journal, bookshop.findItem("shared journal"));

		assertNull(bookshop.findCharacter("Thomas"));
		assertNull(bookshop.findItem("photograph"));
	}
}
