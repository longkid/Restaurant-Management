package view;

import java.awt.Component;
import java.util.EventObject;

import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.table.TableCellEditor;

public class MyTableCellEditor implements TableCellEditor {
	DefaultCellEditor cellEditor;

	public MyTableCellEditor() {
		JCheckBox checkBox = new JCheckBox();
		checkBox.setHorizontalAlignment(JLabel.CENTER);
		cellEditor = new DefaultCellEditor(checkBox);
	}

	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {
		if (value instanceof Boolean) {
			return cellEditor.getTableCellEditorComponent(table, value,
					isSelected, row, column);
		}
		return null;
	}

	public Object getCellEditorValue() {
		return cellEditor.getCellEditorValue();
	}

	public Component getComponent() {

		return cellEditor.getComponent();
	}

	public boolean stopCellEditing() {
		return cellEditor.stopCellEditing();
	}

	public void cancelCellEditing() {
		cellEditor.cancelCellEditing();
	}

	public boolean isCellEditable(EventObject anEvent) {
		return cellEditor.isCellEditable(anEvent);
	}

	public boolean shouldSelectCell(EventObject anEvent) {
		return cellEditor.shouldSelectCell(anEvent);
	}

	public void addCellEditorListener(CellEditorListener listener) {
		cellEditor.addCellEditorListener(listener);
	}

	public void removeCellEditorListener(CellEditorListener listener) {
		cellEditor.removeCellEditorListener(listener);
	}

	public void setClickCountToStart(int n) {
		cellEditor.setClickCountToStart(n);
	}

	public int getClickCountToStart() {
		return cellEditor.getClickCountToStart();
	}
}
