package model;

/*
 * @author Tu Thi Xuan Hien
 */
public class Position {
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

	public Position(PositionTitle title, int salary, int otherSalary) {
		this.title = title;
		this.salary = salary;
		this.otherSalary = otherSalary;
	}

	public PositionTitle getTitle() {
		return title;
	}

	public void setTitle(PositionTitle title) {
		this.title = title;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public int getOtherSalary() {
		return otherSalary;
	}

	public void setOtherSalary(int otherSalary) {
		this.otherSalary = otherSalary;
	}

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
}
