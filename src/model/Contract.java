package model;

import java.util.Date;

/*
 * @author Tu Thi Xuan Hien
 */
public class Contract {
	private Position position;
	private WorkingTime workingTime;
	private Date startDate;
	private Duration time;

	public Contract(Position position, WorkingTime workingTime, Date startDate,
			Duration time) {
		this.position = position;
		this.workingTime = workingTime;
		this.startDate = startDate;
		this.time = time;
	}

}