package com.alongtheway.config;

import com.alongtheway.config.constants.CommandConstants;
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

}
