package de.age.logtool.parsing.util;

import java.util.ArrayList;
import java.util.List;

import de.age.logtool.parsing.LogEventListener;

public class TrackingLogEventListener implements LogEventListener {

	private List<TrackedEvent> events = new ArrayList<TrackedEvent>();
	
	public Integer getNumberOfEvents() {
		return events.size();
	}

	@Override
	public void logEvent(long time, String content) {
		events.add(new TrackedEvent(time, content));
	}
	
	public static class TrackedEvent {
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
	}

}
