package model;

import java.io.Serializable;
import java.util.Date;
/*
 * @author Tu Thi Xuan Hien
 * Description: 
 * 		this class is used to save the contract of each Employee
 * 		each employee has one or several contracts
 * 		the Employee class has 2 properties:
 * 		List<Contract> contracts;==> List of contracts created before
 *		Contract currentContract;==> current contract
 * Attributes:
 * 		Position position; ==> store the position of each employee(Position, salary, ...)
 *		Date startDate;==> store the Starting date of the contract
 *		Duration time;==> store the Ending date of the contract
 *			TWO_MONTHS, ONE_YEAR, THREE_YEARS, NO_LIMIT
 *		CTimeKeepingBook m_TimeKeeping;==> store time keeping
 * Modified Date: 
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
	/* *******************************************************************************
	 * Description: 
	 * 		Default Constructor
	 * 		Initialize the default value of the attributes	
	 * *******************************************************************************/
	public Contract()
	{
		this.position = new Position();
		this.workingTime = null;
		this.startDate = new Date();
		this.time = null;
		m_TimeKeeping=new CTimeKeepingBook();
	}
	/* *******************************************************************************
	 * Description: 
	 * 		Full Constructor
	 * 		set the default value of the attributes	
	 * *******************************************************************************/
	public Contract(Position position, WorkingTime workingTime, Date startDate,
			Duration time,CTimeKeepingBook timeKeeping) {
		this.position = position;
		this.workingTime = workingTime;
		this.startDate = startDate;
		this.time = time;
		m_TimeKeeping=timeKeeping;
	}
	/* *******************************************************************************
	 * Description: 
	 * 		return the position of employee in the contract 
	 * return type: Position
	 * *******************************************************************************/
	public Position getPosition() {
		return position;
	}
	/* *******************************************************************************
	 * Description: 
	 * 		set the position of employee in contract
	 * Parameters:
	 * 		Position position 
	 * return type: void
	 * *******************************************************************************/
	public void setPosition(Position position) {
		this.position = position;
	}

	public WorkingTime getWorkingTime() {
		return workingTime;
	}

	public void setWorkingTime(WorkingTime workingTime) {
		this.workingTime = workingTime;
	}
	/* *******************************************************************************
	 * Description: 
	 * 		get the start date of the contract
	 * return type: Date
	 * *******************************************************************************/
	public Date getStartDate() {
		return startDate;
	}
	/* *******************************************************************************
	 * Description: 
	 * 		set start date of the contract
	 * Parameters:
	 * 		Date startDate
	 * return type: void
	 * *******************************************************************************/
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	/* *******************************************************************************
	 * Description: 
	 * 		get time of the contract
	 * return type: Duration
	 * *******************************************************************************/
	public Duration getTime() {
		return time;
	}
	/* *******************************************************************************
	 * Description: 
	 * 		set time of the contract
	 * Parameters:
	 * 		Duration time 
	 * 			TWO_MONTHS, ONE_YEAR, THREE_YEARS, NO_LIMIT
	 * return type: void
	 * *******************************************************************************/
	public void setTime(Duration time) {
		this.time = time;
	}
	/* *******************************************************************************
	 * Description: 
	 * 		set the time keeping 
	 * Parameters:
	 * 		CTimeKeepingBook TimeKeeping
	 * return type: void
	 * *******************************************************************************/
	public void setTimeKeeping(CTimeKeepingBook TimeKeeping)
	{
		this.m_TimeKeeping=TimeKeeping;
	}
	/* *******************************************************************************
	 * Description: 
	 * 		get the time keeping 
	 * return type: CTimeKeepingBook
	 * *******************************************************************************/
	public CTimeKeepingBook getTimeKeeping()
	{
		return this.m_TimeKeeping;
	}
	/* *******************************************************************************
	 * Description: 
	 * 		get the information of the contract
	 * 		ví dụ:
	 * 			(Accountant)-08/07/2011
	 * 		the information is display on the Nodes of JTree
	 * 				-Contract History
	 * 					(Director)-08/07/2011
	 * 					-Old Contract
	 * 						(Accountant)-08/07/2010
	 * 						(Dishwasher)-08/07/2009
	 * return type: String
	 * *******************************************************************************/
	private String parseNodeTitle()
	{
		int day=this.getStartDate().getDate();
		int month=this.getStartDate().getMonth();
		int year=this.getStartDate().getYear();
		 if(year-1900<0)
			 year=year+1900;
		String strNode="("+this.getPosition().getTitle().toString()+")-"+day+"/"+month+"/"+(year);
		
		return strNode;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return parseNodeTitle();
	}
}