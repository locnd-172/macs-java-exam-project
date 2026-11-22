package com.alongtheway.repository;

import com.alongtheway.config.GameConstants;
import com.alongtheway.config.enums.AssessmentDimension;
import com.alongtheway.domain.assessment.Archetype;

import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.alongtheway.repository.JsonUtils.text;

public class ArchetypeRepository {

    private static final String NAME        = "name";
    private static final String DESCRIPTION = "description";
    private static final String WEIGHTS     = "weights";

    private final JsonNode root;

    /**
     * Reads the archetype file.
     *
     * @throws IOException if the file is missing or is not valid JSON
     */
    public ArchetypeRepository() throws IOException {
        this.root = JsonUtils.read(GameConstants.Resources.ARCHETYPES);
    }

    /**
     * Builds the archetypes.
     *
     * @return the archetypes in file order
     * @throws IllegalArgumentException if a weight names a dimension that does not exist
     */
    public List<Archetype> load() {
        List<Archetype> archetypes = new ArrayList<>();
        for (JsonNode node : root) {
            archetypes.add(
                new Archetype(
                    text(node, NAME),
                    text(node, DESCRIPTION),
                    readWeights(node.path(WEIGHTS))
                )
            );
        }
        return archetypes;
    }

    /**
     * Reads how much each dimension counts for an archetype.
     *
     * @param node the {@code weights} object of the archetype
     * @return the weights in the order of the file
     */
    private Map<AssessmentDimension, Double> readWeights(JsonNode node) {
        Map<AssessmentDimension, Double> weights = new LinkedHashMap<>();

        for (Map.Entry<String, JsonNode> field : node.properties()) {
            String key = field.getKey();
            Double value = field.getValue().asDouble();

            weights.put(AssessmentDimension.valueOf(key), value);
        }
        return weights;
    }
}
