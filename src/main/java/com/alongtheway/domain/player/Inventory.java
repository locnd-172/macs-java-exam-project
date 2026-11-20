package com.alongtheway.domain.player;

import com.alongtheway.domain.world.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
	public Item removeItem(UUID itemId) {
		Item found = items.stream()
		                  .filter(item -> item.getItemId().equals(itemId))
		                  .findFirst()
		                  .orElse(null);

		if (found != null) {
			items.remove(found);
		}

		return found;
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
	 * Finds an inventory item by name or alias.
	 *
	 * @param query the name or alias to search
	 * @return the matching item, or NULL when no item matches
	 */
	public Item findItem(String query) {
		return items.stream()
		            .filter(item -> item.matches(query))
		            .findFirst()
		            .orElse(null);
	}

	/**
	 * Checks whether an item with the given ID exists.
	 *
	 * @param itemId ID of the item
	 * @return true if the item exists, otherwise false
	 */
	public boolean contains(UUID itemId) {
		return items.stream()
		            .anyMatch(item -> item.getItemId().equals(itemId));
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
