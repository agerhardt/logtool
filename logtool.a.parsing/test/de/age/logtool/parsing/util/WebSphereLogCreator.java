package de.age.logtool.parsing.util;

import java.util.Random;

import de.age.testtools.TrackingContentCreator;

public class WebSphereLogCreator extends TrackingContentCreator<TrackedEvent> {

	private static final String INPUT_FORM = "[%1$td.%1$tm.%1$tY %1$tT:%1$tL CET] Example log line %2$s";
	
	private final Random random = new Random();
	private long currentLogTime = System.currentTimeMillis();
	private int numEntries;
	
	@Override
	protected TrackedEvent createContent() {
		currentLogTime += random.nextInt(100) + 100;
		return new TrackedEvent(currentLogTime, String.format(INPUT_FORM, currentLogTime, numEntries++));
	}

}
