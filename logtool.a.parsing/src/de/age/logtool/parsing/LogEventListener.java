package de.age.logtool.parsing;

import de.age.logtool.LogEntry;

public interface LogEventListener {
	
	void logEvent(LogEntry event);

}
