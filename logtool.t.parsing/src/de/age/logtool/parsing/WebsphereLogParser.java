package de.age.logtool.parsing;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class WebsphereLogParser extends AbstractLogParser {

	private static final SimpleDateFormat FORMAT = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss:SSS");
	private StringBuilder buffer;
	
	public WebsphereLogParser() {
		buffer = new StringBuilder();
	}
	
	@Override
	public void parse(String input) {
		if (buffer.length() > 0 && isInputWithTimestamp(input)) {
			flushBuffer();
		} else {
			buffer.append(input);
		}
	}

	private boolean isInputWithTimestamp(String input) {
		return input.startsWith("[") && input.indexOf("]") > 0;
	}
	
	/**
	 * Parses the current content of the buffer as one event and fires that event.
	 */
	private void flushBuffer() {
		try {
			Date parse = FORMAT.parse(buffer.substring(1, 24));
			fireLogEvent(parse.getTime(), buffer.toString());
		} catch (ParseException e) {
			// TODO
			throw new RuntimeException();
		}
		buffer = new StringBuilder();
	}

}
