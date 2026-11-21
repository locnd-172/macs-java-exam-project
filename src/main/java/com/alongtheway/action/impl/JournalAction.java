
package com.alongtheway.action.impl;

import com.alongtheway.action.Action;
import com.alongtheway.domain.GameSession;
import com.alongtheway.parser.Command;

/**
 * Displays the entries discovered by the player.
 */
public class JournalAction implements Action {

	private final Command command;

	/**
	 * Creates a journal action.
	 *
	 * @param command the command entered by the player
	 */
	public JournalAction(Command command) {
		this.command = command;
	}

	/**
	 * Displays the player's journal entries.
	 *
	 * @param session the current game session
	 */
	@Override
	public void execute(GameSession session) {
		String journalContent = session.getPlayer()
		                               .getJournal()
		                               .toDisplayString();

		session.respond(journalContent);
	}
}
