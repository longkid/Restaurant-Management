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
 *  Modified Date:
 */
public class TimeKeepingDetailInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean bIsWorking;
	private Date dateWorking;

	public TimeKeepingDetailInfo() {
		this.bIsWorking=false;
		this.dateWorking=new Date();
	}

	public TimeKeepingDetailInfo(boolean bIsWorking, Date dateWorking) {
		this.bIsWorking = bIsWorking;
		this.dateWorking = dateWorking;
	}

	public void setIsWorking(boolean bIsWorking) {
		this.bIsWorking=bIsWorking;
	}

	public boolean getIsWorking() {
		return this.bIsWorking;
	}

	public void setDateWorking(Date dateWorking) {
		this.dateWorking=dateWorking;
	}

	public Date getDateWorking() {
		return this.dateWorking;
	}
}
