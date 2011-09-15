package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
/*
 * @author Tu Thi Xuan Hien
 * Description: 
 * 		this class is used to save the timekeeping of each month (TimeKeepingSheet)		
 * 		base on this class, we can search the timekeeping of each month and each day
 * in brief:
 * 		each employee has one or more contracts
 * 		each contract has one timekeeping table
 * 		each timekeeping table contains the timekeeping information of several months  
 *  	the timekeeping of each month contains the timekeeping of several days  
 *  	Employee->Contract->CTimeKeepingBook->TimeKeepingSheet->CTimeKeepingDetailInfor
 * Attributes:
 * 		 ArrayList<TimeKeepingSheet> listTimeKeepingSheet;
 * 		this variable is used to save timekeeping of month 
 * 		Date dateLastModified;this variable is used to save the last month timekeeping 			
 * Modified Date: 
 */
@SuppressWarnings("deprecation")
public class TimeKeepingBook implements Serializable {
	private static final long serialVersionUID = 1L;
	private ArrayList<TimeKeepingSheet> listTimeKeepingSheet;
	private Date dateLastModified;

	public TimeKeepingBook() {
		dateLastModified = new Date();
		listTimeKeepingSheet = new ArrayList<TimeKeepingSheet>();
	}
	/* *******************************************************************************
	 * Description: 
	 * 		this method is used to check if a particular month or year exists in timekeeping table
	 * 		if it exist, return TRUE otherwise return FALSE
	 * Parameters:
	 * 		int month - month timekeeping
	 * 		int year - year timekeeping
	 * Exception:
	 * 		error if listTimeKeepingSheet is not allocated memory
	 * 		or aSheet is null
	 * return type: boolean
	 * *******************************************************************************/
	public boolean contains(int month, int year) {
		for (int i = 0; i < size(); i++) {
			TimeKeepingSheet aSheet = get(i);
			if (aSheet.getLastModified().getMonth() == month
					&& (aSheet.getLastModified().getYear() == year))
				return true;
		}
		return false;
	}
	/* *******************************************************************************
	 * Description: 
	 * 		this method is used to check  if the timekeeping of a particular month (TimeKeepingSheet)
	 * 		exists in the timekeeping table 
	 * 		if it exists, return to the position of that month in the list, otherwise, return to -1 
	 * 	Parameters:
	 * 		TimeKeepingSheet aSheet - month of timekeeping
	 * Exception:
	 * 		error occurs if listTimeKeepingSheet is not allocated with memory
	 * 		or aSheet is null
	 * return type: int
	 * *******************************************************************************/
	public int contains(TimeKeepingSheet aSheet) {
		for (int i = 0; i < size(); i++) {
			if (aSheet.getLastModified().getMonth() == get(i).getLastModified()
					.getMonth()
					&& aSheet.getLastModified().getYear() == get(i)
							.getLastModified().getYear())
				return i;
		}
		return -1;
	}

	public TimeKeepingBook(Date dateLastModified) {
		this.dateLastModified = dateLastModified;
		listTimeKeepingSheet = new ArrayList<TimeKeepingSheet>();
	}
	/* *******************************************************************************
	 * Description: 
	 * 		set the last modified date of the timekeeping table		
	 * Parameters:
	 * 		Date dateLastModified - the last modified date
	 * return type: void
	 * *******************************************************************************/
	public void setLastModified(Date dateLastModified) {
		this.dateLastModified = dateLastModified;
	}
	/* *******************************************************************************
	 * Description:  		
	 * 		get the last modified date of the timekeeping table
	 * return type: Date
	 * *******************************************************************************/
	public Date getLastModified() {
		return this.dateLastModified;
	}
	/* *******************************************************************************
	 * Description: 
	 * 		reset the month at n in the timekeeping table 	
	 * Parameters:
	 * 		int n - place n
	 * 		TimeKeepingSheet aSheet - month of timekeeping
	 * Exception:
	 * 		error occurs if listTimeKeepingSheet is not allocated with the memory
	 * 		or n is invalid (n<0 or n>size of the list)
	 * return type: void
	 * *******************************************************************************/
	public void set(int n, TimeKeepingSheet aSheet) {
		this.listTimeKeepingSheet.set(n, aSheet);
	}
	/* *******************************************************************************
	 * Description: 
	 * 		this method is used to add a new month (TimeKeepingSheet) into the timekeeping tabe 
	 * 		(listTimeKeepingSheet)
	 * Parameters:
	 * 		TimeKeepingSheet aSheet (one any month)
	 * Exception:
	 * 		error occur if listTimeKeepingSheet is not allocated with the memory
	 * return type: void
	 * *******************************************************************************/
	public void add(TimeKeepingSheet aSheet) {
		int n = contains(aSheet);
		if (n == -1)
			this.listTimeKeepingSheet.add(aSheet);
		else
			this.listTimeKeepingSheet.set(n, aSheet);
	}
	/* *******************************************************************************
	 * Description: 
	 * 		this method return the size of timekeeping in the timekeeping tale
	 ********************************************************************************/
	public int size() {
		return this.listTimeKeepingSheet.size();
	}
	/* *******************************************************************************
	 * Description: 
	 * 		this method is used to get the month (TimeKeepingSheet) at  n in 
	 * 	  	timekeeping table(listTimeKeepingSheet)
	 * Parameters:
	 * 		int n - (n is place)
	 * Exception:
	 * 		error occurs if  listTimeKeepingSheet is not allocated with the memory
	 * 		or n is invalid (n<0 or n>size of the list)
	 * return type: TimeKeepingSheet
	 * *******************************************************************************/
	public TimeKeepingSheet get(int n) {
		return this.listTimeKeepingSheet.get(n);
	}
	/* *******************************************************************************
	 * Description: 
	 * 		this method return one month timekeeping in the timekeeping table
	 * 		base on any month and year
	 * 		return null if not found month and year in timekeeping table
	 * Parameters:
	 * 		int month - 
	 * 		int year - 
	 * Exception:
	 * 		error if  listTimeKeepingSheet is not allocated the memory
	 * 		or n is invalid (n<0 or n>size of the list)
	 * return type: boolean
	 * *******************************************************************************/
	public TimeKeepingSheet get(int month, int year) {
		for (int i = 0; i < size(); i++) {
			TimeKeepingSheet aSheet = get(i);
			if (aSheet.getLastModified().getMonth() == month
					&& (aSheet.getLastModified().getYear() == year))
				return aSheet;
		}
		return null;
	}
	/* *******************************************************************************
	 * Description: 
	 * 		this method is used to delete a month (TimeKeepingSheet) at n in
	 *  	the timekeeping listTimeKeepingSheet 
	 * Parameters:
	 * 		int n - any place
	 * Exception:
	 * 		error occurs if listTimeKeepingSheet is not allocated with the memory 
	 * 		or n is invalid, (n<0 or n>size of the list)
	 * return type: void
	 * *******************************************************************************/
	public void remove(int n) {
		this.listTimeKeepingSheet.remove(n);
	}
	/* *******************************************************************************
	 * Description: 
	 * 		this method is used to delete a month (TimeKeepingSheet) in the list
	 * Parameters:
	 * 		TimeKeepingSheet aSheet (any month)
	 * Exception:
	 * 		error occurs if listTimeKeepingSheet is not allocated with the memory
	 * return type: void
	 * *******************************************************************************/
	public void remove(TimeKeepingSheet aSheet) {
		this.listTimeKeepingSheet.remove(aSheet);
	}
}
