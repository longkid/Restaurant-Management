package controller;

/**
 * Author: Le Duy Phong

 * Purpose of this class: this class control the process between StaffFrame and some concern class in model
 */
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import model.Employee;
import model.Sex;
import model.Staff;

import view.EmployeeDetailsFrame;
import view.StaffFrame;

public class StaffController {
	public static StaffController singleton = new StaffController();
	private StaffFrame view;

	// this function is used to open StaffFrame for managing staff
	public void visible() {
		this.view = new StaffFrame();
		this.initView();
		this.view.setVisible(true);
	}

	// this function get employee from employees and fill into table
	private Object[][] fill() {
		List<Employee> employees = Staff.getInstance().getEmployees();
		Object[][] objects = new Object[employees.size() + 1][4];
		objects[0][0] = "Full name";
		objects[0][1] = "Sex";
		objects[0][2] = "Birthday";
		objects[0][3] = "Identity card No.";
		for (int i = 1; i <= employees.size(); i++) {
			objects[i][0] = employees.get(i - 1).getFullName();
			objects[i][1] = (employees.get(i - 1).getSex() == Sex.MALE) ? "Male"
					: "Female";
			objects[i][2] = Staff.getDateFormat().format(employees.get(i - 1)
					.getBirthday());
			objects[i][3] = employees.get(i - 1).getIdentityCard().getCardNum();
		}
		return objects;
	}

	// init view and fill data to table
	private void initView() {
		this.view.getStaffTable().setModel(
				new DefaultTableModel(fill(), new String[4]));
	}

	public void refresh() {
		this.view.getStaffTable().setModel(
				new DefaultTableModel(fill(), new String[4]));
	}

	public void update() {
		int index = this.view.getStaffTable().getSelectedRow();
		index--;
		if (index < 0) {
			JOptionPane.showMessageDialog(view, "You must select an employee to update");
		} else {
			EmployeeController.singleton.displayEmployee(index);
		}
	}

	// this function is used to open a form for input data when user add new
	// employee
	public void create() {
		EmployeeController.singleton.clearAllInputField();
		EmployeeController.singleton.setIndex(0);
		EmployeeController.singleton.setUpdate(false);
		EmployeeController.singleton.setVisible(true);
	}

	// this function is used to delete an employee
	public void delete() {
		int index = this.view.getStaffTable().getSelectedRow();
		index--;
		if (index < 0) {
			JOptionPane.showMessageDialog(view, "You must select an employee to delete");
		} else {
			String employeeName = Staff.getInstance().getEmployees().get(index)
					.getFullName();
			int ret = JOptionPane.showOptionDialog(view,
					"Are you sure you want to delete " + employeeName + "?",
					"Delete Employee", JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, null, null);
			if (ret == JOptionPane.YES_OPTION) {
				Staff.getInstance().deleteEmployee(index);
				refresh();
			}
		}
			
	}
	
	// 20110828: LH added
	public void viewDetails() {
		int index = this.view.getStaffTable().getSelectedRow();
		index--;
		if (index < 0) {
			JOptionPane.showMessageDialog(view, "You must select an employee to view details");
		} else {
			new EmployeeDetailsFrame().displayEmployee(index);
		}
	}
}
