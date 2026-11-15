package com.alongtheway.parser;

public class Command {
	private final CommandVerb verb;
	private final String target;

	/**
	 * Creates a command from a verb and a target.
	 *
	 * @param verb the command's verb
	 * @param target the text following the verb
	 */
	public Command(CommandVerb verb, String target) {
		this.verb = verb;
		this.target = target;
	}

	/**
	 * Returns the command's verb.
	 *
	 * @return the verb
	 */
	public CommandVerb getVerb() {
		return verb;
	}

	/**
	 * Returns the target text supplied with the command.
	 *
	 * @return the target text
	 */
	public String getTarget() {
		return target;
	}
}
