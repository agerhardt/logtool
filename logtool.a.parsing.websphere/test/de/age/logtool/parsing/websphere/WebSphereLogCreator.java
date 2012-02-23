package de.age.logtool.parsing.websphere;

import java.util.Random;

import de.age.logtool.LogContent;
import de.age.logtool.LogEntry;
import de.age.logtool.StringLogContent;
import de.age.logtool.Timestamp;
import de.age.testtools.TrackingContentCreator;

public class WebSphereLogCreator extends TrackingContentCreator<LogEntry> {

	private static final String INPUT_FORM = "Example log line %1$s";
	
	private final Random random = new Random();
	private long currentLogTime = System.currentTimeMillis();
	private int numEntries;
	
	@Override
	protected LogEntry createContent() {
		currentLogTime += random.nextInt(100) + 100;
		Timestamp timestamp = new Timestamp(currentLogTime);
		LogContent content = new StringLogContent(String.format(INPUT_FORM, numEntries++));
		return new LogEntry(timestamp, content);
	}

}
