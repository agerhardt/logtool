package de.age.logtool.swing;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.age.logtool.display.DisplayLogEvent;
import de.age.logtool.swing.LogEventTableModel;
import de.age.logtool.swing.util.TrackingTableModelListener;

public class LogEventTableModelTest {

	private LogEventTableModel model;
	private TrackingTableModelListener listener;
	
	@Before
	public void setUp() {
		model = new LogEventTableModel();
		listener = new TrackingTableModelListener();
		model.addTableModelListener(listener);
	}
	
	@After
	public void tearDown() {
		model = null;
	}
	
	@Test
	public void addingAnElementFiresEvent() {
		model.addLogEvent(EasyMock.createNiceMock(DisplayLogEvent.class));
		assertThat(listener.getNumEvents(), is(1));
	}
	
}
