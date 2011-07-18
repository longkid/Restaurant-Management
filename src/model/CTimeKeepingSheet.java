package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
/*
 * @author Tu Thi Xuan Hien
 *   Description:
 *   	this class is used to store several objects of CTimeKeepingDetailInfor
 *   	it presents any working month of employee 
 *   	to check the number of working day of employee, we call getWorkingDay()
 *   Attributes:
 *   	ArrayList<CTimeKeepingDetailInfor> m_ListTimeKeepingDetailInfor;
 *   	this variable is used to store the list CTimeKeepingDetailInfor 
 *   	Date m_dateLastModified;
 *   	this variable is used to store the last modify of the timekeeping of the month  		
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
	 * 		return the list of the timekeeping day in the month
	 * 		each object in the list has type CTimeKeepingDetailInfor
	 * return type: ArrayList<CTimeKeepingDetailInfor>
	 * *******************************************************************************/
	public ArrayList<CTimeKeepingDetailInfor>getList()
	{
		return m_ListTimeKeepingDetailInfor;
	}
	/* *******************************************************************************
	 * Description: 
	 * 		return number of the working day in the month
	 * Exception:
	 * 		error if m_ListTimeKeepingDetailInfor is not allocated the memory
	 * return type: int
	 * *******************************************************************************/
	public int size()
	{
		return m_ListTimeKeepingDetailInfor.size();
	}
	/* *******************************************************************************
	 * Description: 
	 * 		get the detail of one working day(CTimeKeepingDetailInfor) at position n in the list
	 * 	  	m_ListTimeKeepingDetailInfor
	 * Parameters:
	 * 		int n - (n is position)
	 * Exception:
	 * 		error if m_ListTimeKeepingDetailInfor is not allocated with the memory
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
	 * 		error if m_ListTimeKeepingDetailInfor is not allocated with the memory
	 * return type: void
	 * *******************************************************************************/
	public void add(CTimeKeepingDetailInfor aKeep)
	{
		m_ListTimeKeepingDetailInfor.add(aKeep);
	}
	/* *******************************************************************************
	 * Description: 
	 * 		remove one working day (CTimeKeepingDetailInfor) in the list
	 *  	m_ListTimeKeepingDetailInfor
	 * Parameters:
	 * 		CTimeKeepingDetailInfor aKeep 
	 * Exception:
	 * 		error if  m_ListTimeKeepingDetailInfor is not allocated with the memory
	 * return type: void
	 * *******************************************************************************/
	public void remove(CTimeKeepingDetailInfor aKeep)
	{
		m_ListTimeKeepingDetailInfor.remove(aKeep);
	}
	/* *******************************************************************************
	 * Description: 
	 * 		remove one working day (CTimeKeepingDetailInfor) in the list 
	 *  	m_ListTimeKeepingDetailInfor at the position n
	 * Parameters:
	 * 		int n - n is the position that want to remove 
	 * Exception:
	 * 		error if m_ListTimeKeepingDetailInfor is not allocated with the memory 
	 * 		or n is invalid (example n<0 or  n>size of the list)
	 * return type: void
	 * *******************************************************************************/
	public void remove(int n)
	{
		m_ListTimeKeepingDetailInfor.remove(n);
	}
	/* *******************************************************************************
	 * Description: 
	 * 		set the last date modify in the working month		
	 * Parameters:
	 * 		Date dateLastModified - Ngày tháng chỉnh sửa cuối cùng
	 * return type: void
	 * *******************************************************************************/
	public void setLastModified(Date dateLastModified)
	{
		this.m_dateLastModified=dateLastModified;
	}
	/* *******************************************************************************
	 * Description: 
	 * 		get the late date modify
	 * return type: Date
	 * *******************************************************************************/
	public Date getLastModified()
	{
		return this.m_dateLastModified;
	}
	/* *******************************************************************************
	 * Description: 
	 * 		remve all working day in the month
	 * Exception:
	 * 		error if m_ListTimeKeepingDetailInfor is not allocated with the memory
	 * return type: void
	 * *******************************************************************************/
	public void clear()
	{
		m_ListTimeKeepingDetailInfor.clear();
	}
	/* *******************************************************************************
	 * Description: 
	 * 		get sum of the number working day of the employee in the month
	 * Exception:
	 * 		error if m_ListTimeKeepingDetailInfor is not allocated with the memory
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
