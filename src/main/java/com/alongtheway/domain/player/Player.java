
package com.alongtheway.domain.player;

import java.util.UUID;

import com.alongtheway.domain.world.Item;
import com.alongtheway.domain.world.Location;
import com.alongtheway.domain.narrative.Journal;

/**
 * Represents the player in the game.
 * Stores the player's basic information, current location,
 * inventory, and journal.
 */
public class Player {

    private String playerId;
    private String name;
    private Location location;
    private Inventory inventory;
    private Journal journal;

    /**
     * Creates a new player.
     *
     * @param playerId unique ID of the player
     * @param name player's name
     * @param location player's starting location
     * @param journal player's journal
     */
    public Player(String playerId, String name,
                  Location location, Journal journal) {

        this.playerId = playerId;
        this.name = name;
        this.location = location;
        this.inventory = new Inventory();
        this.journal = journal;
    }

    /**
     * Returns the player's ID.
     *
     * @return player ID
     */
    public String getPlayerId() {
        return playerId;
    }

    /**
     * Returns the player's name.
     *
     * @return player name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the player's current location.
     *
     * @return current location
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Updates the player's current location.
     *
     * @param location new location
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * Returns the player's inventory.
     *
     * @return player's inventory
     */
    public Inventory getInventory() {
        return inventory;
    }

    /**
     * Returns the player's journal.
     *
     * @return player's journal
     */
    public Journal getJournal() {
        return journal;
    }

    /**
     * Adds an item to the player's inventory.
     *
     * @param item item the player wants to take
     * @return true if the item was added, otherwise false
     */
    public boolean takeItem(Item item) {
        return inventory.addItem(item);
    }

    /**
     * Removes an item from the player's inventory.
     *
     * @param itemId UUID of the item to drop
     * @return the dropped item, or null if it was not found
     */
    public Item dropItem(UUID itemId) {
        return inventory.removeItem(itemId);
    }
}