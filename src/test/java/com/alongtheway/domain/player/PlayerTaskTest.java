
package com.alongtheway.domain.player;

import java.util.Arrays;

import com.alongtheway.domain.world.Item;
import com.alongtheway.domain.world.Location;
import com.alongtheway.domain.narrative.Journal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the Player, Inventory and Item classes.
 */
class PlayerTaskTest {

    private Location cottage;
    private Location cafe;
    private Journal journal;
    private Player player;
    private Item key;

    /**
     * Creates the objects needed for each test.
     */
    @BeforeEach
    void setUp() {

        cottage = new Location(
                "Cottage",
                "A small cottage in the village.",
                Arrays.asList("home")
        );

        cafe = new Location(
                "Little Corner Cafe",
                "A small cafe in the village.",
                Arrays.asList("coffee shop")
        );

        journal = new Journal();

        player = new Player(
                "player_01",
                "Test Player",
                cottage,
                journal
        );

        key = new Item(
                "Old Key",
                Arrays.asList("rusty key", "small key")
        );

        key.setDescription("An old rusty key.");
    }

    @Test
    void playerHasCorrectStartingLocation() {
        assertSame(cottage, player.getLocation());
    }

    @Test
    void playerLocationCanBeUpdated() {
        player.setLocation(cafe);

        assertSame(cafe, player.getLocation());
    }

    @Test
    void playerHasJournal() {
        assertSame(journal, player.getJournal());
    }

    @Test
    void itemStoresNameAndDescriptionCorrectly() {
        assertEquals("Old Key", key.getName());
        assertEquals("An old rusty key.", key.getDescription());
        assertEquals("An old rusty key.", key.examine());
    }

    @Test
    void itemCanBeFoundUsingAlias() {
        assertTrue(key.matches("rusty key"));
    }

    @Test
    void itemMatchingIgnoresCase() {
        assertTrue(key.matches("OLD KEY"));
    }

    @Test
    void playerCanTakeAnItem() {
        assertTrue(player.takeItem(key));
    }

    @Test
    void inventoryContainsAddedItem() {
        player.takeItem(key);

        assertTrue(player.getInventory().contains(key));
    }

    @Test
    void inventoryHasCorrectNumberOfItems() {
        player.takeItem(key);

        assertEquals(1, player.getInventory().size());
    }

    @Test
    void inventoryRejectsDuplicateItems() {
        player.takeItem(key);

        assertFalse(player.takeItem(key));
        assertEquals(1, player.getInventory().size());
    }
}