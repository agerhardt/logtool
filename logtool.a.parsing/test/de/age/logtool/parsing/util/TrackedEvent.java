package de.age.logtool.parsing.util;

import java.text.DateFormat;
import java.util.Date;

public class TrackedEvent {
	private static final DateFormat FORMAT = DateFormat.getDateTimeInstance();
	
	private final long timestamp;
	private final String content;
	
	public TrackedEvent(long timestamp, String content) {
		super();
		this.timestamp = timestamp;
		this.content = content;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public String getContent() {
		return content;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof TrackedEvent) {
			TrackedEvent otherEvent = (TrackedEvent) obj;
			return otherEvent.timestamp == timestamp && otherEvent.content.equals(content);
		}
		return false;
	}

	@Override
	public String toString() {
		return FORMAT.format(new Date(timestamp)) + " :: " + content;
	}
	
}