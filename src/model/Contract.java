package model;

import java.io.Serializable;
import java.util.Date;

/*
 * @author Tu Thi Xuan Hien
 */
public class Contract  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Position position;
	private WorkingTime workingTime;
	private Date startDate;
	private Duration time;
	private CTimeKeepingBook m_TimeKeeping;
	public Contract()
	{
		this.position = new Position();
		this.workingTime = null;
		this.startDate = new Date();
		this.time = null;
		m_TimeKeeping=new CTimeKeepingBook();
	}
	public Contract(Position position, WorkingTime workingTime, Date startDate,
			Duration time,CTimeKeepingBook timeKeeping) {
		this.position = position;
		this.workingTime = workingTime;
		this.startDate = startDate;
		this.time = time;
		m_TimeKeeping=timeKeeping;
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
	public void setTimeKeeping(CTimeKeepingBook TimeKeeping)
	{
		this.m_TimeKeeping=TimeKeeping;
	}
	public CTimeKeepingBook getTimeKeeping()
	{
		return this.m_TimeKeeping;
	}

}