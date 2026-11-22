package com.alongtheway.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.alongtheway.domain.narrative.NarrativeContent;

class NarrationRepositoryTest {

    private NarrativeContent content;

    @BeforeEach
    void setUp() throws IOException {
        content = new NarrationRepository().load();
    }

    @Test
    void loadsNarration() {
        assertNotNull(content);
    }
}
