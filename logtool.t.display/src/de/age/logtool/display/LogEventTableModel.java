package de.age.logtool.display;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class LogEventTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 2589730196460731687L;
	
	private static final ValueAdapter[] COLUMN_VALUE_ADAPTERS = {
		new TimeStampAdapter(), new DescriptionAdapter()
	};
	
	private final List<DisplayLogEvent> events;
	
	public LogEventTableModel() {
		events = new ArrayList<DisplayLogEvent>();
	}
	
	public void addLogEvent(DisplayLogEvent event) {
		events.add(event);
		fireTableRowsInserted(events.size() - 1, events.size() - 1);
	}
	
	@Override
	public int getRowCount() {
		return events.size();
	}

	@Override
	public String getColumnName(int columnIndex) {
		return COLUMN_VALUE_ADAPTERS[columnIndex].getColumnName();
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return COLUMN_VALUE_ADAPTERS[columnIndex].getColumnClass();
	}

	@Override
	public int getColumnCount() {
		return COLUMN_VALUE_ADAPTERS.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		DisplayLogEvent displayLogEvent = events.get(rowIndex);
		return COLUMN_VALUE_ADAPTERS[columnIndex].getValue(displayLogEvent);
	}
	
	private static interface ValueAdapter {

		Object getValue(DisplayLogEvent displayLogEvent);

		Class<?> getColumnClass();

		String getColumnName();
		
	}
	
	private static final class TimeStampAdapter implements ValueAdapter {

		@Override
		public Object getValue(DisplayLogEvent displayLogEvent) {
			return displayLogEvent;
		}

		@Override
		public Class<?> getColumnClass() {
			return Date.class;
		}

		@Override
		public String getColumnName() {
			return "Timestamp";
		}
		
	}
	
	private static final class DescriptionAdapter implements ValueAdapter {

		@Override
		public Object getValue(DisplayLogEvent displayLogEvent) {
			return displayLogEvent;
		}

		@Override
		public Class<?> getColumnClass() {
			return String.class;
		}

		@Override
		public String getColumnName() {
			return "Description";
		}
		
	}

}
