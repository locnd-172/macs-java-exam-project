package com.alongtheway.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.alongtheway.config.enums.AssessmentDimension;
import com.alongtheway.domain.assessment.Archetype;

class ArchetypeRepositoryTest {

    private List<Archetype> archetypes;

    @BeforeEach
    void setUp() throws IOException {
        archetypes = new ArchetypeRepository().load();
    }

    @Test
    void loadsArchetypes() {
        assertEquals(3, archetypes.size());

        Archetype explorer = archetypes.getFirst();
        assertEquals("The Thoughtful Explorer", explorer.getName());
        assertFalse(explorer.getDescription().isBlank());
    }

    @Test
    void loadsWeights() {
        Map<AssessmentDimension, Double> explorerWeights = archetypes.getFirst().getWeights();

        assertEquals(
            List.of(
                AssessmentDimension.CURIOSITY,
                AssessmentDimension.OPENNESS,
                AssessmentDimension.EMPATHY
            ),
            List.copyOf(explorerWeights.keySet())
        );
        assertEquals(1.0, explorerWeights.get(AssessmentDimension.CURIOSITY));
        assertEquals(0.8, explorerWeights.get(AssessmentDimension.OPENNESS));
    }
}
