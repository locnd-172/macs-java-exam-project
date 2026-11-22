package com.alongtheway.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.alongtheway.domain.narrative.JournalEntry;

class JournalRepositoryTest {

    private List<JournalEntry> entries;

    @BeforeEach
    void setUp() throws IOException {
        entries = new JournalRepository().load();
    }

    @Test
    void loadsEntries() {
        assertEquals(3, entries.size());

        JournalEntry first = entries.getFirst();

        assertEquals("Liam", first.getAuthor());
        assertFalse(first.getContent().isBlank());

        assertEquals("Emma", entries.get(1).getAuthor());
        assertEquals("Anh", entries.get(2).getAuthor());
    }
}
