package model;

import java.io.Serializable;
import java.util.Date;
/*
 * @author Tu Thi Xuan Hien
 *  Description: 
 *  	this class is used to store the information of a working day of each employee
 *  	this object will be stored in the CTimeKeepingSheet
 * Attributes:
 *  	boolean bIsWorking
 *  		if bIsWorking=true then the employee is present
 *  		if bIsWorking=false then the employee is absent
 *  	Date dateWorking
 *  		stores the working date(example: 08/07/2011)
 *  	String strWhoCheck
 *  		Stores the name of the timekeeper (name of the login user)
 *  	String strReason
 *  		stores the detail of timekeeping (example: reason of the absent, late, etc...
 *  Modified Date:
 */
public class TimeKeepingDetailInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean bIsWorking;
	private Date dateWorking;
	private String strWhoCheck;
	private String strReason;
	/* *******************************************************************************
	 * Description: Default Constructor	
	 * *******************************************************************************/
	public TimeKeepingDetailInfo() {
		this.bIsWorking=false;
		this.dateWorking=new Date();
		this.strWhoCheck="";
		this.strReason="";
	}
	/* *******************************************************************************
	 * Description: Full Constructor
	 * Parameters:
	 * 		boolean bIsWorking 
	 * 		Date dateWorking  
	 * 		String strWhoCheck
	 * 		String strReason
	 * *******************************************************************************/
	public TimeKeepingDetailInfo(boolean bIsWorking, Date dateWorking,
			String strWhoCheck, String strReason) {
		this.bIsWorking = bIsWorking;
		this.dateWorking = dateWorking;
		this.strWhoCheck = strWhoCheck;
		this.strReason = strReason;
	}
	/* *******************************************************************************
	 * Description: set working status of employee in a particular day
	 * Parameters:
	 * 		boolean bIsWorking- true: the employee is present, otherwise he or she is absent
	 * return type: void
	 * *******************************************************************************/
	public void setIsWorking(boolean bIsWorking) {
		this.bIsWorking=bIsWorking;
	}
	/* *******************************************************************************
	 * Description: check if the employee is present on that day
	 * return type: boolean
	 * *******************************************************************************/
	public boolean getIsWorking() {
		return this.bIsWorking;
	}
	/* *******************************************************************************
	 * Description: set working date of the employee
	 * Parameters:
	 * 		Date dateWorking -
	 * return type: void
	 * *******************************************************************************/
	public void setDateWorking(Date dateWorking) {
		this.dateWorking=dateWorking;
	}
	/* *******************************************************************************
	 * Description: return working date of employee. 	
	 * return type: Date
	 * *******************************************************************************/
	public Date getDateWorking() {
		return this.dateWorking;
	}
	/* *******************************************************************************
	 * Description: set timekeeper 
	 * Parameters:
	 * 		String strWhoCheck - timekeeper name
	 * return type: void
	 * *******************************************************************************/
	public void setWhoCheck(String strWhoCheck) {
		this.strWhoCheck=strWhoCheck;
	}
	/* *******************************************************************************
	 * Description: return timekeeper of the date selected
	 * return type: String
	 * *******************************************************************************/
	public String getWhoCheck() {
		return this.strWhoCheck;
	}
	/* *******************************************************************************
	 * Description: 
	 * 		set reason of each working day
	 * Parameters:
	 * 		String strReason - reason
	 * return type: void
	 * *******************************************************************************/
	public void setReason(String strReason) {
		this.strReason=strReason;
	}
	/* *******************************************************************************
	 * Description: 
	 * 		get reason of working day selected
	 * return type: String
	 * *******************************************************************************/
	public String getReason() {
		return this.strReason;
	}
}
