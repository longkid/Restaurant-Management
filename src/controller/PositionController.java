package controller;

/*
 * @author Tu Thi Xuan Hien
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Vector;

import javax.swing.JOptionPane;

import model.Position;
import model.PositionTitle;
import model.Staff;
import view.PositionFrame;

public class PositionController {
	private PositionFrame positionFrame = null;
	private List<Position> positions = Staff.getInstance().getPositions();
	private Position currentPosition = null;

	public PositionController() {
		positionFrame = new PositionFrame("Position Management");
	}

	public void doShow() {
		positionFrame.setSize(600, 560);
		doFillSalary();
		loadDataOnTable();
		addEventforAllControl();
		positionFrame.setLocationRelativeTo(null);
		positionFrame.setVisible(true);
	}

	private void loadDataOnTable() {
		if (positions == null)
			return;
		// Remove all row in the table
		positionFrame.getTableModel().setRowCount(0);
		for (Position pos : positions) {
			Vector<String> vec = new Vector<String>();
			vec.add(PositionTitle.getTitleString(pos.getTitle()));
			vec.add(pos.getSalary() + "");
			vec.add(pos.getOtherSalary() + "");
			positionFrame.getTableModel().addRow(vec);
		}
	}

	public void addEventforAllControl() {
		// assign Event into BUtton
		positionFrame.getButtonUpdate().addActionListener(
				new CMyProcessButtonEvent());
		positionFrame.getComboBoxTitle().addActionListener(
				new CMyProcessButtonEvent());
		positionFrame.getButtonSave().addActionListener(
				new CMyProcessButtonEvent());
		positionFrame.getButtonExit().addActionListener(
				new CMyProcessButtonEvent());
		positionFrame.getDetailTable().addMouseListener(
				new CMyProcessMouseEvent());

		lockTextBox(true);
		positionFrame.getButtonUpdate().setEnabled(false);
		positionFrame.getButtonSave().setEnabled(false);
	}

	private void lockTextBox(boolean b) {
		positionFrame.getTextFieldSalary().setEditable(!b);
		positionFrame.getTextFieldOtherSalary().setEditable(!b);
		positionFrame.getComboBoxTitle().setEnabled(!b);
	}

	private void doUpdate() {
		if (positionFrame.getButtonUpdate().getText()
				.equalsIgnoreCase("Update")) {
			positionFrame.getButtonUpdate().setText("Cancel");
			positionFrame.getButtonUpdate().setEnabled(true);
			positionFrame.getButtonSave().setEnabled(true);
			lockTextBox(false);
		} else {
			positionFrame.getButtonUpdate().setText("Update");
			positionFrame.getButtonUpdate().setEnabled(true);
			positionFrame.getButtonSave().setEnabled(false);
			lockTextBox(true);

			if (currentPosition != null) {
				positionFrame.getTextFieldSalary().setText(
						currentPosition.getSalary() + "");
				positionFrame.getTextFieldOtherSalary().setText(
						currentPosition.getOtherSalary() + "");
				positionFrame.getComboBoxTitle().setSelectedIndex(
						currentPosition.getTitle().ordinal());
			}
		}
	}

	private void doExit() {
		if (JOptionPane.showConfirmDialog(positionFrame,
				"Do you want to close this function?", null,
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			positionFrame.dispose();
		}
	}

	private void doSelectedOnTable() {
		int nRow = positionFrame.getDetailTable().getSelectedRow();
		if (nRow >= 0) {
			positionFrame.getButtonUpdate().setEnabled(true);
			Position pos = positions.get(nRow);
			currentPosition = pos;
			positionFrame.getComboBoxTitle().setSelectedIndex(
					pos.getTitle().ordinal());
			doFillSalary();
		}
	}

	private void doFillSalary() {
		int index = positionFrame.getComboBoxTitle().getSelectedIndex();
		Position pos = positions.get(index);
		positionFrame.getTextFieldSalary().setText(pos.getSalary() + "");
		positionFrame.getTextFieldOtherSalary().setText(
				pos.getOtherSalary() + "");
	}

	private void doSave() {
		int index = positionFrame.getComboBoxTitle().getSelectedIndex();
		Position updatedPos = positions.get(index);
		int salary = Integer.parseInt(positionFrame.getTextFieldSalary()
				.getText());
		int otherSalary = Integer.parseInt(positionFrame
				.getTextFieldOtherSalary().getText());
		updatedPos.setSalary(salary);
		updatedPos.setOtherSalary(otherSalary);
		positions.set(index, updatedPos);
		// Update position list in Staff class
		Staff.getInstance().setPositions(positions);
		loadDataOnTable();
		positionFrame.getButtonUpdate().setText("Update");
		positionFrame.getButtonSave().setEnabled(false);
		lockTextBox(true);
	}

	private class CMyProcessButtonEvent implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Object myObj = e.getSource();
			if (myObj.equals(positionFrame.getButtonUpdate())) {
				doUpdate();
			} else if (myObj.equals(positionFrame.getComboBoxTitle())) {
				doFillSalary();
			} else if (myObj.equals(positionFrame.getButtonSave())) {
				doSave();
			} else if (myObj.equals(positionFrame.getButtonExit())) {
				doExit();
			}
		}

	}

	private class CMyProcessMouseEvent extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			doSelectedOnTable();
		}

	}
}
