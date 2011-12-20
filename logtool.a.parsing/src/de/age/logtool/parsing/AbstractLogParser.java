package de.age.logtool.parsing;

import java.util.ArrayList;
import java.util.List;

import de.age.logtool.LogEntry;

public abstract class AbstractLogParser implements LogParser {
	
	private List<LogEventListener> listeners;
	
	public AbstractLogParser() {
		listeners = new ArrayList<LogEventListener>();
	}
	
	@Override
	public void addLogEventListener(LogEventListener l) {
		listeners.add(l);
	}

	@Override
	public void removeLogEventListener(LogEventListener l) {
		listeners.remove(l);
	}

	protected void fireLogEvent(LogEntry event) {
		for (LogEventListener l : listeners) {
			l.logEvent(event);
		}
	}

}
