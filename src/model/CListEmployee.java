package model;

import java.io.Serializable;
import java.util.ArrayList;

/*
 * @author Tu Thi Xuan Hien
 */
public class CListEmployee  implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Employee> m_listEmployee;
	public CListEmployee()
	{
		m_listEmployee=new ArrayList<Employee>();
	}
	public void add(Employee employee)
	{
		m_listEmployee.add(employee);
	}
	public Employee get(int n)
	{
		return m_listEmployee.get(n);
	}
	public void remove(int n)
	{
		m_listEmployee.remove(n);
	}
	public void remove(Employee employee)
	{
		m_listEmployee.remove(employee);
	}
	public int size()
	{
		return m_listEmployee.size();
	}
	public void set(int n, Employee employee)
	{
		m_listEmployee.set(n, employee);
	}
}
