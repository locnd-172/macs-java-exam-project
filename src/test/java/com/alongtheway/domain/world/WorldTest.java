package com.alongtheway.domain.world;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class WorldTest {
	private World world;
	private Location square;
	private Location cafe;
	private Location outsideWorld;
	private Location entrance;

	@BeforeEach
	void setUp() {
		world = new World();

		square = new Location("village square", "square description", List.of());
		cafe = new Location("café", "café description", List.of());
		outsideWorld = new Location("outside the village", "outside description", List.of());
		entrance = new Location("village entrance", "village entrance description", List.of());
	}

	@Test
	void testLocations() {
		world.addLocation(null);
		world.addLocation(square);
		world.addLocation(square);

		List<Location> locations = world.getLocations();

		assertEquals(1, locations.size());
		assertSame(square, locations.get(0));

		locations.clear();
		assertEquals(0, world.getLocations().size());
	}

	@Test
	void testStartingLocation() {
		world.addLocation(entrance);
		world.setStartingLocation(entrance);
		assertSame(entrance, world.getStartingLocation());
	}

	@Test
	void testLinks() {
		square.addLink(cafe);
		world.addLocation(square);
		world.addLocation(cafe);

		List<Location> squareLinks = world.getLinks(square);

		assertEquals(List.of(cafe), squareLinks);
		assertSame(square.getLinks(), squareLinks);
		assertTrue(world.getLinks(null).isEmpty());
		assertTrue(world.getLinks(outsideWorld).isEmpty());

		squareLinks.clear();
		assertFalse(square.hasLink(cafe));
	}
}
