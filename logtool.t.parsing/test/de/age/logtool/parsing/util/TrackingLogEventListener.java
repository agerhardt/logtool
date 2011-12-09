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
	
	public List<TrackedEvent> getEvents() {
		return events;
	}

}
