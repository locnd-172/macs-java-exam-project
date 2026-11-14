package com.alongtheway.domain.player;

import com.alongtheway.domain.world.Item;
import java.util.ArrayList;
import java.util.List;

/**
 * Stores and manages the items held by the player.
 */
public class Inventory {

    private List<Item> items;

    /**
     * Creates an empty inventory.
     */
    public Inventory() {
        items = new ArrayList<>();
    }

    /**
     * Adds an item to the inventory.
     *
     * @param item item to add
     * @return true if the item was added, otherwise false
     */
    public boolean addItem(Item item) {

        if (item == null || contains(item.getItemId())) {
            return false;
        }

        items.add(item);
        return true;
    }

    /**
     * Removes an item from the inventory.
     *
     * @param item item to remove
     * @return true if the item was removed, otherwise false
     */
    public boolean removeItem(Item item) {

        if (item == null) {
            return false;
        }

        return removeItem(item.getItemId()) != null;
    }

    /**
     * Removes an item using its ID.
     *
     * @param itemId ID of the item to remove
     * @return removed item, or null if it was not found
     */
    public Item removeItem(String itemId) {

        for (int i = 0; i < items.size(); i++) {

            if (items.get(i).getItemId().equals(itemId)) {
                return items.remove(i);
            }
        }

        return null;
    }

    /**
     * Checks whether an item is in the inventory.
     *
     * @param item item to check
     * @return true if the item exists, otherwise false
     */
    public boolean contains(Item item) {

        if (item == null) {
            return false;
        }

        return contains(item.getItemId());
    }

    /**
     * Checks whether an item with the given ID exists.
     *
     * @param itemId ID of the item
     * @return true if the item exists, otherwise false
     */
    public boolean contains(String itemId) {

        for (Item item : items) {

            if (item.getItemId().equals(itemId)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Returns all items currently in the inventory.
     *
     * @return a list containing the inventory items
     */
    public List<Item> getItems() {
        return new ArrayList<>(items);
    }

    /**
     * Returns the number of items in the inventory.
     *
     * @return number of items
     */
    public int size() {
        return items.size();
    }

    /**
     * Checks whether the inventory is empty.
     *
     * @return true if the inventory is empty
     */
    public boolean isEmpty() {
        return items.isEmpty();
    }
}
