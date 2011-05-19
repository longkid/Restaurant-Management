package model;

import java.util.Date;

/*
 * @author Tu Thi Xuan Hien
 */
public class Contract {
	private String department;
	private Position position;
	private WorkingTime workingTime;
	private Date startDate;
	private int duration;

	public Contract(String department, Position position,
			WorkingTime workingTime, Date startDate, int duration) {
		super();
		this.department = department;
		this.position = position;
		this.workingTime = workingTime;
		this.startDate = startDate;
		this.duration = duration;
	}

}