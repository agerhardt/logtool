package de.age.logtool.parsing.composite;

import java.util.ArrayList;
import java.util.List;

import de.age.logtool.LogEntry;
import de.age.logtool.StringLogContent;
import de.age.logtool.Timestamp;
import de.age.logtool.parsing.LogEventListener;
import de.age.logtool.parsing.LogEventSource;
import de.age.logtool.parsing.util.LogListenerList;

public class TrackingLogEventSource implements LogEventSource {

	private final LogListenerList listeners = new LogListenerList();
	private final List<LogEntry> events = new ArrayList<LogEntry>();
	
	@Override
	public void addLogEventListener(LogEventListener l) {
		listeners.addLogEventListener(l);
	}

	@Override
	public void removeLogEventListener(LogEventListener l) {
		listeners.removeLogEventListener(l);
	}
	
	public void fireEvent() {
		Timestamp current = Timestamp.current();
		LogEntry entry = new LogEntry(current, new StringLogContent(current.toString()));
		events.add(entry);
		listeners.fireLogEvent(entry);
	}
	
	public List<LogEntry> getEvents() {
		return events;
	}

}
