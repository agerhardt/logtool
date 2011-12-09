package de.age.logtool.parsing;

public interface LogEventListener {
	
	void logEvent(long time, String content);

}
