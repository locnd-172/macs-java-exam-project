package com.alongtheway.config.constants;

public abstract class MessageConstants {
	protected MessageConstants() {
	}

	public static final String MOVE_ARRIVAL = "You made your way to %s.";
	public static final String MOVE_UNKNOWN = "There's no a place by that name.";
	public static final String MOVE_ALREADY_THERE = "You are already here.";
	public static final String MOVE_BLOCKED = "You can't reach that place from here.";

	public static final String TALK_TARGET_REQUIRED = "Who would you like to communicate?";
	public static final String TALK_CHARACTER_NOT_FOUND = "There is no one here by that name.";
	public static final String TALK_GENERAL_RESPONSE = "You speak into the silence.";
	public static final String TALK_RESPONSE = "%s share a thing with you.";
	public static final String TALK_RESPONSE_WITH_TOPIC = "%s share about %s.";
	public static final String TALK_RESPONSE_WITH_TOPIC_NO_CHARACTER = "You asked about %s.";

	public static final String USE_TARGET_REQUIRED = "What would you like to use?";
	public static final String USE_ITEM_NOT_FOUND = "You are not carrying anything by that name.";
	public static final String USE_RESPONSE = "You use the %s.";

	public static final String HELP_TARGET_REQUIRED = "Who would you like to help?";
	public static final String HELP_CHARACTER_NOT_FOUND = "No one here goes by that name.";
	public static final String HELP_RESPONSE = "You offer to help %s.";

	public static final String EXAMINE_TARGET_REQUIRED = "What do you want to check?";
	public static final String EXAMINE_TARGET_NOT_FOUND = "There's no such item here!";
	public static final String EXAMINE_DESCRIPTION_UNAVAILABLE = "There is nothing more to notice!";

	public static final String TAKE_TARGET_REQUIRED = "What would you like to pick up?";
	public static final String TAKE_ITEM_NOT_FOUND = "There is no such item here.";
	public static final String TAKE_SUCCESS = "You picked up the %s.";

	public static final String DROP_TARGET_REQUIRED = "What would you like to drop?";
	public static final String DROP_ITEM_NOT_FOUND = "You are not carrying anything by that name.";
	public static final String DROP_SUCCESS = "You dropped the %s.";

}
