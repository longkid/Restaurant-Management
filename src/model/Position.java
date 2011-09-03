package model;

import java.io.Serializable;

/*
 * @author Tu Thi Xuan Hien
 * Description: 
 * 		this class is used to store the position of the employee, each 
 * 		position contains the basic salary and other salary
 * Attributes:
 * 		PositionTitle title;==> title of the position
 *		int salary; ==> basic salary depending on the position
 *		int otherSalary;==> other salary
 *Modified Date:
 */
public class Position implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int[] SALARIES = { 300, 500, 200, 800, 250, 400, 100,
			150, 100, 150, 250 };
	private static final int[] OTHER_SALARIES = { 50, 100, 200, 300 };
	private static final int ACCOUNTANT_SALARY = 300;

	private PositionTitle title;
	private int salary; // unit: US Dollar
	private int otherSalary; // unit: US Dollar

	/* *******************************************************************************
	 * Description: Default Constructor
	 * *****************************************
	 * *************************************
	 */
	public Position() {
		this.title = PositionTitle.ACCOUNTANT;
		this.salary = ACCOUNTANT_SALARY;
		this.otherSalary = 0;
	}

	/* *******************************************************************************
	 * Description: Full Constructor
	 * ********************************************
	 * **********************************
	 */
	public Position(PositionTitle title, int salary, int otherSalary) {
		this.title = title;
		this.salary = salary;
		this.otherSalary = otherSalary;
	}

	/* *******************************************************************************
	 * Description: get the position of the employee return type: PositionTitle
	 * *
	 * *************************************************************************
	 * ****
	 */
	public PositionTitle getTitle() {
		return title;
	}

	/* *******************************************************************************
	 * Description: set the position of the employee Parameters: PositionTitle
	 * title - return type: void
	 * ************************************************
	 * ******************************
	 */
	public void setTitle(PositionTitle title) {
		this.title = title;
	}

	/* *******************************************************************************
	 * Description: get the salary of the employee base on the position return
	 * type: PositionTitle
	 * ******************************************************
	 * ************************
	 */
	public int getSalary() {
		return salary;
	}

	/* *******************************************************************************
	 * Description: set the salary of the employee Parameters: int salary -
	 * salary (unit USD) return type: void
	 * **************************************
	 * ****************************************
	 */
	public void setSalary(int salary) {
		this.salary = salary;
	}

	/* *******************************************************************************
	 * Description: get the other salary return type: int
	 * ***********************
	 * *******************************************************
	 */
	public int getOtherSalary() {
		return otherSalary;
	}

	/* *******************************************************************************
	 * Description: set the other salary of the employee Parameters: int
	 * otherSalary - other salary (unit USD) return type: void
	 * ******************
	 * ************************************************************
	 */
	public void setOtherSalary(int otherSalary) {
		this.otherSalary = otherSalary;
	}

	public static Position createPosition(PositionTitle title) {
		int index = title.ordinal();
		switch (title) {
		case ACCOUNTANT:
			return new Position(PositionTitle.ACCOUNTANT, SALARIES[index],
					OTHER_SALARIES[1]);
		case HEAD_ACCOUNTANT:
			return new Position(PositionTitle.HEAD_ACCOUNTANT, SALARIES[index],
					OTHER_SALARIES[2]);
		case CASHIER:
			return new Position(PositionTitle.CASHIER, SALARIES[index],
					OTHER_SALARIES[0]);
		case DIRECTOR:
			return new Position(PositionTitle.DIRECTOR, SALARIES[index],
					OTHER_SALARIES[3]);
		case CHEF:
			return new Position(PositionTitle.CHEF, SALARIES[index],
					OTHER_SALARIES[0]);
		case EXECUTIVE_CHEF:
			return new Position(PositionTitle.EXECUTIVE_CHEF, SALARIES[index],
					OTHER_SALARIES[1]);
		case BUSBOY:
			return new Position(PositionTitle.BUSBOY, SALARIES[index],
					OTHER_SALARIES[0]);
		case DISHWASHER:
			return new Position(PositionTitle.DISHWASHER, SALARIES[index],
					OTHER_SALARIES[0]);
		case RUNNER:
			return new Position(PositionTitle.RUNNER, SALARIES[index],
					OTHER_SALARIES[0]);
		case SERVER:
			return new Position(PositionTitle.SERVER, SALARIES[index],
					OTHER_SALARIES[0]);
		case HEAD_SERVER:
			return new Position(PositionTitle.HEAD_SERVER, SALARIES[index],
					OTHER_SALARIES[1]);
		default:
			return null;
		}
	}
}
