package com.alongtheway.domain.narrative;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the Journal and JournalEntry classes.
 */
class JournalTaskTest {

	private Journal journal;
	private JournalEntry cafeEntry;
	private JournalEntry gardenerEntry;

	/**
	 * Creates the objects needed for each test.
	 */
	@BeforeEach
	void setUp() {

		journal = new Journal();

		cafeEntry = new JournalEntry("Café Owner", "Sometimes a small conversation can change someone's day.");

		gardenerEntry = new JournalEntry("Gardener", "Taking care of something requires patience.");
	}

	@Test
	void newJournalIsEmpty() {

		assertTrue(journal.isEmpty());
		assertEquals(0, journal.size());
	}

	@Test
	void journalEntryStoresAuthorCorrectly() {

		assertEquals("Café Owner", cafeEntry.getAuthor());
	}

	@Test
	void journalEntryStoresContentCorrectly() {

		assertEquals("Sometimes a small conversation can change someone's day.", cafeEntry.getContent());
	}

	@Test
	void journalEntryHasCorrectStringRepresentation() {

		String result = cafeEntry.toString();

		assertTrue(result.contains("Café Owner"));
		assertTrue(result.contains("Sometimes a small conversation can change someone's day."));
	}

	@Test
	void journalCanAddAnEntry() {

		journal.addEntry(cafeEntry);

		assertEquals(1, journal.size());
		assertFalse(journal.isEmpty());
	}

	@Test
	void journalCanAddMultipleEntries() {

		journal.addEntry(cafeEntry);
		journal.addEntry(gardenerEntry);

		assertEquals(2, journal.size());
	}

	@Test
	void journalCanRetrieveAddedEntries() {

		journal.addEntry(cafeEntry);
		journal.addEntry(gardenerEntry);

		List<JournalEntry> entries = journal.listEntries();

		assertEquals(2, entries.size());
		assertSame(cafeEntry, entries.get(0));
		assertSame(gardenerEntry, entries.get(1));
	}

	@Test
	void journalIgnoresNullEntries() {

		journal.addEntry(null);

		assertTrue(journal.isEmpty());
		assertEquals(0, journal.size());
	}

	@Test
	void journalDisplaysEmptyMessageWhenThereAreNoEntries() {

		assertEquals("Your journal is empty.", journal.toDisplayString());
	}

	@Test
	void journalDisplaysAllEntries() {

		journal.addEntry(cafeEntry);
		journal.addEntry(gardenerEntry);

		String result = journal.toDisplayString();

		assertTrue(result.contains("===== YOUR JOURNAL ====="));

		assertTrue(result.contains("Entry 1"));
		assertTrue(result.contains("Café Owner"));
		assertTrue(result.contains("Sometimes a small conversation can change someone's day."));

		assertTrue(result.contains("Entry 2"));
		assertTrue(result.contains("Gardener"));
		assertTrue(result.contains("Taking care of something requires patience."));
	}

	@Test
	void listEntriesReturnsSeparateList() {

		journal.addEntry(cafeEntry);

		List<JournalEntry> entries = journal.listEntries();

		entries.clear();

		// Clearing the returned list should not clear the journal.
		assertEquals(1, journal.size());
		assertFalse(journal.isEmpty());
	}
}
