package com.alongtheway.config;

import com.alongtheway.config.constants.CommandConstants;
import com.alongtheway.config.constants.MessageConstants;
import com.alongtheway.config.constants.TextConstants;

public final class GameConstants {

	private GameConstants() {
	}

	public static final class Defaults {
		private Defaults() {
		}

		public static final String GAME_TITLE = "Somewhere Along the Way";
		public static final String STARTING_LOCATION_ID = "village-square";
	}

	public static final class Text extends TextConstants {
		private Text() {
		}
	}

	public static final class Commands extends CommandConstants {
		private Commands() {
		}
	}

	public static final class Messages extends MessageConstants {
		private Messages() {
		}
	}

	/**
	 * Contains constants related to the player's journal.
	 */
	public static final class Journal {

		private Journal() {
		}

		public static final String EMPTY_MESSAGE = "Your journal is empty.";
		public static final String HEADER = "===== YOUR JOURNAL =====\n";
		public static final String ENTRY_HEADER = "\nEntry ";
	}

}
