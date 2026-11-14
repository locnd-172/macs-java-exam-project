package com.alongtheway.util;

import com.alongtheway.config.GameConstants;

public final class TextUtils {

	public static String normalize(String value) {
		if (value == null) {
			return GameConstants.Text.EMPTY;
		}
		return value
			.toLowerCase()
			.replaceAll(GameConstants.Text.NORMALIZE_FILTER_REGEX, GameConstants.Text.SPACE)
			.trim()
			.replaceAll(GameConstants.Text.WHITESPACE_REGEX, GameConstants.Text.SPACE);
	}
}
