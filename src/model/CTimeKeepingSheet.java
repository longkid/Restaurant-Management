package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
/*
 * @author Tu Thi Xuan Hien
 *   Description:
 *   	this class is used to store several objects of CTimeKeepingDetailInfor
 *   	it presents any working month of the employee 
 *   	to check the number of working days of the employee which is called getWorkingDay()
 *   Attributes:
 *   	ArrayList<CTimeKeepingDetailInfor> m_ListTimeKeepingDetailInfor;
 *   	this variable is used to store the list CTimeKeepingDetailInfor 
 *   	Date m_dateLastModified;
 *   	this variable is used to store the last modified date of the timekeeping of any month  		
 */
public class CTimeKeepingSheet implements Serializable{
	private static final long serialVersionUID = 1L;
	private ArrayList<CTimeKeepingDetailInfor> m_ListTimeKeepingDetailInfor;
	private Date m_dateLastModified;
	/* *******************************************************************************
	 * Description: Default Constructor
	 * *******************************************************************************/
	public CTimeKeepingSheet()
	{
		m_dateLastModified=new Date();
		m_ListTimeKeepingDetailInfor=new ArrayList<CTimeKeepingDetailInfor>();
	}
	/* *******************************************************************************
	 * Description: Constructor have one parameter
	 * *******************************************************************************/
	public CTimeKeepingSheet(Date dateLastModified)
	{
		m_dateLastModified=dateLastModified;
		m_ListTimeKeepingDetailInfor=new ArrayList<CTimeKeepingDetailInfor>();
	}
	/* *******************************************************************************
	 * Description: 
	 * 		return to the list of the timekeeping day in a month
	 * 		each object in the list has the CTimeKeepingDetailInfor type
	 * return type: ArrayList<CTimeKeepingDetailInfor>
	 * *******************************************************************************/
	public ArrayList<CTimeKeepingDetailInfor>getList()
	{
		return m_ListTimeKeepingDetailInfor;
	}
	/* *******************************************************************************
	 * Description: 
	 * 		return to number of the working days in the month
	 * Exception:
	 * 		error occurs if m_ListTimeKeepingDetailInfor is not allocated with the memory
	 * return type: int
	 * *******************************************************************************/
	public int size()
	{
		return m_ListTimeKeepingDetailInfor.size();
	}
	/* *******************************************************************************
	 * Description: 
	 * 		get the detail of a working day(CTimeKeepingDetailInfor) at n in the list
	 * 	  	m_ListTimeKeepingDetailInfor
	 * Parameters:
	 * 		int n - (n is the position)
	 * Exception:
	 * 		error occurs if m_ListTimeKeepingDetailInfor is not allocated with the memory
	 * 		or n is invalid, ( n<0 or n>size of the list)
	 * return type: CTimeKeepingDetailInfor
	 * *******************************************************************************/
	public CTimeKeepingDetailInfor get(int n)
	{
		return m_ListTimeKeepingDetailInfor.get(n);
	}
	/* *******************************************************************************
	 * Description: 
	 * 		add one working day (CTimeKeepingDetailInfor) into the list m_ListTimeKeepingDetailInfor
	 * Parameters:
	 * 		CTimeKeepingDetailInfor aKeep 
	 * Exception:
	 * 		error occurs if m_ListTimeKeepingDetailInfor is not allocated with the memory
	 * return type: void
	 * *******************************************************************************/
	public void add(CTimeKeepingDetailInfor aKeep)
	{
		m_ListTimeKeepingDetailInfor.add(aKeep);
	}
	/* *******************************************************************************
	 * Description: 
	 * 		remove one working day (CTimeKeepingDetailInfor) from the list
	 *  	m_ListTimeKeepingDetailInfor
	 * Parameters:
	 * 		CTimeKeepingDetailInfor aKeep 
	 * Exception:
	 * 		error occurs if  m_ListTimeKeepingDetailInfor is not allocated with the memory
	 * return type: void
	 * *******************************************************************************/
	public void remove(CTimeKeepingDetailInfor aKeep)
	{
		m_ListTimeKeepingDetailInfor.remove(aKeep);
	}
	/* *******************************************************************************
	 * Description: 
	 * 		remove one working day (CTimeKeepingDetailInfor) at n from the list 
	 *  	m_ListTimeKeepingDetailInfor 
	 * Parameters:
	 * 		int n - n is the position that we want to remove 
	 * Exception:
	 * 		error occurs if m_ListTimeKeepingDetailInfor is not allocated with the memory 
	 * 		or n is invalid (example n<0 or  n>size of the list)
	 * return type: void
	 * *******************************************************************************/
	public void remove(int n)
	{
		m_ListTimeKeepingDetailInfor.remove(n);
	}
	/* *******************************************************************************
	 * Description: 
	 * 		set the last modified date of the working month		
	 * Parameters:
	 * 		Date dateLastModified - the last modified date
	 * return type: void
	 * *******************************************************************************/
	public void setLastModified(Date dateLastModified)
	{
		this.m_dateLastModified=dateLastModified;
	}
	/* *******************************************************************************
	 * Description: 
	 * 		get the last modified date
	 * return type: Date
	 * *******************************************************************************/
	public Date getLastModified()
	{
		return this.m_dateLastModified;
	}
	/* *******************************************************************************
	 * Description: 
	 * 		remove all working days in a month
	 * Exception:
	 * 		error occurs if m_ListTimeKeepingDetailInfor is not allocated with the memory
	 * return type: void
	 * *******************************************************************************/
	public void clear()
	{
		m_ListTimeKeepingDetailInfor.clear();
	}
	/* *******************************************************************************
	 * Description: 
	 * 		get sum of the working days of the employee in a month
	 * Exception:
	 * 		error occurs if m_ListTimeKeepingDetailInfor is not allocated with the memory
	 * 		or CTimeKeepingDetailInfor info is null
	 * return type: int
	 * *******************************************************************************/
	public int getWorkingDay()
	{
		int n=0;
		for(CTimeKeepingDetailInfor info :m_ListTimeKeepingDetailInfor )
		{
			if(info.getIsWorking())
				n++;
		}
		return n;
	}
}
