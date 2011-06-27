package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
/*
 * @author Tu Thi Xuan Hien
 */
@SuppressWarnings("deprecation")
public class CTimeKeepingBook implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<CTimeKeepingSheet> m_ListTimeKeepingSheet;
	private Date m_dateLastModified;
	public CTimeKeepingBook()
	{
		m_dateLastModified=new Date();
		m_ListTimeKeepingSheet=new ArrayList<CTimeKeepingSheet>();
	}
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
	public void setLastModified(Date dateLastModified)
	{
		m_dateLastModified=dateLastModified;
	}
	public Date getLastModified()
	{
		return this.m_dateLastModified;
	}
	public void set(int n, CTimeKeepingSheet aSheet)
	{
		this.m_ListTimeKeepingSheet.set(n, aSheet);
	}
	public void add(CTimeKeepingSheet aSheet)
	{
		int n=contains(aSheet);
		if(n==-1)
			this.m_ListTimeKeepingSheet.add(aSheet);
		else
			this.m_ListTimeKeepingSheet.set(n, aSheet);
	}
	public int size()
	{
		return this.m_ListTimeKeepingSheet.size();
	}
	public CTimeKeepingSheet get(int n)
	{
		return this.m_ListTimeKeepingSheet.get(n);
	}
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
	public void remove(int n)
	{
		this.m_ListTimeKeepingSheet.remove(n);
	}
	public void remove(CTimeKeepingSheet aSheet)
	{
		this.m_ListTimeKeepingSheet.remove(aSheet);
	}
}
