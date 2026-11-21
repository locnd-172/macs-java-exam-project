package com.alongtheway.domain.assessment;

import java.util.List;
import java.util.Map;

import com.alongtheway.config.enums.AssessmentDimension;

public class AssessmentReport {

	private final Archetype archetype;
	private final Map<AssessmentDimension, Double> scores;
	private final List<String> insights;

	/**
	 * Creates an assessment report.
	 *
	 * @param archetype the archetype
	 * @param scores    the score of each dimension
	 * @param insights  the insights
	 */
	public AssessmentReport(Archetype archetype, Map<AssessmentDimension, Double> scores, List<String> insights) {
		this.archetype = archetype;
		this.scores = scores;
		this.insights = insights;
	}
}
