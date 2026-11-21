package com.alongtheway.domain;

import java.util.ArrayList;
import java.util.List;

import com.alongtheway.domain.assessment.Archetype;
import com.alongtheway.domain.assessment.AssessmentObservation;
import com.alongtheway.domain.assessment.AssessmentReport;
import com.alongtheway.domain.assessment.Observation;
import com.alongtheway.domain.player.Player;

public class AssessmentEngine {

	private final List<Archetype> archetypes;
	private final List<AssessmentObservation> observations = new ArrayList<>();

	public AssessmentEngine(List<Archetype> archetypes) {
		this.archetypes = List.copyOf(archetypes);
	}

	/**
	 * Returns the archetypes a player can match.
	 *
	 * @return a list of archetypes
	 */
	public List<Archetype> getArchetypes() {
		return archetypes;
	}

	/**
	 * Returns the observations recorded so far.
	 *
	 * @return a copy of the recorded observations
	 */
	public List<AssessmentObservation> getObservations() {
		return List.copyOf(observations);
	}

	public void record(AssessmentObservation observation, Player player) {
		// TODO
	}

}
