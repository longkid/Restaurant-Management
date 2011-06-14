package model;
/*
 * @author Tu Thi Xuan Hien
 */

import java.io.Serializable;
import java.util.ArrayList;

public class PositionList implements Serializable 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Position> m_PositionList;
	public PositionList()
	{
		m_PositionList=new ArrayList<Position>();
	}
	public boolean checkExist(Position aPostion)
	{
		for(Position postion : m_PositionList)
		{
			if(postion.getTitle().equals(aPostion.getTitle()))
				return true;
		}
		return false;
	}
	public void add(Position aPostion)
	{
		m_PositionList.add(aPostion);
	}
	public void update(int nIndex,Position aPostion)
	{
		m_PositionList.set(nIndex, aPostion);
	}
	public Position get(int nIndex)
	{
		return m_PositionList.get(nIndex);
	}
	public void removeAt(int nIndex)
	{
		m_PositionList.remove(nIndex);
	}
	public int Count()
	{
		return m_PositionList.size();
	}
}
