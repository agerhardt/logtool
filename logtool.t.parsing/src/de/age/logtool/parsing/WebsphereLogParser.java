package de.age.logtool.parsing;

import java.text.SimpleDateFormat;


public class WebsphereLogParser extends AbstractLogParser {

	private SimpleDateFormat format;
	private StringBuilder buffer;
	
	public WebsphereLogParser() {
		buffer = new StringBuilder();
		format = new SimpleDateFormat();
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
		fireLogEvent(0, buffer.toString());
		buffer = new StringBuilder();
	}

}
