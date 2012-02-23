package de.age.logtool.parsing.util;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.age.logtool.LogEntry;
import de.age.logtool.StringLogContent;
import de.age.logtool.Timestamp;
import de.age.logtool.parsing.util.LogListenerList;

public class LogListenerListTest {
	
	private LogListenerList list;
	private TrackingLogEventListener listener;
	
	@Before
	public void setUp() {
		list = new LogListenerList();
		listener = new TrackingLogEventListener();
	}
	
	@After
	public void tearDown() {
		list = null;
		listener = null;
	}
	
	@Test
	public void isAddingListenersAndFiringEvents() {
		list.addLogEventListener(listener);
		list.fireLogEvent(new LogEntry(Timestamp.current(), new StringLogContent("content")));
		assertThat(listener.getNumberOfEvents(), is(1));
	}
	
	@Test
	public void isRemovingListeners() {
		list.addLogEventListener(listener);
		list.removeLogEventListener(listener);
		list.fireLogEvent(new LogEntry(Timestamp.current(), new StringLogContent("content")));
		assertThat(listener.getNumberOfEvents(), is(0));
	}

}
