package de.age.logtool.parsing.websphere;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.age.logtool.LogContent;
import de.age.logtool.LogEntry;
import de.age.logtool.StringLogContent;
import de.age.logtool.parsing.util.TrackingLogEventListener;
import de.age.logtool.parsing.websphere.WebsphereLogParser;

public class WebsphereLogParserTest {
	
	private static final String INPUT_FORM = "[%1$td.%1$tm.%1$tY %1$tT:%1$tL CET] %2$s";
	
	private WebSphereLogCreator content;
	private WebsphereLogParser parser;
	private TrackingLogEventListener listener;
	
	@Before
	public void setUp() {
		content = new WebSphereLogCreator();
		parser = new WebsphereLogParser();
		listener = new TrackingLogEventListener();
		parser.addLogEventListener(listener);
	}
	
	@After
	public void tearDown() {
		parser = null;
		content = null;
		listener = null;
	}
	
	@Test
	public void singleLineDoesNotGetParsedUntilEntryIsFinished() {
		parser.parse(getStringContent(content.create().getContent()));
		assertThat(listener.getNumberOfEvents(), is(0));
	}
	
	@Test
	public void singleLineGetsParsedOnNextTimestamp() {
		parser.parse(generateLogline(content.create()));
		parser.parse(generateLogline(content.create()));
		assertThat(listener.getNumberOfEvents(), is(1));
	}

	@Test
	public void timestampsGetParsedCorrectly() {
		parser.parse(generateLogline(content.create()));
		parser.parse(generateLogline(content.create()));
		assertThat(listener.getEvents(), is(content.matcher(0, 1)));
	}
	
	@Test
	public void illegalContentAtStartGetsIgnored() {
		parser.parse("Illegal Content");
		parser.parse(generateLogline(content.create()));
		assertThat(listener.getNumberOfEvents(), is(0));
	}
	
	@Test
	public void multiLineContent() {
		String line1 = "First line";
		String line2 = "Second line";
		parser.parse(String.format(INPUT_FORM, new Date(), line1));
		parser.parse(line2);
		parser.parse(generateLogline(content.create()));
		assertThat(listener.getNumberOfEvents(), is(1));
		assertThat(getStringContent(listener.getEvents().get(0).getContent()), is(equalTo(line1 + line2)));
	}

	private static String getStringContent(LogContent content) {
		StringLogContent stringContent = (StringLogContent) content;
		return stringContent.getString();
	}
	
	private String generateLogline(LogEntry entry) {
		return String.format(INPUT_FORM, entry.getTimestamp().toDate(), entry.getContent());
	}

}
