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

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public WorkingTime getWorkingTime() {
		return workingTime;
	}

	public void setWorkingTime(WorkingTime workingTime) {
		this.workingTime = workingTime;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Duration getTime() {
		return time;
	}

	public void setTime(Duration time) {
		this.time = time;
	}

}