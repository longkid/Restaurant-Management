package model;

import java.io.Serializable;
/*
 * @author Tu Thi Xuan Hien
 * Description: 
 * 		this class is used to store the position of the employee, each position has 
 * 		salary and other salary
 * Attributes:
 * 		PositionTitle title;==> title of the position
 *		int salary; ==> basic salary depend on the position
 *		int otherSalary;==> other salary
 *Modified Date:
 */
public class Position implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int ACCOUNTANT_SALARY = 300;
	public static final int HEAD_ACCOUNTANT_SALARY = 500;
	public static final int CASHIER_SALARY = 200;
	public static final int DIRECTOR_SALARY = 800;
	public static final int CHEF_SALARY = 250;
	public static final int EXECUTIVE_CHEF_SALARY = 400;
	public static final int BUSBOY_SALARY = 100;
	public static final int DISHWASHER_SALARY = 150;
	public static final int RUNNER_SALARY = 100;
	public static final int SERVER_SALARY = 150;
	public static final int HEAD_SERVER_SALARY = 250;
	public static final int BONUS_LEVEL1 = 50;
	public static final int BONUS_LEVEL2 = 100;
	public static final int BONUS_LEVEL3 = 200;
	public static final int BONUS_LEVEL4 = 300;

	private PositionTitle title;
	private int salary; // unit: US Dollar
	private int otherSalary; // unit: US Dollar
	/* *******************************************************************************
	 * Description: Default Constructor
	 * *******************************************************************************/
	public Position() {
		this.title = PositionTitle.ACCOUNTANT;
		this.salary = ACCOUNTANT_SALARY;
		this.otherSalary = 0;
	}
	/* *******************************************************************************
	 * Description: Full Constructor
	 * *******************************************************************************/
	public Position(PositionTitle title, int salary, int otherSalary) {
		this.title = title;
		this.salary = salary;
		this.otherSalary = otherSalary;
	}
	/* *******************************************************************************
	 * Description: get the position of the employee 
	 * return type: PositionTitle
	 * *******************************************************************************/
	public PositionTitle getTitle() {
		return title;
	}
	/* *******************************************************************************
	 * Description: set the position of the employee
	 * Parameters:
	 * 		PositionTitle title - chức vụ
	 * return type: void
	 * *******************************************************************************/
	public void setTitle(PositionTitle title) {
		this.title = title;
	}
	/* *******************************************************************************
	 * Description: get the salary of the employee follow the position
	 * return type: PositionTitle
	 * *******************************************************************************/
	public int getSalary() {
		return salary;
	}
	/* *******************************************************************************
	 * Description: set the salary of the employee
	 * Parameters:
	 * 		int salary - salary (unit USD)
	 * return type: void
	 * *******************************************************************************/
	public void setSalary(int salary) {
		this.salary = salary;
	}
	/* *******************************************************************************
	 * Description: get the other salary
	 * return type: int
	 * *******************************************************************************/
	public int getOtherSalary() {
		return otherSalary;
	}
	/* *******************************************************************************
	 * Description: set the other salary of the employee 
	 * Parameters:
	 * 		int otherSalary - other salary (unit USD)
	 * return type: void
	 * *******************************************************************************/
	public void setOtherSalary(int otherSalary) {
		this.otherSalary = otherSalary;
	}
	/* *******************************************************************************
	 * Description: this method is used to create a position and add into position list
	 * Parameters:
	 * 		PositionTitle title
	 * note: this method is seldom used because the user interface allow entering only the salary 
	 * and other salary.
	 * return type: Position
	 * *******************************************************************************/
	public static Position createPosition(PositionTitle title) {
		switch (title) {
		case ACCOUNTANT:
			return new Position(PositionTitle.ACCOUNTANT, ACCOUNTANT_SALARY,
					BONUS_LEVEL2);
		case HEAD_ACCOUNTANT:
			return new Position(PositionTitle.HEAD_ACCOUNTANT,
					HEAD_ACCOUNTANT_SALARY, BONUS_LEVEL3);
		case CASHIER:
			return new Position(PositionTitle.CASHIER, CASHIER_SALARY,
					BONUS_LEVEL1);
		case DIRECTOR:
			return new Position(PositionTitle.DIRECTOR, DIRECTOR_SALARY,
					BONUS_LEVEL4);
		case CHEF:
			return new Position(PositionTitle.CHEF, CHEF_SALARY, BONUS_LEVEL1);
		case EXECUTIVE_CHEF:
			return new Position(PositionTitle.EXECUTIVE_CHEF,
					EXECUTIVE_CHEF_SALARY, BONUS_LEVEL2);
		case BUSBOY:
			return new Position(PositionTitle.BUSBOY, BUSBOY_SALARY,
					BONUS_LEVEL1);
		case DISHWASHER:
			return new Position(PositionTitle.DISHWASHER, DISHWASHER_SALARY,
					BONUS_LEVEL1);
		case RUNNER:
			return new Position(PositionTitle.RUNNER, RUNNER_SALARY,
					BONUS_LEVEL1);
		case SERVER:
			return new Position(PositionTitle.SERVER, SERVER_SALARY,
					BONUS_LEVEL1);
		case HEAD_SERVER:
			return new Position(PositionTitle.HEAD_SERVER, HEAD_SERVER_SALARY,
					BONUS_LEVEL2);
		default:
			return null;
		}
	}
	/* *******************************************************************************
	 * Description: 
	 * 		get the String title of each position
	 * Parameters:
	 * 		PositionTitle aPostionTitle 
	 * return type: String
	 * *******************************************************************************/
	public static String getPostionTitleString(PositionTitle aPostionTitle)
	{
		String strResult="";
		switch(aPostionTitle)
		{
		case ACCOUNTANT:
			strResult="Accountant";
			break;
		case HEAD_ACCOUNTANT:
			strResult="Head Accountant";
			break;
		case CASHIER:
			strResult="Cashier";
			break;
		case DIRECTOR:
			strResult="Director";
			break;
		case CHEF:
			strResult="Chef";
			break;
		case EXECUTIVE_CHEF:
			strResult="Executive Chef";
			break;
		case BUSBOY:
			strResult="Busboy";
			break;
		case DISHWASHER:
			strResult="Dishwasher";
			break;
		case RUNNER:
			strResult="Runner";
			break;
		case SERVER:
			strResult="Server";
			break;
		case HEAD_SERVER:
			strResult="Head Server";
			break;
		}
		return strResult;
	}
	/* *******************************************************************************
	 * Description: get title position base parameter position 
	 * Parameters:
	 * 		int nIndex - position in enum PostionTitle
	 * return type: PositionTitle
	 * *******************************************************************************/
	public static PositionTitle getPostionTitle(int nIndex)
	{
		PositionTitle posTitle=PositionTitle.ACCOUNTANT;
		switch(nIndex)
		{
		case 0:
			posTitle=PositionTitle.ACCOUNTANT;
			break;
		case 1:
			posTitle=PositionTitle.HEAD_ACCOUNTANT;
			break;
		case 2:
			posTitle=PositionTitle.CASHIER;
			break;
		case 3:
			posTitle=PositionTitle.DIRECTOR;
			break;
		case 4:
			posTitle=PositionTitle.CHEF;
			break;
		case 5:
			posTitle=PositionTitle.EXECUTIVE_CHEF;
			break;
		case 6:
			posTitle=PositionTitle.BUSBOY ;
			break;
		case 7:
			posTitle=PositionTitle.DISHWASHER;
			break;
		case 8:
			posTitle=PositionTitle.RUNNER;
			break;
		case 9:
			posTitle=PositionTitle.SERVER;
			break;
		case 10:
			posTitle=PositionTitle.HEAD_SERVER;
			break;
		}
		return posTitle;
	}
}
