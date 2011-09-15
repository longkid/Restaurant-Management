package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
/*
 * @author Tu Thi Xuan Hien
 *   Description:
 *   	this class is used to store several objects of TimeKeepingDetailInfo
 *   	it presents any working month of the employee 
 *   	to check the number of working days of the employee which is called getWorkingDay()
 *   Attributes:
 *   	ArrayList<TimeKeepingDetailInfo> listTimeKeepingDetailInfo;
 *   	this variable is used to store the list TimeKeepingDetailInfo 
 *   	Date dateLastModified;
 *   	this variable is used to store the last modified date of the timekeeping of any month  		
 */
public class TimeKeepingSheet implements Serializable {
	private static final long serialVersionUID = 1L;
	private ArrayList<TimeKeepingDetailInfo> listTimeKeepingDetailInfo;
	private Date dateLastModified;
	/* *******************************************************************************
	 * Description: Default Constructor
	 * *******************************************************************************/
	public TimeKeepingSheet() {
		dateLastModified=new Date();
		listTimeKeepingDetailInfo=new ArrayList<TimeKeepingDetailInfo>();
	}
	/* *******************************************************************************
	 * Description: Constructor have one parameter
	 * *******************************************************************************/
	public TimeKeepingSheet(Date dateLastModified) {
		this.dateLastModified=dateLastModified;
		listTimeKeepingDetailInfo=new ArrayList<TimeKeepingDetailInfo>();
	}
	/* *******************************************************************************
	 * Description: 
	 * 		return to the list of the timekeeping day in a month
	 * 		each object in the list has the TimeKeepingDetailInfo type
	 * return type: ArrayList<TimeKeepingDetailInfo>
	 * *******************************************************************************/
	public ArrayList<TimeKeepingDetailInfo>getList() {
		return listTimeKeepingDetailInfo;
	}
	/* *******************************************************************************
	 * Description: 
	 * 		return to number of the working days in the month
	 * Exception:
	 * 		error occurs if listTimeKeepingDetailInfo is not allocated with the memory
	 * return type: int
	 * *******************************************************************************/
	public int size() {
		return listTimeKeepingDetailInfo.size();
	}
	/* *******************************************************************************
	 * Description: 
	 * 		get the detail of a working day(TimeKeepingDetailInfo) at n in the list
	 * 	  	listTimeKeepingDetailInfo
	 * Parameters:
	 * 		int n - (n is the position)
	 * Exception:
	 * 		error occurs if listTimeKeepingDetailInfo is not allocated with the memory
	 * 		or n is invalid, ( n<0 or n>size of the list)
	 * return type: TimeKeepingDetailInfo
	 * *******************************************************************************/
	public TimeKeepingDetailInfo get(int n) {
		return listTimeKeepingDetailInfo.get(n);
	}
	/* *******************************************************************************
	 * Description: 
	 * 		add one working day (TimeKeepingDetailInfo) into the list listTimeKeepingDetailInfo
	 * Parameters:
	 * 		TimeKeepingDetailInfo aKeep 
	 * Exception:
	 * 		error occurs if listTimeKeepingDetailInfo is not allocated with the memory
	 * return type: void
	 * *******************************************************************************/
	public void add(TimeKeepingDetailInfo aKeep) {
		listTimeKeepingDetailInfo.add(aKeep);
	}
	/* *******************************************************************************
	 * Description: 
	 * 		remove one working day (TimeKeepingDetailInfo) from the list
	 *  	listTimeKeepingDetailInfo
	 * Parameters:
	 * 		TimeKeepingDetailInfo aKeep 
	 * Exception:
	 * 		error occurs if  listTimeKeepingDetailInfo is not allocated with the memory
	 * return type: void
	 * *******************************************************************************/
	public void remove(TimeKeepingDetailInfo aKeep) {
		listTimeKeepingDetailInfo.remove(aKeep);
	}
	/* *******************************************************************************
	 * Description: 
	 * 		remove one working day (TimeKeepingDetailInfo) at n from the list 
	 *  	listTimeKeepingDetailInfo 
	 * Parameters:
	 * 		int n - n is the position that we want to remove 
	 * Exception:
	 * 		error occurs if listTimeKeepingDetailInfo is not allocated with the memory 
	 * 		or n is invalid (example n<0 or  n>size of the list)
	 * return type: void
	 * *******************************************************************************/
	public void remove(int n) {
		listTimeKeepingDetailInfo.remove(n);
	}
	/* *******************************************************************************
	 * Description: 
	 * 		set the last modified date of the working month		
	 * Parameters:
	 * 		Date dateLastModified - the last modified date
	 * return type: void
	 * *******************************************************************************/
	public void setLastModified(Date dateLastModified) {
		this.dateLastModified=dateLastModified;
	}
	/* *******************************************************************************
	 * Description: 
	 * 		get the last modified date
	 * return type: Date
	 * *******************************************************************************/
	public Date getLastModified() {
		return this.dateLastModified;
	}
	/* *******************************************************************************
	 * Description: 
	 * 		remove all working days in a month
	 * Exception:
	 * 		error occurs if listTimeKeepingDetailInfo is not allocated with the memory
	 * return type: void
	 * *******************************************************************************/
	public void clear() {
		listTimeKeepingDetailInfo.clear();
	}
	/* *******************************************************************************
	 * Description: 
	 * 		get sum of the working days of the employee in a month
	 * Exception:
	 * 		error occurs if listTimeKeepingDetailInfo is not allocated with the memory
	 * 		or TimeKeepingDetailInfo info is null
	 * return type: int
	 * *******************************************************************************/
	public int getWorkingDay() {
		int n = 0;
		for (TimeKeepingDetailInfo info : listTimeKeepingDetailInfo) {
			if (info.getIsWorking())
				n++;
		}
		return n;
	}
}
