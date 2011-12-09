package de.age.logtool.parsing;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Calendar;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.age.logtool.parsing.util.TrackingLogEventListener;

public class WebsphereLogParserTest {
	
	private static final String INPUT_FORM = "[%1$td.%1$tm.%1$tY %1$tT:%1$tL CET] %2$s";
	
	private WebsphereLogParser parser;
	private TrackingLogEventListener listener;
	
	@Before
	public void setUp() {
		parser = new WebsphereLogParser();
		listener = new TrackingLogEventListener();
		parser.addLogEventListener(listener);
	}
	
	@After
	public void tearDown() {
		parser = null;
	}
	
	@Test
	public void singleLineDoesNotGetParsedUntilEntryIsFinished() {
		parser.parse("[02.12.11 09:47:29:875 CET] Example log line");
		assertThat(listener.getNumberOfEvents(), is(0));
	}
	
	@Test
	public void singleLineGetsParsedOnNextTimestamp() {
		parser.parse("[02.12.11 09:47:29:875 CET] Example log line 1");
		parser.parse("[02.12.11 09:48:29:875 CET] Example log line 2");
		assertThat(listener.getNumberOfEvents(), is(1));
	}
	
	@Test
	public void timestampsGetParsedCorrectly() {
		Calendar cal = Calendar.getInstance();
		cal.set(2011, 12, 30, 23, 59, 30);
		Date firstTime = cal.getTime();
		cal.set(2011, 12, 31,  1, 58, 20);
		Date secondTime = cal.getTime();
		parser.parse(String.format(INPUT_FORM, firstTime, "Example log line 1"));
		parser.parse(String.format(INPUT_FORM, secondTime, "Example log line 2"));
		assertThat(listener.getNumberOfEvents(), is(1));
	}
	
	public static void main(String[] args) {
		System.out.printf(INPUT_FORM, new Date(), "text");
	}

}
