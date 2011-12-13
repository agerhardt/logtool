package de.age.logtool.display;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

public class LogEventTableCellRenderer implements TableCellRenderer {

	private final TableCellRenderer delegate;
	
	public LogEventTableCellRenderer() {
		delegate = new DefaultTableCellRenderer();
	}
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		Component delegateResult = delegate.getTableCellRendererComponent(table, value, isSelected, hasFocus,
				row, column);
		return delegateResult;
	}

}
