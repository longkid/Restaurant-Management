package model;

import java.io.Serializable;
import java.util.Date;
/*
 * @author Tu Thi Xuan Hien
 *  Description: 
 *  	this class is used to store the information of a working day of each employee
 *  	this object will be stored in the CTimeKeepingSheet
 * Attributes:
 *  	boolean m_bIsWorking
 *  		if m_bIsWorking=true then the employee is present
 *  		if m_bIsWorking=false then the employee is absent
 *  	Date m_dateWorking
 *  		stores the working date(example: 08/07/2011)
 *  	String m_strWhoCheck
 *  		Stores the name of the timekeeper (name of the login user)
 *  	String m_strReason
 *  		stores the detail of timekeeping (example: reason of the absent, late, etc...
 *  Modified Date:
 */
public class CTimeKeepingDetailInfor implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean m_bIsWorking;
	private Date m_dateWorking;
	private String m_strWhoCheck;
	private String m_strReason;
	/* *******************************************************************************
	 * Description: Default Constructor	
	 * *******************************************************************************/
	public CTimeKeepingDetailInfor()
	{
		this.m_bIsWorking=false;
		this.m_dateWorking=new Date();
		this.m_strWhoCheck="";
		this.m_strReason="";
	}
	/* *******************************************************************************
	 * Description: Full Constructor
	 * Parameters:
	 * 		boolean bIsWorking 
	 * 		Date dateWorking  
	 * 		String strWhoCheck
	 * 		String strReason
	 * *******************************************************************************/
	public CTimeKeepingDetailInfor(boolean bIsWorking,Date dateWorking,String strWhoCheck,String strReason)
	{
		this.m_bIsWorking=bIsWorking;
		this.m_dateWorking=dateWorking;
		this.m_strWhoCheck=strWhoCheck;
		this.m_strReason=strReason;
	}
	/* *******************************************************************************
	 * Description: set working status of employee in a particular day
	 * Parameters:
	 * 		boolean bIsWorking- true: the employee is present, otherwise he or she is absent
	 * return type: void
	 * *******************************************************************************/
	public void setIsWorking(boolean bIsWorking)
	{
		this.m_bIsWorking=bIsWorking;
	}
	/* *******************************************************************************
	 * Description: check if the employee is present on that day
	 * return type: boolean
	 * *******************************************************************************/
	public boolean getIsWorking()
	{
		return this.m_bIsWorking;
	}
	/* *******************************************************************************
	 * Description: set working date of the employee
	 * Parameters:
	 * 		Date dateWorking -
	 * return type: void
	 * *******************************************************************************/
	public void setDateWorking(Date dateWorking)
	{
		this.m_dateWorking=dateWorking;
	}
	/* *******************************************************************************
	 * Description: return working date of employee. 	
	 * return type: Date
	 * *******************************************************************************/
	public Date getDateWorking()
	{
		return this.m_dateWorking;
	}
	/* *******************************************************************************
	 * Description: set timekeeper 
	 * Parameters:
	 * 		String strWhoCheck - timekeeper name
	 * return type: void
	 * *******************************************************************************/
	public void setWhoCheck(String strWhoCheck)
	{
		this.m_strWhoCheck=strWhoCheck;
	}
	/* *******************************************************************************
	 * Description: return timekeeper of the date selected
	 * return type: String
	 * *******************************************************************************/
	public String getWhoCheck()
	{
		return this.m_strWhoCheck;
	}
	/* *******************************************************************************
	 * Description: 
	 * 		set reason of each working day
	 * Parameters:
	 * 		String strReason - reason
	 * return type: void
	 * *******************************************************************************/
	public void setReason(String strReason)
	{
		this.m_strReason=strReason;
	}
	/* *******************************************************************************
	 * Description: 
	 * 		get reason of working day selected
	 * return type: String
	 * *******************************************************************************/
	public String getReason()
	{
		return this.m_strReason;
	}
}
