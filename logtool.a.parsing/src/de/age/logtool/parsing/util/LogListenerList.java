package de.age.logtool.parsing.util;

import java.util.ArrayList;
import java.util.List;

import de.age.logtool.LogEntry;
import de.age.logtool.parsing.LogEventListener;
import de.age.logtool.parsing.LogEventSource;

/**
 * Helper class for handling the listener methods of {@link LogEventSource}
 */
public class LogListenerList {

	private List<LogEventListener> listeners;
	
	public LogListenerList() {
		listeners = new ArrayList<LogEventListener>();
	}
	
	public void addLogEventListener(LogEventListener l) {
		listeners.add(l);
	}

	public void removeLogEventListener(LogEventListener l) {
		listeners.remove(l);
	}

	public void fireLogEvent(LogEntry event) {
		for (LogEventListener l : listeners) {
			l.logEvent(event);
		}
	}

}
