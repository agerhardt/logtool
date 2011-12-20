package de.age.logtool.parsing.util;

import java.util.ArrayList;
import java.util.List;

import de.age.logtool.LogEntry;
import de.age.logtool.parsing.LogEventListener;

public class TrackingLogEventListener implements LogEventListener {

	private List<LogEntry> events = new ArrayList<LogEntry>();
	
	public Integer getNumberOfEvents() {
		return events.size();
	}

	@Override
	public void logEvent(LogEntry event) {
		events.add(event);
	}
	
	public List<LogEntry> getEvents() {
		return events;
	}

}
