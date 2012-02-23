package de.age.logtool.parsing.websphere;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

import de.age.logtool.LogContent;
import de.age.logtool.LogEntry;
import de.age.logtool.StringLogContent;
import de.age.logtool.Timestamp;
import de.age.logtool.parsing.LogEventListener;
import de.age.logtool.parsing.LogParser;
import de.age.logtool.parsing.util.LogListenerList;


class WebsphereLogParser implements LogParser {

	private static final SimpleDateFormat FORMAT = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss:SSS");
	private static final Pattern PATTERN = Pattern.compile("\\[\\d{2}\\.\\d{2}\\.\\d{4} \\d{2}:\\d{2}:\\d{2}:\\d{3} CET\\].*");
	private StringBuilder buffer;
	private final LogListenerList listeners;
	
	public WebsphereLogParser() {
		buffer = new StringBuilder();
		listeners = new LogListenerList();
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
		return PATTERN.matcher(input).find();
	}
	
	/**
	 * Parses the current content of the buffer as one event and fires that event.
	 */
	private void flushBuffer() {
		if (isInputWithTimestamp(buffer.toString())) {
			try {
				Date parse = FORMAT.parse(buffer.substring(1, 24));
				Timestamp timestamp = new Timestamp(parse.getTime());
				LogContent content = new StringLogContent(buffer.substring(30));
				LogEntry event = new LogEntry(timestamp, content);
				listeners.fireLogEvent(event);
			} catch (ParseException e) {
				// TODO
				throw new RuntimeException();
			}
		}
		buffer = new StringBuilder();
	}

	@Override
	public void addLogEventListener(LogEventListener l) {
		listeners.addLogEventListener(l);
	}

	@Override
	public void removeLogEventListener(LogEventListener l) {
		listeners.removeLogEventListener(l);
	}

}
