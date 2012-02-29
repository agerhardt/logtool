package de.age.logtool.parsing.composite;

import java.util.ArrayList;
import java.util.List;

import de.age.logtool.LogEntry;
import de.age.logtool.parsing.LogEventListener;
import de.age.logtool.parsing.LogEventSource;
import de.age.logtool.parsing.util.LogListenerList;

public class CompositeLogEventSource implements LogEventSource {

	private final LogListenerList listeners;
	private final List<LogEventSource> sources;
	
	public CompositeLogEventSource() {
		listeners = new LogListenerList();
		sources = new ArrayList<LogEventSource>();
	}
	
	public void addLogEventSource(LogEventSource newSource) {
		sources.add(newSource);
		newSource.addLogEventListener(new LogEventPasser());
	}

	@Override
	public void addLogEventListener(LogEventListener l) {
		listeners.addLogEventListener(l);
	}

	@Override
	public void removeLogEventListener(LogEventListener l) {
		listeners.removeLogEventListener(l);
	}
	
	private class LogEventPasser implements LogEventListener {

		@Override
		public void logEvent(LogEntry event) {
			listeners.fireLogEvent(event);
		}
		
	}
}
