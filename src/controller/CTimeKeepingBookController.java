package controller;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import view.CMyTableCellEditor;
import view.CMyTableCellRenderer;
import view.CTimeKeepingBookFrame;
import model.CTimeKeepingBook;
import model.CTimeKeepingDetailInfor;
import model.CTimeKeepingSheet;
import model.Contract;
import model.Employee;
import model.Staff;

enum eSHOW {
	NEWCONTRACT, EDITCONTRACT, DELETECONTRACT, CALCPAYROLL
}

public class CTimeKeepingBookController {
	private CTimeKeepingBookFrame timeKeepingBookFrame = null;
	private List<Employee> employees = Staff.getInstance().getEmployees();
	private Employee currentEmployee = null;
	private Contract currentContract = null;
	private int nMonthSelected, nYearSelected, nNumberDayOfMonth;

	public CTimeKeepingBookController() {
		timeKeepingBookFrame = new CTimeKeepingBookFrame(
				"Timekeeping & Contract Management");
	}

	public void doShow() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		timeKeepingBookFrame
				.setSize(screenSize.width, screenSize.height - 30);
		timeKeepingBookFrame.setVisible(true);
		timeKeepingBookFrame.setLocationRelativeTo(null);
		addEventforAllControl();
		doLoadData();
	}

	public void doLoadData() {
		loadDataIntoTable(employees);
		enableControlForContract();
		if (currentEmployee != null) {
			doProcessMonthSelection();
			doGetListContractForEmployee(currentEmployee);
			timeKeepingBookFrame.getTableEmployee().changeSelection(0, 0,
					false, false);
			if (currentEmployee.getContracts() != null)
				timeKeepingBookFrame.getTreeViewContract().expandRow(2);
		}
		int month = Integer.parseInt(timeKeepingBookFrame.getComboBoxMonth()
				.getSelectedItem().toString());
		int year = Integer.parseInt(timeKeepingBookFrame.getComboBoxYear()
				.getSelectedItem().toString());
		nMonthSelected = month;
		nYearSelected = year;
	}

	public void addEventforAllControl() {
		timeKeepingBookFrame.getTreeViewContract().addTreeSelectionListener(
				new CTreeEvent());
		timeKeepingBookFrame.getTableEmployee().addMouseListener(
				new CProcessMouseEvent());
		timeKeepingBookFrame.getComboBoxMonth().addActionListener(
				new CButtonEvent());
		timeKeepingBookFrame.getComboBoxYear().addActionListener(
				new CButtonEvent());
		timeKeepingBookFrame.getButtonSaveTimeKeeping().addActionListener(
				new CButtonEvent());
		timeKeepingBookFrame.getButtonSearch().addActionListener(
				new CButtonEvent());
		timeKeepingBookFrame.getButtonShowAll().addActionListener(
				new CButtonEvent());
		timeKeepingBookFrame.getButtonTrash().addActionListener(
				new CButtonEvent());
		timeKeepingBookFrame.getButtonAddNewContract().addActionListener(
				new CButtonEvent());
		timeKeepingBookFrame.getButtonModifyContract().addActionListener(
				new CButtonEvent());
		timeKeepingBookFrame.getButtonCalcPayroll().addActionListener(
				new CButtonEvent());
		timeKeepingBookFrame.getButtonReport().addActionListener(
				new CButtonEvent());
		timeKeepingBookFrame.getButtonClose().addActionListener(
				new CButtonEvent());
		timeKeepingBookFrame.getMenuItemSystemExit().addActionListener(
				new CButtonEvent());
	}

	private void enableControlForContract() {
		boolean bEnable = true;
		if (currentContract == null) {
			bEnable = false;
		}
		timeKeepingBookFrame.getButtonModifyContract().setEnabled(bEnable);
		timeKeepingBookFrame.getButtonCalcPayroll().setEnabled(bEnable);
		timeKeepingBookFrame.getButtonTrash().setEnabled(bEnable);
		timeKeepingBookFrame.getComboBoxMonth().setEnabled(bEnable);
		timeKeepingBookFrame.getComboBoxYear().setEnabled(bEnable);
		timeKeepingBookFrame.getButtonSaveTimeKeeping().setEnabled(bEnable);
		timeKeepingBookFrame.getPanelRight().setEnabled(bEnable);
		timeKeepingBookFrame.getTableTimeKeeping().setVisible(bEnable);
		if (!bEnable)
			timeKeepingBookFrame.getTableModelTimeKeeping().setColumnCount(0);
	}

	private void loadDataIntoTable(List<Employee> employees) {
		if (employees != null) {
			timeKeepingBookFrame.getTableModelEmployee().setRowCount(0);
			for (int i = 0; i < employees.size(); i++) {
				Employee emp = employees.get(i);
				timeKeepingBookFrame.getTableModelEmployee().addRow(
						emp.getVector());
			}
			if (employees.size() > 0) {
				currentEmployee = employees.get(0);
				currentContract = currentEmployee.getCurrentContract();
			}
		}
	}

	public static int getNumberOfDaysInMonth(int month, int year) {
		return new GregorianCalendar(year, month - 1, 1).getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	private void doActionFromToolBar(eSHOW eShow) {
		switch (eShow) {
		case NEWCONTRACT:
			ContractController newContractController = new ContractController(
					"New contract", "New contract:", currentEmployee,
					employees);
			newContractController.doShow();
			if (newContractController.isSave()) {
				processMouseClickOnEmployeeTable();
				int nRow = timeKeepingBookFrame.getTableEmployee()
						.getSelectedRow();
				loadDataIntoTable(employees);
				timeKeepingBookFrame.getTableEmployee().changeSelection(nRow,
						0, false, false);
			}
			break;
		case EDITCONTRACT:
			ContractController editContractController = new ContractController(
					"Edit contract", "Edit contract:", currentEmployee,
					employees);
			editContractController.doShow();
			editContractController.updateInformationForEdit();
			if (editContractController.isSave()) {
				processMouseClickOnEmployeeTable();
			}
			break;
		case DELETECONTRACT:
			// JOptionPane.showMessageDialog(null,"Delete this contract");
			DefaultMutableTreeNode node = (DefaultMutableTreeNode) timeKeepingBookFrame
					.getTreeViewContract().getLastSelectedPathComponent();
			if (node == null) {
				JOptionPane.showMessageDialog(null,
						"Please choose the contract you want to delete.");
				return;
			}
			Object obj = node.getUserObject();
			if (obj instanceof Contract) {
				Object[] options = { "Yes", "No" };
				ImageIcon icon = new ImageIcon("images/trash.png");
				int ret = JOptionPane.showOptionDialog(null,
						"Are you sure you want to delete [" + obj.toString()
								+ "]?", "Delete Contract",
						JOptionPane.DEFAULT_OPTION,
						JOptionPane.QUESTION_MESSAGE,
						icon, options, options[0]);
				if (ret != 0)
					return;
				if (currentEmployee != null) {
					ArrayList<Contract> listContracts = (ArrayList<Contract>) currentEmployee
							.getContracts();
					if (listContracts.contains(obj)) {
						listContracts.remove(obj);
						currentEmployee.setContracts(listContracts);
					} else {
						currentEmployee.setContracts(null);
					}
					processMouseClickOnEmployeeTable();
					loadDataIntoTable(employees);
					// Update employees list in Staff class
					Staff.getInstance().setEmployees(employees);
				}
			}

			break;
		case CALCPAYROLL:
			calcPayroll();
			break;
		}
	}

	private void calcPayroll() {
		CPrintPreviewController printPreviewController = new CPrintPreviewController();
		printPreviewController.setTittle("Payroll Report");
		printPreviewController.setListEmployee(employees);
		printPreviewController.setMonth(nMonthSelected);
		printPreviewController.setYear(nYearSelected);
		printPreviewController.setContent(printPreviewController
				.createPayRollReport());
		printPreviewController.doShow();
	}

	private void processTreeSelection() {
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) timeKeepingBookFrame
				.getTreeViewContract().getLastSelectedPathComponent();
		Object obj = node.getUserObject();
		if (obj instanceof Contract) {
			JOptionPane.showMessageDialog(null, obj.toString());
		}
	}

	private void doGetListContractForEmployee(Employee currentEmployee) {
		Contract currentContract = currentEmployee.getCurrentContract();
		// Need remove all child node
		timeKeepingBookFrame.getRootNode().removeAllChildren();
		if (currentContract != null) {
			// Add Current Contract here
			DefaultMutableTreeNode currentContractNode = new DefaultMutableTreeNode(
					currentContract);
			timeKeepingBookFrame.getRootNode().add(currentContractNode);
		}
		// 20110822: LH modified
		List<Contract> remainingContracts = (ArrayList<Contract>) currentEmployee
				.getContracts();
		if (remainingContracts != null && remainingContracts.size() > 1) {
			// Show contract history here
			DefaultMutableTreeNode oldContractNode = new DefaultMutableTreeNode(
					"Old Contracts");
			timeKeepingBookFrame.getRootNode().add(oldContractNode);
			for (int i = 0; i < remainingContracts.size() - 1; i++) {
				DefaultMutableTreeNode oldContractSubNode = new DefaultMutableTreeNode(
						remainingContracts.get(i));
				oldContractNode.add(oldContractSubNode);
			}
		}
		timeKeepingBookFrame.getTreeViewContract().updateUI();
	}

	private void doSearch() {
		String strName = timeKeepingBookFrame.getTextFieldSearch().getText();
		List<Employee> listSearch = new ArrayList<Employee>();
		if (employees != null) {
			for (int i = 0; i < employees.size(); i++) {
				Employee employee = employees.get(i);
				if (employee.getFullName().indexOf(strName) != -1) {
					listSearch.add(employee);
				}
			}
			loadDataIntoTable(listSearch);
		}
	}

	private void doShowAll() {
		loadDataIntoTable(employees);
	}

	private void processMouseClickOnEmployeeTable() {
		int row = timeKeepingBookFrame.getTableEmployee().getSelectedRow();
		currentEmployee = employees.get(row);
		/*currentContract = currentEmployee.getCurrentContract();
		if (currentContract != null) {
			doProcessMonthSelection();
			doGetListContractForEmployee(currentEmployee);
			if (currentEmployee.getContracts() != null)
				timeKeepingBookFrame.getTreeViewContract().expandRow(2);
		} else {
			timeKeepingBookFrame.getRootNode().removeAllChildren();
			timeKeepingBookFrame.getTreeViewContract().updateUI();
		}*/
		// 20110822: LH modified
		doProcessMonthSelection();
		doGetListContractForEmployee(currentEmployee);
		if (currentEmployee.getContracts() != null) {
			timeKeepingBookFrame.getTreeViewContract().expandRow(2);
		}
		enableControlForContract();
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	private void doProcessSaveTimeKeeping() {
		if (currentEmployee != null) {
			Contract correctContract = currentEmployee.searchCorrespondingContract(nYearSelected, nMonthSelected);
			// 20110822: LH modified
			/*if (currentContract != null) {
				CTimeKeepingBook keepBook = currentContract.getTimeKeeping();*/
			if (correctContract != null) {
				CTimeKeepingBook keepBook = correctContract.getTimeKeeping();
				if (keepBook == null)
					keepBook = new CTimeKeepingBook();
				if (keepBook != null) {
					CTimeKeepingSheet keepSheet = keepBook.get(
							nMonthSelected, nYearSelected);
					if (keepSheet == null) {
						keepSheet = new CTimeKeepingSheet();
						Date dateKeepSheet = new Date(nYearSelected,
								nMonthSelected, 1);
						keepSheet.setLastModified(dateKeepSheet);
					}
					keepSheet.clear();
					for (int i = 0; i < nNumberDayOfMonth; i++) {
						CTimeKeepingDetailInfor keepDetail = new CTimeKeepingDetailInfor();
						Boolean bcheck = (Boolean) ((Vector<Object>) timeKeepingBookFrame
								.getTableModelTimeKeeping().getDataVector()
								.elementAt(i)).elementAt(0);
						Date date = new Date(nYearSelected, nMonthSelected,
								i + 1);
						keepDetail.setDateWorking(date);
						keepDetail.setIsWorking(bcheck);
						keepSheet.add(keepDetail);
					}
					keepBook.add(keepSheet);
					// 20110822: LH modified
					/*currentContract.setTimeKeeping(keepBook);
					currentEmployee.setCurrentContract(currentContract);*/
					correctContract.setTimeKeeping(keepBook);
					int index = currentEmployee.getContracts().indexOf(correctContract);
					currentEmployee.updateContract(index, correctContract);
					// Update employees list in Staff class
					Staff.getInstance().setEmployees(employees);
				}
			}
		}
	}

	private void updateDataTableTimeKeeping(int n, int month, int year) {
		// Updating Status foreach Keeptime details
		Object[][] objData = new Object[n][];
		Object[] objColumn = new Object[] { "Working", "Date" };
		int day;
		String date;
		if (currentEmployee != null) {
			Contract correctContract = currentEmployee.searchCorrespondingContract(year, month);
			// 20110822: LH modified
			if (correctContract != null) {
				CTimeKeepingBook keepBook = correctContract.getTimeKeeping();
			/*if (currentContract != null) {
				CTimeKeepingBook keepBook = currentContract.getTimeKeeping();*/
				if (keepBook != null) {
					CTimeKeepingSheet keepSheet = keepBook.get(month, year);
					if (keepSheet == null) {
						for (int i = 0; i < n; i++) {
							day = i + 1;
							date = getStringOfDate(year, month, day);
							Object[] objValue = new Object[] {
									new Boolean(false), date };
							objData[i] = objValue;
						}
					} else {
						for (int i = 0; i < keepSheet.size(); i++) {
							CTimeKeepingDetailInfor infor = keepSheet.get(i);
							day = i + 1;
							date = getStringOfDate(year, month, day);
							Object[] objValue = new Object[] {
									new Boolean(infor.getIsWorking()), date };
							objData[i] = objValue;
						}
					}

				}
			}
		} else {
			for (int i = 0; i < n; i++) {
				day = i + 1;
				date = getStringOfDate(year, month, day);
				Object[] objValue = new Object[] { new Boolean(false), date };
				objData[i] = objValue;
			}
		}
		timeKeepingBookFrame.getTableModelTimeKeeping().setDataVector(
				objData, objColumn);
		timeKeepingBookFrame.getTableTimeKeeping().getColumn("Working")
				.setCellRenderer(new CMyTableCellRenderer());
		timeKeepingBookFrame.getTableTimeKeeping().getColumn("Working")
				.setCellEditor(new CMyTableCellEditor());
	}

	private String getStringOfDate(int year, int month, int day) {
		return year + "-" + ((month < 10) ? ("0" + month) : month)
				+ "-" + ((day < 10) ? ("0" + day) : day);
	}

	private void doProcessYearSelection() {
		doProcessMonthSelection();
	}

	private void doProcessMonthSelection() {
		int month = Integer.parseInt(timeKeepingBookFrame.getComboBoxMonth()
				.getSelectedItem().toString());
		int year = Integer.parseInt(timeKeepingBookFrame.getComboBoxYear()
				.getSelectedItem().toString());
		int n = getNumberOfDaysInMonth(month, year);
		nMonthSelected = month;
		nYearSelected = year;
		nNumberDayOfMonth = n;
		updateDataTableTimeKeeping(n, month, year);
	}

	private void doExit() {
		Object[] options = { "Yes", "No" };
		ImageIcon icon = new ImageIcon("images/exit.png");
		int ret = JOptionPane.showOptionDialog(null,
				"Do you want to close this function?", null,

				JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,

				icon, options, options[0]);
		if (ret == 0)
			timeKeepingBookFrame.dispose();

	}

	private class CProcessMouseEvent extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub
			Object o = arg0.getSource();
			if (o.equals(timeKeepingBookFrame.getTableEmployee())) {
				processMouseClickOnEmployeeTable();
			}
		}

	}

	private class CButtonEvent implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Object o = e.getSource();
			if (o.equals(timeKeepingBookFrame.getButtonAddNewContract())) {
				doActionFromToolBar(eSHOW.NEWCONTRACT);
			} else if (o.equals(timeKeepingBookFrame.getButtonModifyContract())) {
				doActionFromToolBar(eSHOW.EDITCONTRACT);
			} else if (o.equals(timeKeepingBookFrame.getButtonTrash())) {
				doActionFromToolBar(eSHOW.DELETECONTRACT);
			} else if (o.equals(timeKeepingBookFrame.getButtonCalcPayroll())) {
				doActionFromToolBar(eSHOW.CALCPAYROLL);
			} else if (o.equals(timeKeepingBookFrame.getMenuItemSystemExit())) {
				doExit();
			} else if (o.equals(timeKeepingBookFrame.getComboBoxMonth())) {
				doProcessMonthSelection();
			} else if (o.equals(timeKeepingBookFrame.getComboBoxYear())) {
				doProcessYearSelection();
			} else if (o.equals(timeKeepingBookFrame
					.getButtonSaveTimeKeeping())) {
				doProcessSaveTimeKeeping();
			} else if (o.equals(timeKeepingBookFrame.getButtonClose())) {
				doExit();
			} else if (o.equals(timeKeepingBookFrame.getButtonSearch())) {
				doSearch();
			} else if (o.equals(timeKeepingBookFrame.getButtonShowAll())) {
				doShowAll();
			}
		}

	}

	private class CTreeEvent implements TreeSelectionListener {

		@Override
		public void valueChanged(TreeSelectionEvent e) {
			// TODO Auto-generated method stub
			processTreeSelection();
		}

	}
}
