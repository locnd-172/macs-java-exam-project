
package com.alongtheway.domain.narrative;

import java.util.ArrayList;
import java.util.List;

import com.alongtheway.config.GameConstants;

/**
 * Stores the memories and entries discovered by the player.
 * The player can add entries and review them later.
 */
public class Journal {

    private List<JournalEntry> entries;

    /**
     * Creates an empty journal.
     */
    public Journal() {
        entries = new ArrayList<>();
    }

    /**
     * Adds a new entry to the journal.
     *
     * @param entry the journal entry to add
     */
    public void addEntry(JournalEntry entry) {

        if (entry != null) {
            entries.add(entry);
        }
    }

    /**
     * Returns all entries stored in the journal.
     *
     * @return a list of journal entries
     */
    public List<JournalEntry> listEntries() {
        return new ArrayList<>(entries);
    }

    /**
     * Returns the number of entries in the journal.
     *
     * @return number of entries
     */
    public int size() {
        return entries.size();
    }

    /**
     * Checks whether the journal has any entries.
     *
     * @return true if the journal is empty
     */
    public boolean isEmpty() {
        return entries.isEmpty();
    }

    /**
     * Creates a readable string containing all journal entries.
     *
     * @return formatted journal contents
     */
    public String toDisplayString() {

        if (entries.isEmpty()) {
            return GameConstants.Journal.EMPTY_MESSAGE;
        }

        StringBuilder result = new StringBuilder();

        result.append(GameConstants.Journal.HEADER);

        for (int i = 0; i < entries.size(); i++) {

            result.append(GameConstants.Journal.ENTRY_HEADER).append(i + 1).append("\n");
            result.append(entries.get(i).toString());
            result.append("\n");
        }

        return result.toString();
    }
}
