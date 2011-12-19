package de.age.logtool.display;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.age.logtool.display.swing.LogEventTableModel;
import de.age.logtool.display.util.TrackingTableModelListener;

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
		model.addLogEvent(new DisplayLogEvent());
		assertThat(listener.getNumEvents(), is(1));
	}
	
}
