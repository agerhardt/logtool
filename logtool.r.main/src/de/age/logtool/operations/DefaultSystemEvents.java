package de.age.logtool.operations;

import java.util.ArrayList;
import java.util.List;

import de.age.logtool.display.DisplayLogEvent;
import de.age.logtool.display.LogEventListener;
import de.age.logtool.display.SystemEvents;

public class DefaultSystemEvents implements SystemEvents {

	private final List<LogEventListener> logEventListeners;
	
	public DefaultSystemEvents() {
		logEventListeners = new ArrayList<LogEventListener>();
	}
	
	@Override
	public void addLogEventListener(LogEventListener listener) {
	}

	protected void fireLogEvent(DisplayLogEvent event) {
		for (LogEventListener l : logEventListeners) {
			l.event(event);
		}
	}
}
