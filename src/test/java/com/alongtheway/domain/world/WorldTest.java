package com.alongtheway.domain.world;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Map;

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

		square = new Location("village-square", "village square", "square description", List.of("town centre"));
		cafe = new Location("cafe", "café", "café description", List.of());
		outsideWorld = new Location("outside", "outside the village", "outside description", List.of());
		entrance = new Location("entrance", "village entrance", "village entrance description", List.of());
	}

	@Test
	void testLocations() {
		world.addLocation(null);
		world.addLocation(square);
		world.addLocation(square);
		world.addLocation(new Location("village-square", "another square", "", List.of()));

		Map<String, Location> locations = world.getLocations();

		assertEquals(1, locations.size());
		assertSame(square, locations.get("village-square"));
		assertSame(square, world.getLocationById("village-square"));
		assertNull(world.getLocationById("missing"));

		locations.clear();
		assertEquals(0, world.getLocations().size());
	}

	@Test
	void testFindLocation() {
		world.addLocation(square);
		world.addLocation(cafe);

		assertSame(square, world.findLocation("VILLAGE SQUARE"));
		assertSame(square, world.findLocation("  TOWN-CENTRE! "));
		assertNull(world.findLocation("unknown"));
		assertNull(world.findLocation("!!!"));
		assertNull(world.findLocation(null));
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
