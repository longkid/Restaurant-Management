package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
/*
 * @author Tu Thi Xuan Hien
 * Description: 
 * 		this class us used to save timekeeping of each month (CTimeKeepingSheet)		
 * 		we can use this class to get time keeping of any month in the year, and we get 
 * 		timekeeping detail of the day
 * general view:
 * 		one employee has one or several contract
 * 		one contract has one timekeeping table
 * 		one timekeeping table has several months
 *  	one month timekeeping has several days  
 *  	Employee->Contract->CTimeKeepingBook->CTimeKeepingSheet->CTimeKeepingDetailInfor
 * Attributes:
 * 		 ArrayList<CTimeKeepingSheet> m_ListTimeKeepingSheet;
 * 		this variable is used to save timekeeping of month 
 * 		Date m_dateLastModified;this variable is used to save the last month timekeeping 			
 * Modified Date: 
 */
@SuppressWarnings("deprecation")
public class CTimeKeepingBook implements Serializable{
	private static final long serialVersionUID = 1L;
	private ArrayList<CTimeKeepingSheet> m_ListTimeKeepingSheet;
	private Date m_dateLastModified;
	public CTimeKeepingBook()
	{
		m_dateLastModified=new Date();
		m_ListTimeKeepingSheet=new ArrayList<CTimeKeepingSheet>();
	}
	/* *******************************************************************************
	 * Description: 
	 * 		this method check month and year timekeeping is existing in timekeeping 
	 * 		if exist the return true else return false	
	 * Parameters:
	 * 		int month - month timekeeping
	 * 		int year - year timekeeping
	 * Exception:
	 * 		error if m_ListTimeKeepingSheet is not allocated memory
	 * 		or aSheet is null
	 * return type: boolean
	 * *******************************************************************************/
	public boolean contains(int month,int year)
	{
		for(int i=0;i<size();i++)
		{
			CTimeKeepingSheet aSheet=get(i);
			if(aSheet.getLastModified().getMonth()==month && (aSheet.getLastModified().getYear()==year))
				return true;
		}
		return false;
	}
	/* *******************************************************************************
	 * Description: 
	 * 		this method is used to check  timekeeping of month (CTimeKeepingSheet) is existing in timekeeping table 
	 * 		if exist then return the place of the month in list else return -1 
	 * 	Parameters:
	 * 		CTimeKeepingSheet aSheet - month of timekeeping
	 * Exception:
	 * 		error if m_ListTimeKeepingSheet is not allocated memory
	 * 		or aSheet is null
	 * return type: int
	 * *******************************************************************************/
	public int contains(CTimeKeepingSheet aSheet)
	{
		for(int i=0;i<size();i++)
		{
			if(aSheet.getLastModified().getMonth()==get(i).getLastModified().getMonth() && aSheet.getLastModified().getYear()==get(i).getLastModified().getYear())
				return i;
		}
		return -1;
	}
	public CTimeKeepingBook(Date dateLastModified)
	{
		m_dateLastModified=dateLastModified;
		m_ListTimeKeepingSheet=new ArrayList<CTimeKeepingSheet>();
	}
	/* *******************************************************************************
	 * Description: 
	 * 		set the last day modify of the timekeeping table		
	 * Parameters:
	 * 		Date dateLastModified - date of last modify
	 * return type: void
	 * *******************************************************************************/
	public void setLastModified(Date dateLastModified)
	{
		m_dateLastModified=dateLastModified;
	}
	/* *******************************************************************************
	 * Description: 
	 * 		
	 * 		get the date of the last modify in timekeeping table
	 * return type: Date
	 * *******************************************************************************/
	public Date getLastModified()
	{
		return this.m_dateLastModified;
	}
	/* *******************************************************************************
	 * Description: 
	 * 		reset the month of the timekeeping in the timekeeping table at the place n		
	 * Parameters:
	 * 		int n - place n
	 * 		CTimeKeepingSheet aSheet - month of timekeeping
	 * Exception:
	 * 		error if m_ListTimeKeepingSheet is not allocated the memory
	 * 		or n is invalid (n<0 or n>size of the list)
	 * return type: void
	 * *******************************************************************************/
	public void set(int n, CTimeKeepingSheet aSheet)
	{
		this.m_ListTimeKeepingSheet.set(n, aSheet);
	}
	/* *******************************************************************************
	 * Description: 
	 * 		this method is used to add the new month (CTimeKeepingSheet) into timekeeping tabe 
	 * 		(m_ListTimeKeepingSheet)
	 * Parameters:
	 * 		CTimeKeepingSheet aSheet (one any month)
	 * Exception:
	 * 		error if m_ListTimeKeepingSheet is not allocated the memory
	 * return type: void
	 * *******************************************************************************/
	public void add(CTimeKeepingSheet aSheet)
	{
		int n=contains(aSheet);
		if(n==-1)
			this.m_ListTimeKeepingSheet.add(aSheet);
		else
			this.m_ListTimeKeepingSheet.set(n, aSheet);
	}
	/* *******************************************************************************
	 * Description: 
	 * 		this method return the size of timekeeping in the timekeeping tale
	 ********************************************************************************/
	public int size()
	{
		return this.m_ListTimeKeepingSheet.size();
	}
	/* *******************************************************************************
	 * Description: 
	 * 		this method is used to get one month (CTimeKeepingSheet) at the place n in 
	 * 	  	timekeeping table(m_ListTimeKeepingSheet)
	 * Parameters:
	 * 		int n - (n is place)
	 * Exception:
	 * 		error if  m_ListTimeKeepingSheet is not allocated the memory
	 * 		or n is invalid (n<0 or n>size of the list)
	 * return type: CTimeKeepingSheet
	 * *******************************************************************************/
	public CTimeKeepingSheet get(int n)
	{
		return this.m_ListTimeKeepingSheet.get(n);
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
	 * 		error if  m_ListTimeKeepingSheet is not allocated the memory
	 * 		or n is invalid (n<0 or n>size of the list)
	 * return type: boolean
	 * *******************************************************************************/
	public CTimeKeepingSheet get(int month,int year)
	{
		for(int i=0;i<size();i++)
		{
			CTimeKeepingSheet aSheet=get(i);
			if(aSheet.getLastModified().getMonth()==month && (aSheet.getLastModified().getYear()==year))
				return aSheet;
		}
		return null;
	}
	/* *******************************************************************************
	 * Description: 
	 * 		this method is used to delete one month timekeeping(CTimeKeepingSheet) in
	 *  	the timekeeping m_ListTimeKeepingSheet at the place n
	 * Parameters:
	 * 		int n - any place
	 * Exception:
	 * 		error if m_ListTimeKeepingSheet is not allocated the memory 
	 * 		or n is invalid, (n<0 or n>size of the list)
	 * return type: void
	 * *******************************************************************************/
	public void remove(int n)
	{
		this.m_ListTimeKeepingSheet.remove(n);
	}
	/* *******************************************************************************
	 * Description: 
	 * 		this method is used to delete one month timekeeping (CTimeKeepingSheet) 
	 * Parameters:
	 * 		CTimeKeepingSheet aSheet (any month)
	 * Exception:
	 * 		error if m_ListTimeKeepingSheet is not allocated the memory
	 * return type: void
	 * *******************************************************************************/
	public void remove(CTimeKeepingSheet aSheet)
	{
		this.m_ListTimeKeepingSheet.remove(aSheet);
	}
}
