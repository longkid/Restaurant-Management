package controller;
/**
 * Author: Le Duy Phong

 * Purpose of this class: this class control the process between StaffFrame and some concern class in model
 */
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import model.Employee;
import model.ProcessFile;
import model.Staff;

import view.EmployeeFrame;
import view.StaffFrame;

public class StaffController {
	public static StaffController singleton = new StaffController();
	private StaffFrame view;
//this function is used to open StaffFrame for managing staff
	public void visible(){
		this.view=new StaffFrame();
		this.initView();
		this.view.setVisible(true);
	}
//this function get employee from employees	and fill into table
	private Object[][] fill() {
		List<Employee> employees = Staff.getInstance().getEmployees();
		Object[][] objects = new Object[employees.size()+1][3];
		objects[0][0] = "Full name";
		objects[0][1] = "Birthday";
		objects[0][2] ="No identity card";
		for (int i = 1; i <= employees.size(); i++) {
			objects[i][0] = employees.get(i-1).getFullName();
			objects[i][1] = Staff.getInstance().dateFormat.format(employees.get(i-1).getBirthday());
			objects[i][2] = employees.get(i-1).getIdentityCard().getCardNum();
		}
		return objects;
	}
//init view and fill data to table
	private void initView() {

		this.view.getTable_1().setModel(
				new DefaultTableModel(fill(), new String[] { "Full name",
						"Birthday", "No identity card" }));
	}

	public void refresh() {
		this.view.getTable_1().setModel(
				new DefaultTableModel(fill(), new String[] { "Full name",
						"Birthday", "No identity card" }));
	}

	public void update() {
		int index = this.view.getTable_1().getSelectedRow();
		index--;
		if(index<0){return;}
		EmployeeController.singleton.displayEmployee(index);
	}
	//this function is used to open a form for input data when user add new employee
	public void create(){
		EmployeeController.singleton.setIndex(0);
		EmployeeController.singleton.setUpdate(false);		
		EmployeeController.singleton.setVisible(true);
		
	}
	//this fuction is used to delete an employee
	public void delete() {
		// TODO Auto-generated method stub
		int index = this.view.getTable_1().getSelectedRow();
		index--;
		if(index<0){return;}
		Staff.getInstance().deleteEmployee(index);
		ProcessFile.WriteData(Staff.getInstance().getEmployees(), ProcessFile.FILENAME_EMPLOYEE);
		JOptionPane.showMessageDialog(this.view, "Delete successful!");
		this.refresh();
	}
}
