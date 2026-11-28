package com.alongtheway.domain.world;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class DirectionTest {

	@Test
	void testFromText() {
		assertEquals(Direction.NORTH, Direction.fromText("north"));
		assertEquals(Direction.WEST, Direction.fromText("W!"));

		assertEquals(Direction.SOUTHEAST, Direction.fromText("South-East"));
		assertEquals(Direction.SOUTHEAST, Direction.fromText("se"));

		assertEquals(Direction.EAST, Direction.fromText("towards the east"));
		assertEquals(Direction.NORTHEAST, Direction.fromText("to the north east"));
	}

	@Test
	void testOpposite() {
		assertEquals(Direction.SOUTH, Direction.NORTH.opposite());
		assertEquals(Direction.WEST, Direction.EAST.opposite());
		assertEquals(Direction.SOUTHWEST, Direction.NORTHEAST.opposite());
		assertEquals(Direction.NORTHWEST, Direction.SOUTHEAST.opposite());
	}

	@Test
	void testFalseDirection() {
		assertNull(Direction.fromText(null));
		assertNull(Direction.fromText(""));
		assertNull(Direction.fromText("cafe"));
		assertNull(Direction.fromText("north then east"));
		assertNull(Direction.fromText("ahead"));
	}
}
