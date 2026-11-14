package com.alongtheway.domain.narrative;

import java.util.List;

/**
 * Tests the Journal and JournalEntry classes.
 */
public class JournalTaskTest {

    private static void check(boolean condition, String message) {
        if (!condition) {
            throw new AssertionError("FAILED: " + message);
        }

        System.out.println("PASSED: " + message);
    }

    public static void main(String[] args) {

        // Test 1: Create an empty journal
        Journal journal = new Journal();

        check(
                journal.isEmpty(),
                "New journal is empty"
        );

        // Create two entries
        JournalEntry entry1 = new JournalEntry(
                "Cafe Owner",
                "People used to meet near the old bridge."
        );

        JournalEntry entry2 = new JournalEntry(
                "Gardener",
                "I have taken care of this garden for many years."
        );

        // Test 2: Check entry information
        check(
                entry1.getAuthor().equals("Cafe Owner")
                        && entry1.getContent().equals(
                        "People used to meet near the old bridge."
                ),
                "Journal entry stores author and content"
        );

        // Test 3: Add first entry
        journal.addEntry(entry1);

        check(
                journal.size() == 1,
                "First entry is added"
        );

        // Test 4: Add second entry
        journal.addEntry(entry2);

        check(
                journal.size() == 2,
                "Multiple entries can be added"
        );

        // Test 5: Retrieve entries
        List<JournalEntry> entries = journal.listEntries();

        check(
                entries.size() == 2
                        && entries.get(0) == entry1
                        && entries.get(1) == entry2,
                "Journal entries can be retrieved"
        );

        // Test 6: Display journal
        String displayedJournal = journal.toDisplayString();

        check(
                displayedJournal.contains("Cafe Owner")
                        && displayedJournal.contains("Gardener")
                        && displayedJournal.contains(
                        "People used to meet near the old bridge."
                ),
                "Journal displays its entries"
        );

        // Test 7: Add null entry
        journal.addEntry(null);

        check(
                journal.size() == 2,
                "Null entry is not added"
        );

        // Test 8: Journal is no longer empty
        check(
                !journal.isEmpty(),
                "Journal is not empty after adding entries"
        );

        System.out.println("\nAll Journal task tests completed.");

        // Show the complete journal
        System.out.println("\n" + journal.toDisplayString());
    }
}
