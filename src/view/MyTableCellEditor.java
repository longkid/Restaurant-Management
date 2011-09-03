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
	DefaultCellEditor m_cellEditor;

	public MyTableCellEditor() {
		JCheckBox checkBox = new JCheckBox();
		checkBox.setHorizontalAlignment(JLabel.CENTER);
		m_cellEditor = new DefaultCellEditor(checkBox);
	}

	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {
		if (value instanceof Boolean) {
			return m_cellEditor.getTableCellEditorComponent(table, value,
					isSelected, row, column);
		}
		return null;
	}

	public Object getCellEditorValue() {
		return m_cellEditor.getCellEditorValue();
	}

	public Component getComponent() {

		return m_cellEditor.getComponent();
	}

	public boolean stopCellEditing() {
		return m_cellEditor.stopCellEditing();
	}

	public void cancelCellEditing() {
		m_cellEditor.cancelCellEditing();
	}

	public boolean isCellEditable(EventObject anEvent) {
		return m_cellEditor.isCellEditable(anEvent);
	}

	public boolean shouldSelectCell(EventObject anEvent) {
		return m_cellEditor.shouldSelectCell(anEvent);
	}

	public void addCellEditorListener(CellEditorListener listener) {
		m_cellEditor.addCellEditorListener(listener);
	}

	public void removeCellEditorListener(CellEditorListener listener) {
		m_cellEditor.removeCellEditorListener(listener);
	}

	public void setClickCountToStart(int n) {
		m_cellEditor.setClickCountToStart(n);
	}

	public int getClickCountToStart() {
		return m_cellEditor.getClickCountToStart();
	}
}
