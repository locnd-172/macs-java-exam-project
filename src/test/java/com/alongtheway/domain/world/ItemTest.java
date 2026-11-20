package com.alongtheway.domain.world;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

class ItemTest {

	@Test
	void testNameMatch() {
		Item item = new Item("Old photograph", List.of("picture", "photo"));
		assertTrue(item.match("old photograph"));
		assertTrue(item.match("photograph"));
	}

	@Test
	void testAliasMatch() {
		Item item = new Item(
			"Old photograph",
			List.of("Picture", "PHOTO", "Family-Snapshot")
		);
		assertTrue(item.match("PHOTO"));
		assertTrue(item.match("FAMILY SNAPSHOT"));
		assertTrue(item.match("picture"));
		assertFalse(item.match("keepsake"));
	}

	@Test
	void testInvalidQuery() {
		Item item = new Item("Old photograph", List.of("picture"));
		assertFalse(item.match(" "));
		assertFalse(item.match("!!!"));
		assertFalse(item.match("journal"));
	}

	@Test
	void testWholeWordMatch() {
		Item item = new Item("old book", List.of("book", "novel"));

		assertTrue(item.match("read the book"));
		assertFalse(item.match("notebook"));
	}

	@Test
	void testNameAndDescription() {
		Item item = new Item("Old Photograph", List.of("picture"));
		item.setDescription("Two children stand on the bridge.");
		item.setName("Bridge Photograph");
		assertEquals("Bridge Photograph", item.getName());
		assertEquals("Two children stand on the bridge.", item.getDescription());
		assertEquals("Two children stand on the bridge.", item.examine());
	}
}
