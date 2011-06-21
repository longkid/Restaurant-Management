package controller;
/**
 * Author: Le Duy Phong

 * Purpose of this class: this class control the process between StaffFrame and some concern class in model
 */
import java.util.List;

import javax.swing.table.DefaultTableModel;

import model.Employee;
import model.Staff;

import view.EmployeeFrame;
import view.StaffFrame;

public class StaffController {
	public static StaffController singleton = new StaffController();
	private StaffFrame view;

	public void visible(){
		this.view=new StaffFrame();
		this.initView();
		this.view.setVisible(true);
	}
	
	private Object[][] fill() {
		List<Employee> employees = Staff.getInstance().getEmployees();
		Object[][] objects = new Object[employees.size()][3];

		for (int i = 0; i < employees.size(); i++) {
			objects[i][0] = employees.get(i).getFullName();
			objects[i][1] = employees.get(i).getBirthday();
			objects[i][2] = employees.get(i).getIdentityCard().getCardNum();
		}
		return objects;
	}

	private void initView() {

		this.view.getTable_1().setModel(
				new DefaultTableModel(fill(), new String[] { "New column",
						"New column", "New column" }));
	}

	public void refresh() {
		this.view.getTable_1().setModel(
				new DefaultTableModel(fill(), new String[] { "New column",
						"New column", "New column" }));
	}

	public void update() {
		int index = this.view.getTable_1().getSelectedRow();
		Employee employee = Staff.getInstance().getEmployees()
				.get(index);
		EmployeeFrame.singleton.updateEmployee(employee);
	}
	public void create(){
		EmployeeFrame.singleton.setVisible(true);
	}
}
