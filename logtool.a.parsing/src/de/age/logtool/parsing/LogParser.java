package de.age.logtool.parsing;

public interface LogParser extends LogEventSource {
	
	void parse(String input);

}
