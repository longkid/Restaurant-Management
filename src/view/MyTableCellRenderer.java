package view;

import java.awt.Component;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class MyTableCellRenderer extends DefaultTableCellRenderer {
	private static final long serialVersionUID = 1L;
	JCheckBox checkBox = new JCheckBox();

	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		if (value instanceof Boolean) {
			checkBox.setSelected(((Boolean) value).booleanValue());
			checkBox.setHorizontalAlignment(JLabel.CENTER);
			return checkBox;
		}
		String strValue = (value == null) ? "" : value.toString();
		return super.getTableCellRendererComponent(table, strValue, isSelected,
				hasFocus, row, column);
	}
}
