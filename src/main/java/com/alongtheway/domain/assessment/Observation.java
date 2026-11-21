package com.alongtheway.domain.assessment;

import com.alongtheway.config.enums.AssessmentDimension;

public class Observation {

	private String action;
	private AssessmentDimension dimension;
	private int value;
	private double weight;
	private String context;

	/**
	 * Creates an observation.
	 *
	 * @param action    the player action
	 * @param dimension the assessment dimension
	 * @param value     the observed value
	 * @param weight    the weight of the observation
	 * @param context   a short note
	 */
	public Observation(String action, AssessmentDimension dimension, int value, double weight, String context) {
		this.action = action;
		this.dimension = dimension;
		this.value = value;
		this.weight = weight;
		this.context = context;
	}
}
