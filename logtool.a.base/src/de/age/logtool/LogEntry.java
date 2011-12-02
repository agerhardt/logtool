package de.age.logtool;

import de.age.logtool.exceptions.IllegalContentException;
import de.age.logtool.exceptions.IllegalTimestampException;

/**
 * A simple entry in a log, can be a simple line, or a more complex structure, like a trace or xml-dump.
 */
public class LogEntry {
	private final Timestamp timestamp;
	private final LogContent content;

	public LogEntry(Timestamp timestamp, LogContent content) {
		if (timestamp == null) {
			throw IllegalTimestampException.nullTimestamp();
		}
		this.timestamp = timestamp;
		if (content == null) {
			throw IllegalContentException.nullContent();
		}
		this.content = content;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public LogContent getContent() {
		return content;
	}
}
