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
		assertTrue(character.find("ad"));
	}

	@Test
	void testAliasMatch() {
		Character character = new Character("Ada", "Bookshop owner",
				List.of("BookShop   Owner!", "BOOKSELLER", "Keeper of the old shop"));
		assertTrue(character.find("  THE bookshop-owner!! "));
		assertTrue(character.find("bookseller"));
		assertTrue(character.find("OLD-SHOP"));
	}

	@Test
	void testInvalidQuery() {
		Character character = new Character("Ada", "Bookshop owner", List.of("bookseller"));
		assertFalse(character.find("  "));
		assertFalse(character.find("!!!"));
		assertFalse(character.find("Thomas"));
	}
}
