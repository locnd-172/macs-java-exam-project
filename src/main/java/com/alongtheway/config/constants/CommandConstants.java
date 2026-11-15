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
	public static final Set<String> DROP_TOKENS = Set.of(
		"drop", "put", "throw"
	);
}
