package com.alongtheway.domain.player;

import com.alongtheway.domain.world.Item;
import com.alongtheway.domain.world.Location;
import com.alongtheway.domain.narrative.Journal;

/**
 * Tests the Player, Inventory and Item classes.
 */
public class PlayerTaskTest {

    private static void check(boolean condition, String message) {
        if (!condition) {
            throw new AssertionError("FAILED: " + message);
        }

        System.out.println("PASSED: " + message);
    }

    public static void main(String[] args) {

        // Create two locations
        Location cottage = new Location();

        Location cafe = new Location();

        // Create a journal and player
        Journal journal = new Journal();

        Player player = new Player(
                "player_01",
                "Test Player",
                cottage,
                journal
        );

        // Test 1: Initial location
        check(
                player.getLocation() == cottage,
                "Player has the correct starting location"
        );

        // Test 2: Update location
        player.setLocation(cafe);

        check(
                player.getLocation() == cafe,
                "Player location can be updated"
        );

        // Create an item
        Item key = new Item(
                "item_01",
                "Old Key",
                "An old rusty key."
        );

        // Test 3: Item information
        check(
                key.getName().equals("Old Key")
                        && key.getItemId().equals("item_01")
                        && key.examine().equals("An old rusty key."),
                "Item stores its information correctly"
        );

        // Test 4: Add item to inventory
        check(
                player.takeItem(key),
                "Player can take an item"
        );

        // Test 5: Check inventory
        check(
                player.getInventory().contains(key),
                "Inventory contains the added item"
        );

        // Test 6: Check inventory size
        check(
                player.getInventory().size() == 1,
                "Inventory has the correct number of items"
        );

        // Test 7: Prevent duplicate item ID
        Item duplicateKey = new Item(
                "item_01",
                "Another Key",
                "A different description."
        );

        check(
                !player.takeItem(duplicateKey),
                "Inventory rejects a duplicate item ID"
        );

        // Test 8: Drop item
        Item droppedItem = player.dropItem("item_01");

        check(
                droppedItem == key,
                "Player can drop an item"
        );

        // Test 9: Check inventory after dropping
        check(
                !player.getInventory().contains(key),
                "Dropped item is no longer in inventory"
        );

        // Test 10: Drop nonexistent item
        check(
                player.dropItem("item_99") == null,
                "Dropping a nonexistent item returns null"
        );

        System.out.println("\nAll Player task tests completed.");
    }
}
