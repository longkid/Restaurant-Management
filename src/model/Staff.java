package model;

/*Author: Le Duy Phong
 * This class is used for managing all employees in the restaurant.
 * There should be only one instance of this class in the application.
 */
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class Staff {
	private static Staff singleton;
	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy-MM-dd");
	private List<Employee> employees = null;
	private List<Position> positions = null;

	@SuppressWarnings("unchecked")
	private Staff() {
		positions = (List<Position>) FileProcessing.ReadData(FileProcessing.FILENAME_POSITION);
		if (positions == null) {
			initializePositions();
			FileProcessing.WriteData(positions, FileProcessing.FILENAME_POSITION);
		}
		
		employees = (List<Employee>) FileProcessing.ReadData(FileProcessing.FILENAME_EMPLOYEE);
		if (employees == null) {
			//initializeEmployees();
			employees = new ArrayList<Employee>();
			FileProcessing.WriteData(employees, FileProcessing.FILENAME_EMPLOYEE);
		}
	}

	public static Staff getInstance() {
		if (singleton == null) {
			singleton = new Staff();
		}
		
		return singleton;
	}

	public static SimpleDateFormat getDateFormat() {
		return dateFormat;
	}

	public static void setDateFormat(SimpleDateFormat dateFormat) {
		Staff.dateFormat = dateFormat;
	}

	private void initializePositions() {
		positions = new ArrayList<Position>();
		for (PositionTitle title : PositionTitle.values()) {
			positions.add(Position.createPosition(title));
		}
	}
	
	public List<Position> getPositions() {
		return positions;
	}
	
	public void setPositions(List<Position> positions) {
		this.positions = positions;
		writeData(positions, FileProcessing.FILENAME_POSITION);
	}

	public Position getPosition(PositionTitle title) {
		return positions.get(title.ordinal());
	}
	
	public void updatePosition(PositionTitle title, Position p) {
		Position temp = getPosition(title);
		temp.setSalary(p.getSalary());
		temp.setOtherSalary(p.getOtherSalary());
		positions.set(title.ordinal(), temp);
		writeData(positions, FileProcessing.FILENAME_POSITION);
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
		writeData(employees, FileProcessing.FILENAME_EMPLOYEE);
	}

	/*
	 * Add the specified employee
	 */
	public void addEmployee(Employee emp) {
		employees.add(emp);
		writeData(employees, FileProcessing.FILENAME_EMPLOYEE);
	}

	/*
	 * Update information of existed employee with information of specified employee
	 */
	public void updateEmployee(int index, Employee emp) {
		employees.set(index, emp);
		writeData(employees, FileProcessing.FILENAME_EMPLOYEE);
	}

	/*
	 * Remove the employee equivalent to specified employee
	 */
	public void deleteEmployee(int index) {
		employees.remove(index);
		writeData(employees, FileProcessing.FILENAME_EMPLOYEE);
	}

	private void writeData(Object obj, String strPath) {
		boolean result = FileProcessing.WriteData(obj, strPath);
		if (result) {
			JOptionPane.showMessageDialog(null, "Successful Update");
		} else {
			JOptionPane.showMessageDialog(null, "Failed Update");
		}
	}
}
