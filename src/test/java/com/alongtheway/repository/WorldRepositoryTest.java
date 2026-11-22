package com.alongtheway.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.alongtheway.config.GameConstants;
import com.alongtheway.domain.world.Character;
import com.alongtheway.domain.world.Item;
import com.alongtheway.domain.world.Location;
import com.alongtheway.domain.world.World;

class WorldRepositoryTest {

    private World world;

    @BeforeEach
    void setUp() throws IOException {
        world = new WorldRepository().load();
    }

    @Test
    void loadsLocations() {
        assertEquals(
            Set.of("village-entrance", "village-square", "cafe", "old-bookshop", "garden"),
            world.getLocations().keySet()
        );

        Location cafe = world.getLocationById("cafe");
        assertEquals("cafe", cafe.getLocationId());
        assertEquals("Every Half Café", cafe.getName());

        Location bookshop = world.findLocation("bookshop");
        assertSame(bookshop, world.findLocation("bookstore"));
        assertEquals("old-bookshop", bookshop.getLocationId());
        assertEquals("The Bookshop", bookshop.getName());

        assertSame(
            world.getLocationById(GameConstants.Defaults.STARTING_LOCATION_ID),
            world.getStartingLocation()
        );
    }

    @Test
    void loadsCharacters() {
        Location cafe = world.getLocationById("cafe");
        Character emma = cafe.findCharacter("Emma");

        assertNotNull(emma);
        assertEquals("emma", emma.getCharacterId());
        assertEquals("Emma", emma.getName());

        assertSame(emma, cafe.findCharacter("coffee shop owner"));

        Location garden = world.getLocationById("garden");

        assertNull(garden.findCharacter("Emma"));
        assertNotNull(garden.findCharacter("Liam"));
    }

    @Test
    void loadsItems() {
        Location bookshop = world.getLocationById("old-bookshop");
        Item journal = bookshop.findItem("journal");

        assertNotNull(journal);
        assertEquals("old-journal", journal.getItemId());
        assertEquals("The Mysterious Journal", journal.getName());

        Location cafe = world.getLocationById("cafe");
        Location square = world.getLocationById("village-square");

        assertNull(cafe.findItem("journal"));
        assertNotNull(square.findItem("notice board"));
    }

    @Test
    void linksLocations() {
        Location entrance = world.getLocationById("village-entrance");
        Location square = world.getLocationById("village-square");
        Location bookshop = world.getLocationById("old-bookshop");
        Location garden = world.getLocationById("garden");

        assertTrue(entrance.hasLink(square));
        assertTrue(square.hasLink(entrance));

        assertTrue(bookshop.hasLink(garden));
        assertTrue(garden.hasLink(bookshop));

        assertEquals(4, square.getLinks().size());

        assertFalse(entrance.hasLink(garden));
    }
}
