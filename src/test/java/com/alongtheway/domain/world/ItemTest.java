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
		assertTrue(item.matches("old photograph"));
		assertTrue(item.matches("photograph"));
	}

	@Test
	void testAliasMatch() {
		Item item = new Item(
			"Old photograph",
			List.of("Picture", "PHOTO", "Family-Snapshot")
		);
		assertTrue(item.matches("PHOTO"));
		assertTrue(item.matches("FAMILY SNAPSHOT"));
		assertTrue(item.matches("picture"));
		assertFalse(item.matches("keepsake"));
	}

	@Test
	void testInvalidQuery() {
		Item item = new Item("Old photograph", List.of("picture"));
		assertFalse(item.matches(" "));
		assertFalse(item.matches("!!!"));
		assertFalse(item.matches("journal"));
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
