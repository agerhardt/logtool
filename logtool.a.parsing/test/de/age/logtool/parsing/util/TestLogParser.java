package de.age.logtool.parsing.util;

import de.age.logtool.LogContent;
import de.age.logtool.LogEntry;
import de.age.logtool.Timestamp;
import de.age.logtool.parsing.AbstractLogParser;

public class TestLogParser extends AbstractLogParser {

	@Override
	public void parse(String input) {
		Timestamp timestamp = Timestamp.current();
		LogContent content = new LogContent(); // TODO
		LogEntry event = new LogEntry(timestamp, content);
		fireLogEvent(event);
	}
	
}