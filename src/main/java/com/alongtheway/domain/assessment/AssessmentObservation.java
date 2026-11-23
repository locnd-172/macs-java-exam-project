package com.alongtheway.domain.assessment;

import com.alongtheway.config.enums.AssessmentDimension;

public class AssessmentObservation {

	private AssessmentDimension dimension;
	private int value;
	private double weight;
	private String locationId;

	/**
	 * Creates an assessment observation.
	 *
	 * @param dimension  the assessment dimension
	 * @param value      the observed value
	 * @param weight     the weight of the observation
	 * @param locationId the location it was recorded in
	 */
	public AssessmentObservation(AssessmentDimension dimension, int value, double weight, String locationId) {
		this.dimension = dimension;
		this.value = value;
		this.weight = weight;
		this.locationId = locationId;
	}
}
