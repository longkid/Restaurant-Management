package model;

import java.io.Serializable;
import java.util.Date;
/*
 * @author Tu Thi Xuan Hien
 */
public class CTimeKeepingDetailInfor implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean m_bIsWorking;
	private Date m_dateWorking;
	private String m_strWhoCheck;
	private String m_strReason;
	public CTimeKeepingDetailInfor()
	{
		this.m_bIsWorking=false;
		this.m_dateWorking=new Date();
		this.m_strWhoCheck="";
		this.m_strReason="";
	}
	public CTimeKeepingDetailInfor(boolean bIsWorking,Date dateWorking,String strWhoCheck,String strReason)
	{
		this.m_bIsWorking=bIsWorking;
		this.m_dateWorking=dateWorking;
		this.m_strWhoCheck=strWhoCheck;
		this.m_strReason=strReason;
	}
	public void setIsWorking(boolean bIsWorking)
	{
		this.m_bIsWorking=bIsWorking;
	}
	public boolean getIsWorking()
	{
		return this.m_bIsWorking;
	}
	public void setDateWorking(Date dateWorking)
	{
		this.m_dateWorking=dateWorking;
	}
	public Date getDateWorking()
	{
		return this.m_dateWorking;
	}
	public void setWhoCheck(String strWhoCheck)
	{
		this.m_strWhoCheck=strWhoCheck;
	}
	public String getWhoCheck()
	{
		return this.m_strWhoCheck;
	}
	public void setReason(String strReason)
	{
		this.m_strReason=strReason;
	}
	public String getReason()
	{
		return this.m_strReason;
	}
}
