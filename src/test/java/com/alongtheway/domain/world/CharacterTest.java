package com.alongtheway.domain.world;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

class CharacterTest {

	@Test
	void testNameMatch() {
		Character character = new Character("Ada", "Bookshop owner", List.of("bookseller"));
		assertTrue(character.match("  ADA!!!  "));
		assertTrue(character.match("talk to Ada"));
		assertFalse(character.match("ad"));
		assertFalse(character.match("madam"));
	}

	@Test
	void testAliasMatch() {
		Character character = new Character("Ada", "Bookshop owner",
				List.of("BookShop   Owner!", "BOOKSELLER", "Keeper of the old shop"));
		assertTrue(character.match("  THE bookshop-owner!! "));
		assertTrue(character.match("bookseller"));
		assertTrue(character.match("talk to the keeper of the old shop"));
		assertTrue(character.match("OLD-SHOP"));
		assertFalse(character.match("booksellers"));
	}

	@Test
	void testPartialAlias() {
		Character character = new Character("Ada", "Bookshop owner", List.of("the shop owner"));
		assertTrue(character.match("shop owner"));
		assertTrue(character.match("talk to the shop owner"));
		assertFalse(character.match("shop own"));
	}

	@Test
	void testInvalidQuery() {
		Character character = new Character("Ada", "Bookshop owner", List.of("bookseller"));
		assertFalse(character.match("  "));
		assertFalse(character.match("!!!"));
		assertFalse(character.match("Thomas"));
	}
}
