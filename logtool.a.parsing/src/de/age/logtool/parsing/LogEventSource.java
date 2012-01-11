package de.age.logtool.parsing;

public interface LogEventSource {

	void addLogEventListener(LogEventListener l);
	
	void removeLogEventListener(LogEventListener l);
	
}
