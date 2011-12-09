package de.age.logtool.parsing.util;

import de.age.logtool.parsing.AbstractLogParser;

public class TestLogParser extends AbstractLogParser {

	@Override
	public void parse(String input) {
		fireLogEvent(System.currentTimeMillis(), input);
	}
	
}