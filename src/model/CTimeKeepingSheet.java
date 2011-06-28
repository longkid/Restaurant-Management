package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
/*
 * @author Tu Thi Xuan Hien
 */
public class CTimeKeepingSheet implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<CTimeKeepingDetailInfor> m_ListTimeKeepingDetailInfor;
	private Date m_dateLastModified;
	public CTimeKeepingSheet()
	{
		m_dateLastModified=new Date();
		m_ListTimeKeepingDetailInfor=new ArrayList<CTimeKeepingDetailInfor>();
	}
	public CTimeKeepingSheet(Date dateLastModified)
	{
		m_dateLastModified=dateLastModified;
		m_ListTimeKeepingDetailInfor=new ArrayList<CTimeKeepingDetailInfor>();
	}
	public ArrayList<CTimeKeepingDetailInfor>getList()
	{
		return m_ListTimeKeepingDetailInfor;
	}
	public int size()
	{
		return m_ListTimeKeepingDetailInfor.size();
	}
	public CTimeKeepingDetailInfor get(int n)
	{
		return m_ListTimeKeepingDetailInfor.get(n);
	}
	public void add(CTimeKeepingDetailInfor aKeep)
	{
		m_ListTimeKeepingDetailInfor.add(aKeep);
	}
	public void remove(CTimeKeepingDetailInfor aKeep)
	{
		m_ListTimeKeepingDetailInfor.remove(aKeep);
	}
	public void remove(int n)
	{
		m_ListTimeKeepingDetailInfor.remove(n);
	}
	public void setLastModified(Date dateLastModified)
	{
		this.m_dateLastModified=dateLastModified;
	}
	public Date getLastModified()
	{
		return this.m_dateLastModified;
	}
	public void clear()
	{
		m_ListTimeKeepingDetailInfor.clear();
	}
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
