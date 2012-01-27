package de.age.logtool.adapters;

import de.age.logtool.LogEntry;
import de.age.logtool.parsing.LogEventListener;

/**
 * Receives events from business-type logevent sources, translates those into displayable logevents and
 * passes these onto listeners. 
 */
public class ParserToDisplayEventAdapter implements LogEventListener {

	@Override
	public void logEvent(LogEntry event) {
	}

}
