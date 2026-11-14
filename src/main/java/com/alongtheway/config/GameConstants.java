package com.alongtheway.config;


public final class GameConstants {

	private GameConstants() {
	}

	public static final class Defaults {
		private Defaults() {
		}

		public static final String GAME_TITLE = "Somewhere Along the Way";
		public static final String STARTING_LOCATION_ID = "village-square";
	}

	public static class Text {
		private Text() {
		}

		public static final String EMPTY = "";
		public static final String SPACE = " ";
		public static final String NORMALIZE_FILTER_REGEX = "[^a-z0-9 ]";
		public static final String WHITESPACE_REGEX = "\\s+";

	}


}
