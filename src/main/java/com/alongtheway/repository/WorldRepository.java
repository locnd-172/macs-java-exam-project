package com.alongtheway.repository;

import com.alongtheway.config.GameConstants;
import com.alongtheway.domain.world.Character;
import com.alongtheway.domain.world.Item;
import com.alongtheway.domain.world.Location;
import com.alongtheway.domain.world.World;

import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

import static com.alongtheway.repository.JsonUtils.text;
import static com.alongtheway.repository.JsonUtils.texts;

public class WorldRepository {

    private final JsonNode root;

    private static final String LOCATIONS  = "locations";
    private static final String CHARACTERS = "characters";
    private static final String ITEMS      = "items";
    private static final String ID         = "id";
    private static final String NAME       = "name";
    private static final String LINKS      = "links";
    private static final String ALIASES    = "aliases";

    /**
     * Reads the world file.
     *
     * @throws IOException if the file is missing or is not valid JSON
     */
    public WorldRepository() throws IOException {
        this.root = JsonUtils.read(GameConstants.Resources.WORLD);
    }

    /**
     * Builds world for the game.
     *
     * @return the world with its locations linked
     */
    public World load() {
        World world = new World();

        readLocations(world);
        linkLocations(world);

        world.setStartingLocation(world.getLocationById(GameConstants.Defaults.STARTING_LOCATION_ID));

        return world;
    }

    /**
     * Builds location with the characters and items inside it.
     *
     * @param world the world to add entities to
     */
    private void readLocations(World world) {
        for (JsonNode node : root.path(LOCATIONS)) {
            Location location = new Location(
                text(node, ID),
                text(node, NAME),
                null,
                texts(node.path(ALIASES))
            );
            world.addLocation(location);

            for (JsonNode characterNode : node.path(CHARACTERS)) {
                location.addCharacter(readCharacter(characterNode));
            }

            for (JsonNode itemNode : node.path(ITEMS)) {
                location.addItem(readItem(itemNode));
            }
        }
    }

    /**
     * Links the locations to each other.
     *
     * @param world the world whose locations are built
     */
    private void linkLocations(World world) {
        for (JsonNode node : root.path(LOCATIONS)) {
            Location location = world.getLocationById(text(node, ID));
            for (String linkedId : texts(node.path(LINKS))) {
                location.addLink(world.getLocationById(linkedId));
            }
        }
    }

    /**
     * Builds a character from its entry.
     *
     * @param node the entry of the character
     * @return the character
     */
    private Character readCharacter(JsonNode node) {
        return new Character(
            text(node, ID),
            text(node, NAME),
            null,
            texts(node.path(ALIASES))
        );
    }

    /**
     * Builds an item from its entry.
     *
     * @param node the entry of the item
     * @return the item
     */
    private Item readItem(JsonNode node) {
        return new Item(
            text(node, ID),
            text(node, NAME),
            texts(node.path(ALIASES))
        );
    }
}
