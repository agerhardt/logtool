package de.age.logtool.swing.util;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

public class TrackingTableModelListener implements TableModelListener {

	private final List<TableModelEvent> events = new ArrayList<TableModelEvent>();
	
	@Override
	public void tableChanged(TableModelEvent e) {
		events.add(e);
	}
	
	public int getNumEvents() {
		return events.size();
	}

}
