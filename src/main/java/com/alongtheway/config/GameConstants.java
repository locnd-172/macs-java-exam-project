package com.alongtheway.config;

import com.alongtheway.config.constants.CommandConstants;
import com.alongtheway.config.constants.MessageConstants;
import com.alongtheway.config.constants.ResourceConstants;
import com.alongtheway.config.constants.TextConstants;

public final class GameConstants {

	private GameConstants() {
	}

	public static final class Defaults {
		private Defaults() {
		}

		public static final String GAME_TITLE = "Somewhere Along the Way";
		public static final String STARTING_LOCATION_ID = "village-square";
		public static final String SAVE_DIRECTORY = "src/main/resources/saves";
	}

	public static final class Narrative {
		private Narrative() {
		}

		public static final String DEFAULT_SCENARIO_ID = "default";
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

	public static final class Resources extends ResourceConstants {
		private Resources() {
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
