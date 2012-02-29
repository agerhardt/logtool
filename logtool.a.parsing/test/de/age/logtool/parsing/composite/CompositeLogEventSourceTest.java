package de.age.logtool.parsing.composite;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.age.logtool.parsing.util.TrackingLogEventListener;

public class CompositeLogEventSourceTest {
	
	private CompositeLogEventSource composite;
	private TrackingLogEventListener listener;
	
	@Before
	public void setUp() {
		listener = new TrackingLogEventListener();
		composite = new CompositeLogEventSource();
		composite.addLogEventListener(listener);
	}
	@After
	public void tearDown() {
		composite = null;
		listener = null;
	}
	
	@Test
	public void eventsOfSingleSourceGetPassedOn() {
		TrackingLogEventSource source = new TrackingLogEventSource();
		composite.addLogEventSource(source);
		source.fireEvent();
		assertThat(listener.getNumberOfEvents(), is(equalTo(1)));
		assertThat(listener.getEvents().get(0), is(equalTo(source.getEvents().get(0))));
	}
	
	@Test
	public void eventsOfMultipleSourcesGetPassedOn() {
		TrackingLogEventSource source1 = new TrackingLogEventSource();
		TrackingLogEventSource source2 = new TrackingLogEventSource();
		composite.addLogEventSource(source1);
		composite.addLogEventSource(source2);
		source1.fireEvent();
		source2.fireEvent();
		source2.fireEvent();
		source1.fireEvent();
		assertThat(listener.getNumberOfEvents(), is(equalTo(4)));
	}

}
