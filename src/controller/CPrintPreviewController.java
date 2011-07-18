package controller;
/*
 * @author Tu Thi Xuan Hien
 */
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.List;

import model.CTimeKeepingBook;
import model.CTimeKeepingSheet;
import model.Employee;
import model.Position;
import view.CPrintPreview;

public class CPrintPreviewController 
{
	private CPrintPreview m_printPreview;
	private String m_strJobName="Test_Printting";
	private List<Employee> m_listEmployee;
	private int m_nMonthSelected;
	private int  m_nYearSelected;
	public CPrintPreviewController()
	{
		m_printPreview=new CPrintPreview ();
		m_printPreview.addWindowListener(
				new WindowAdapter() 
				{
					@Override
					public void windowClosing(WindowEvent e) {
						m_printPreview.dispose();
					}
				});
	}
	public void setTittle(String strTitle)
	{
		m_printPreview.setTitle(strTitle);
	}
	public void setListEmployee(List<Employee> listEmployee)
	{
		
		m_listEmployee=listEmployee;
	}
	public void setMonth(int nMonthSelected)
	{
		m_nMonthSelected=nMonthSelected;
	}
	public void setYear(int nYearSelected)
	{
		m_nYearSelected=nYearSelected;
	}
	public String createPayRollReport()
	{
		//String strContent="";
		String strContent="<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">";
		strContent+="<html xmlns=\"http://www.w3.org/1999/xhtml\">";
		strContent+="<head>";
		strContent+="<meta http-equiv=\"Content-Type\" content=\"text/html\"; charset=\"UTF-8\" />";
		strContent+="<title>PayRoll</title>";
		strContent+="</head>";
		
		strContent+="<body style=\"margin: 0;	padding: 0;\">";
		strContent+="<div class=\"wrapper\">";
		strContent+="<div style=\"background:#FF9933;border:1px\"></div>";
		strContent+="<h1 align=\"center\"  style=\"color: #779A00;font-size: 36px;font-weight: bold;\">PayRoll </h1><center>";
		strContent+="<div style=\"background:#FC9D36\"></div>";
		strContent+="<form id=\"form1\" name=\"form1\" method=\"post\" action=\"\">";
		strContent+="Month: ";
		strContent+="<font color=\"red\" style=\"font-weight:bold;\">"+m_nMonthSelected+"</font>";
		strContent+=" Year: ";
		strContent+="<font color=\"red\"  style=\"font-weight:bold;\">"+m_nYearSelected+"</font>";
		strContent+="</form></center>";
		strContent+="<table width=\"100%\" border=\"1\" cellpadding=\"0\" cellspacing=\"0\">";
		strContent+="<tr>";
		strContent+="<th width=\"18%\">FullName</th>";
		strContent+="<th width=\"19%\">Position</th>";
		strContent+="<th width=\"17%\">Salary</th>";
		strContent+="<th width=\"17%\">Other Salary</th>";
		strContent+="<th width=\"22%\">Workday number</th>";
		strContent+="<th width=\"24%\">Final Salary</th>";
		strContent+="</tr>";
		int nSumSalary=0;
		if(m_listEmployee!=null)
		{
			for(int i=0;i<m_listEmployee.size();i++)
			{
				Employee emp=m_listEmployee.get(i);
				if(emp.getCurrentContract()==null)
					continue;
				strContent+="<tr>";
				strContent+="<td>&nbsp;"+emp.getFullName()+"</td>";
				strContent+="<td>&nbsp;"+Position.getPostionTitleString(emp.getCurrentContract().getPosition().getTitle())+"</td>";
				strContent+="<td>&nbsp;"+emp.getCurrentContract().getPosition().getSalary()+"</td>";
				strContent+="<td>&nbsp;"+emp.getCurrentContract().getPosition().getOtherSalary()+"</td>";
				
				CTimeKeepingBook book=emp.getCurrentContract().getTimeKeeping();
				CTimeKeepingSheet sheet=book.get(m_nMonthSelected, m_nYearSelected);
				int nWorkday=0;
				if(sheet!=null)
					nWorkday=sheet.getWorkingDay();
				strContent+="<td>&nbsp;"+nWorkday+"</td>";
				int nSalary=0;
				nSalary=(emp.getCurrentContract().getPosition().getSalary()/30)*nWorkday+emp.getCurrentContract().getPosition().getOtherSalary();
				if(nWorkday==0)
					nSalary=0;
				strContent+="<td>&nbsp;"+nSalary+"</td>";
				strContent+="</tr>";
				nSumSalary=nSumSalary+nSalary;
			}
		}
		strContent+="<tr>";
		strContent+="<td colspan=\"5\" align=\"center\" style=\"color:red;font-weight:bold\">&nbsp; Sum Salary</td>";
		strContent+="<td>&nbsp;"+nSumSalary+"</td>";
		strContent+="</tr>";
		strContent+="</table>";
		strContent+="<p style=\"font-weight:bold;text-align:center\">Designed by <font color=\"blue\">Tu Thi Xuan Hien</font></p>";
		strContent+="</div>";
		strContent+="</body>";
		strContent+="</html>";
		return strContent;
	}
	public String createWorkSumaryReport()
	{
		String strContent="<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">";
		strContent+="<html xmlns=\"http://www.w3.org/1999/xhtml\">";
		strContent+="<head>";
		strContent+="<meta http-equiv=\"Content-Type\" content=\"text/html\"; charset=\"UTF-8\" />";
		strContent+="<title>Workday summary</title>";
		strContent+="</head>";
		
		strContent+="<body style=\"margin: 0;	padding: 0;\">";
		strContent+="<div class=\"wrapper\">";
		strContent+="<div style=\"background:#FF9933\"></div>";
		strContent+="<h1 align=\"center\"  style=\"color: #779A00;font-size: 36px;font-weight: bold;\">Workday summary </h1><center>";
		strContent+="<div style=\"background:#FC9D36;border:1px\"></div>";
		strContent+="<form id=\"form1\" name=\"form1\" method=\"post\" action=\"\">";
		strContent+="<table>";
		strContent+="<tr>";
		strContent+="<td align=\"right\">";
		strContent+="<font style=\"font-weight:bold;\">Full Name:</font>";
		strContent+="</td>";
		strContent+="<td>";
		strContent+="<font color=\"blue\">Tu Thi Xuan Hien</font>";
		strContent+="</td>";
		strContent+="<td align=\"right\">";
		strContent+="<font style=\"font-weight:bold;\">Position:</font>";
		strContent+="</td>";
		strContent+="<td>";
		strContent+="<font style=\"font-weight:bold;\">Director</font>";
		strContent+="</td>";
		strContent+="</tr>";
		strContent+="<tr>";
		strContent+="<td align=\"right\">";
		strContent+="<font style=\"font-weight:bold;\">Month:</font>";
		strContent+="</td>";
		strContent+="<td>";
		strContent+="06";
		strContent+="</td>";
		strContent+="<td align=\"right\">";
		strContent+="<font style=\"font-weight:bold;\">Year:</font>";
		strContent+="</td>";
		strContent+="<td>";
		strContent+="2011";
		strContent+="</td>";
		strContent+="</tr>";
		strContent+="</table>";
		
		strContent+="</form></center>";
		strContent+="<table width=\"100%\" border=\"1\" cellpadding=\"0\" cellspacing=\"0\">";
		strContent+="<tr>";
		strContent+="<th width=\"18%\">Date</th>";
		strContent+="<th width=\"19%\">Shift</th>";
		strContent+="<th width=\"17%\">Time</th>";
		strContent+="<th width=\"22%\">Absent valid</th>";
		strContent+="<th width=\"24%\">Absent invalid</th>";
		strContent+="</tr>";
		strContent+="<tr>";
		strContent+="<td>&nbsp;</td>";
		strContent+="<td>&nbsp;</td>";
		strContent+="<td>&nbsp;</td>";
		strContent+="<td>&nbsp;</td>";
		strContent+="<td>&nbsp;</td>";
		strContent+="</tr>";
		strContent+="<tr>";
		strContent+="<td>&nbsp;</td>";
		strContent+="<td>&nbsp;</td>";
		strContent+="<td>&nbsp;</td>";
		strContent+="<td>&nbsp;</td>";
		strContent+="<td>&nbsp;</td>";
		strContent+="</tr>";
		strContent+="</table>";
		strContent+="<p style=\"font-weight:bold;text-align:center\">Designed by <font color=\"blue\">Tu Thi Xuan Hien</font></p>";
		strContent+="</div>";
		strContent+="</body>";
		strContent+="</html>";
		return strContent;
	}
	public void addEventforAllControl()
	{
		m_printPreview.getButtonPrint().addActionListener(new CMyButtonEvent());
		m_printPreview.getButtonClose().addActionListener(new CMyButtonEvent());
	}
	public void setContent(String strContent)
	{
		m_printPreview.getPrintAbleEditor().setText(strContent);
	}
	public void setJobName(String strJobName)
	{
		m_strJobName=strJobName;
	}
	private void doPrint()
	{
		PrinterJob printer=PrinterJob.getPrinterJob();
		printer.setPrintable(m_printPreview.getPrintAbleEditor());
		try
		{
			printer.setJobName(m_strJobName);
			if(printer.printDialog())
			{
				printer.print();
			}
		}
		catch(PrinterException ex)
		{
			System.out.println(ex.getMessage());
		}
	}
	public void doShow()
	{
		Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
		m_printPreview.setSize(screenSize.width/2, screenSize.height/2);
		m_printPreview.setLocationRelativeTo(null);
		m_printPreview.setVisible(true);
		addEventforAllControl();
	}
	private class CMyButtonEvent implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Object o=e.getSource();
			if(o.equals(m_printPreview.getButtonPrint()))
			{
				doPrint();
			}
			else if(o.equals(m_printPreview.getButtonClose()))
			{
				m_printPreview.dispose();
			}
		}
		
	}
}

