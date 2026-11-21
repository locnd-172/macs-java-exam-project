package com.alongtheway.config.constants;

import java.util.Set;

public abstract class CommandConstants {
	protected CommandConstants() {
	}

	public static final Set<String> MOVE_TOKENS = Set.of(
		"go", "move", "walk", "head", "enter", "visit", "travel", "return"
	);

	public static final Set<String> EXAMINE_TOKENS = Set.of(
		"look", "examine", "inspect", "read", "check", "view", "see", "observe"
	);
	public static final Set<String> EXAMINE_LOCATION_TARGETS = Set.of(
		"around", "here", "surroundings"
	);

	public static final Set<String> DROP_TOKENS = Set.of(
		"drop", "put", "throw"
	);

	public static final Set<String> TALK_TOKENS = Set.of(
		"talk", "speak", "chat", "ask"
	);
	public static final String TALK_TOPIC_SEPARATOR = "(?i)about";


	public static final Set<String> USE_TOKENS = Set.of(
		"use", "try"
	);

	public static final Set<String> HELP_TOKENS = Set.of(
		"help", "offer"
	);

	public static final Set<String> TAKE_TOKENS = Set.of(
		"take", "get", "pick", "pickup", "collect"
	);

	public static final Set<String> JOURNAL_TOKENS = Set.of(
		"journal", "review"
	);

}
