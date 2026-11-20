package com.alongtheway.util;

import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

import com.alongtheway.config.GameConstants;

public final class TextUtils {

	private TextUtils() {
	}

	/**
	 * Normalizes text for comparison:
	 * 1. lowercases
	 * 2. replaces characters other than letters, digits, and spaces with a space
	 * 3. trims leading and trailing whitespace
	 * 4. collapses repeated whitespace into a single space
	 *
	 * @param value the text to normalize
	 * @return the normalized text, or an empty string when the value is null
	 */
	public static String normalize(String value) {
		if (value == null) {
			return GameConstants.Text.EMPTY;
		}
		return value.toLowerCase(Locale.ROOT)
		            .replaceAll(GameConstants.Text.NORMALIZE_FILTER_REGEX, GameConstants.Text.SPACE)
		            .trim()
		            .replaceAll(GameConstants.Text.WHITESPACE_REGEX, GameConstants.Text.SPACE);
	}

	/**
	 * Matches a query against any candidate.
	 * Both sides are normalized and compared as whole words in either direction.
	 * A null candidate list does not match.
	 *
	 * @param query      the input text
	 * @param candidates the candidate to check
	 * @return true when the query matches any candidate
	 */
	public static boolean match(String query, List<String> candidates) {
		String normalizedQuery = normalize(query);

		if (normalizedQuery.isBlank() || candidates == null) {
			return false;
		}

		return candidates.stream()
		                 .anyMatch(candidate
			                 -> matchNormalized(normalizedQuery, candidate)
		                 );
	}

	private static boolean matchNormalized(String normalizedQuery, String candidate) {
		String normalizedCandidate = normalize(candidate);

		if (normalizedCandidate.isBlank()) {
			return false;
		}

		return containsWholeWords(normalizedQuery, normalizedCandidate)
			|| containsWholeWords(normalizedCandidate, normalizedQuery);
	}

	private static boolean containsWholeWords(String text, String words) {
		String boundary = GameConstants.Text.WORD_BOUNDARY_REGEX;
		String pattern = boundary + Pattern.quote(words) + boundary;
		return Pattern.compile(pattern).matcher(text).find();
	}
}
