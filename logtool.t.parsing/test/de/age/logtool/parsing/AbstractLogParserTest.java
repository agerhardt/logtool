package de.age.logtool.parsing;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.age.logtool.parsing.util.TestLogParser;
import de.age.logtool.parsing.util.TrackingLogEventListener;

public class AbstractLogParserTest {
	
	private AbstractLogParser logParser;
	private TrackingLogEventListener listener;
	
	@Before
	public void setUp() {
		logParser = new TestLogParser();
		listener = new TrackingLogEventListener();
	}
	
	@After
	public void tearDown() {
		logParser = null;
		listener = null;
	}
	
	@Test
	public void isAddingListenersAndFiringEvents() {
		logParser.addLogEventListener(listener);
		logParser.parse("Input 1");
		assertThat(listener.getNumberOfEvents(), is(1));
	}
	
	@Test
	public void isRemovingListeners() {
		logParser.addLogEventListener(listener);
		logParser.removeLogEventListener(listener);
		logParser.parse("Input 1");
		assertThat(listener.getNumberOfEvents(), is(0));
	}

}
