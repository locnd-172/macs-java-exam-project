package com.alongtheway.domain.assessment;

import java.util.Map;

import com.alongtheway.config.enums.AssessmentDimension;

public class Archetype {

	private String name;
	private String description;
	private Map<AssessmentDimension, Double> weights;

	/**
	 * Creates an archetype.
	 *
	 * @param name        the archetype's name
	 * @param description the archetype's description
	 * @param weights     the weight of dimension
	 */
	public Archetype(String name, String description, Map<AssessmentDimension, Double> weights) {
		this.name = name;
		this.description = description;
		this.weights = weights;
	}

	/**
	 * Returns the archetype's name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the archetype's description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Returns how much each dimension counts for this archetype.
	 *
	 * @return the weights, in the order they were declared
	 */
	public Map<AssessmentDimension, Double> getWeights() {
		return weights;
	}
}
