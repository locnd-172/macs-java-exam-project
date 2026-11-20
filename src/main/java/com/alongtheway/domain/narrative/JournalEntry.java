package com.alongtheway.domain.narrative;

/**
 * Represents one entry in the player's journal.
 * Each entry contains an author and some content.
 */
public class JournalEntry {

	private String author;
	private String content;

	/**
	 * Creates a new journal entry.
	 *
	 * @param author  the author of the entry
	 * @param content the content of the entry
	 */
	public JournalEntry(String author, String content) {
		this.author = author;
		this.content = content;
	}

	/**
	 * Returns the author of this entry.
	 *
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * Returns the content of this entry.
	 *
	 * @return the entry content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * Returns a readable representation of the entry.
	 *
	 * @return author and content
	 */
	@Override
	public String toString() {
		return "Author: " + author + "\n" + content;
	}
}
