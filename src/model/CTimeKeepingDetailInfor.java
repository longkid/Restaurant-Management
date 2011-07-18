package model;

import java.io.Serializable;
import java.util.Date;
/*
 * @author Tu Thi Xuan Hien
 *  Description: 
 *  	this class is used to store the detail information of the working day of one employee
 *  	this object will be store in the CTimeKeepingSheet
 * Attributes:
 *  	boolean m_bIsWorking
 *  		if m_bIsWorking=true then the employee present
 *  		if m_bIsWorking=false then the employee absent
 *  	Date m_dateWorking
 *  		store working day(example: 08/07/2011)
 *  	String m_strWhoCheck
 *  		Store the name of the timekeeper (name of the login user)
 *  	String m_strReason
 *  		store detail of timekeeping (example: reason of the absent, late, etc...
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
	 * Description: set status of working day of employee
	 * Parameters:
	 * 		boolean bIsWorking- true present, else absent
	 * return type: void
	 * *******************************************************************************/
	public void setIsWorking(boolean bIsWorking)
	{
		this.m_bIsWorking=bIsWorking;
	}
	/* *******************************************************************************
	 * Description: return working day of employee
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
