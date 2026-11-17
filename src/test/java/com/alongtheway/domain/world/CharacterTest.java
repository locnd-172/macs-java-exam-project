package com.alongtheway.domain.world;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

class CharacterTest {

	@Test
	void testNameMatch() {
		Character character = new Character("Ada", "Bookshop owner", List.of("bookseller"));
		assertTrue(character.find("  ADA!!!  "));
		assertTrue(character.find("talk to Ada"));
		assertFalse(character.find("ad"));
		assertFalse(character.find("madam"));
	}

	@Test
	void testAliasMatch() {
		Character character = new Character("Ada", "Bookshop owner",
				List.of("BookShop   Owner!", "BOOKSELLER", "Keeper of the old shop"));
		assertTrue(character.find("  THE bookshop-owner!! "));
		assertTrue(character.find("bookseller"));
		assertTrue(character.find("talk to the keeper of the old shop"));
		assertTrue(character.find("OLD-SHOP"));
		assertFalse(character.find("booksellers"));
	}

	@Test
	void testPartialAlias() {
		Character character = new Character("Ada", "Bookshop owner", List.of("the shop owner"));
		assertTrue(character.find("shop owner"));
		assertTrue(character.find("talk to the shop owner"));
		assertFalse(character.find("shop own"));
	}

	@Test
	void testInvalidQuery() {
		Character character = new Character("Ada", "Bookshop owner", List.of("bookseller"));
		assertFalse(character.find("  "));
		assertFalse(character.find("!!!"));
		assertFalse(character.find("Thomas"));
	}
}
