package com.alongtheway.repository;

import com.alongtheway.config.GameConstants;
import com.alongtheway.domain.narrative.JournalEntry;

import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.alongtheway.repository.JsonUtils.text;

public class JournalRepository {

    private static final String AUTHOR  = "author";
    private static final String CONTENT = "content";

    private final JsonNode root;

    /**
     * Reads the journal file.
     *
     * @throws IOException if the file is missing or is not valid JSON
     */
    public JournalRepository() throws IOException {
        this.root = JsonUtils.read(GameConstants.Resources.JOURNALS);
    }

    /**
     * Builds the journal entries.
     *
     * @return the entries in file order
     */
    public List<JournalEntry> load() {
        List<JournalEntry> entries = new ArrayList<>();
        for (JsonNode node : root) {
            entries.add(new JournalEntry(text(node, AUTHOR), text(node, CONTENT)));
        }
        return entries;
    }
}
