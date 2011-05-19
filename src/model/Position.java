package model;

/*
 * @author Tu Thi Xuan Hien
 */
public class Position {
	private PositionTitle title;
	private int salary;
	private int otherSalary;

	public Position() {
		this.title = PositionTitle.LEADER;
		this.salary = 0;
		this.otherSalary = 0;
	}

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
}
