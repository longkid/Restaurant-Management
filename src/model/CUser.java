package model;

import java.io.Serializable;

public class CUser implements Serializable
{
	private static final long serialVersionUID = 1L;
	private String m_strUserName="";
	private String m_strPassword="";
	private boolean m_bIsRememberPassword=false;
	public CUser()
	{
		this.m_strUserName="";
		this.m_strPassword="";
		this.m_bIsRememberPassword=false;
	}
	public CUser(String strUserName,String strPassword,boolean bIsRememberPassword)
	{
		this.m_strUserName=strUserName;
		this.m_strPassword=strPassword;
		this.m_bIsRememberPassword=bIsRememberPassword;
	}
	public String getUserName()
	{
		return this.m_strUserName;
	}
	public String getPassword()
	{
		return this.m_strPassword;
	}
	public boolean getRememberPassword()
	{
		return m_bIsRememberPassword;
	}
	public void setRememeberPassword(boolean bIsRememberPassword)
	{
		m_bIsRememberPassword=bIsRememberPassword ;
	}
}
