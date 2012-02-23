package de.age.logtool.parsing.composite;

import de.age.logtool.parsing.LogEventListener;
import de.age.logtool.parsing.LogEventSource;
import de.age.logtool.parsing.util.LogListenerList;

public class CompositeLogEventSource implements LogEventSource {

	private final LogListenerList listeners;
	
	public CompositeLogEventSource() {
		listeners = new LogListenerList();
	}
	
	public void addLogEventSource(LogEventSource newSource) {
		// TODO Auto-generated method stub
	}

	@Override
	public void addLogEventListener(LogEventListener l) {
		listeners.addLogEventListener(l);
	}

	@Override
	public void removeLogEventListener(LogEventListener l) {
		listeners.removeLogEventListener(l);
	}
}
