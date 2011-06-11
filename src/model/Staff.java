package model;

import java.util.ArrayList;
import java.util.List;

/*
 * This class is used for managing all employees in the restaurant.
 * There should be only one instance of this class in the application.
 */
public class Staff {
	private static Staff singleton;
	private List<Employee> employees = new ArrayList<Employee>();

	private Staff() {
	}

	public static Staff getInstance() {
		if (singleton == null) {
			singleton = new Staff();
		}
		
		return singleton;
	}

	/*
	 * Add the specified employee
	 */
	public void addEmployee(Employee emp) {
		employees.add(emp);
	}

	/*
	 * Update information of existed employee with information of specified employee
	 */
	public void updateEmployee(int index, Employee emp) {
		employees.set(index, emp);
	}

	/*
	 * Remove the employee equivalent to specified employee
	 */
	public void deleteEmployee(int index) {
		employees.remove(index);
	}
}
