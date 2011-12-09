package de.age.logtool.parsing;

public interface LogParser {
	
	void addLogEventListener(LogEventListener l);
	
	void removeLogEventListener(LogEventListener l);
	
	void parse(String input);

}
