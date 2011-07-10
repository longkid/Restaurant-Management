package view;


import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.print.PrinterJob;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import model.CListEmployee;
import model.CTimeKeepingBook;
import model.CTimeKeepingSheet;
import model.Contract;
import model.Employee;
import model.PositionTitle;


public class CPrintPreview   extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CPrintAbleEditorPanel editorPane;
	private JButton btnPrint,btnClose;
	private String m_strJobName="Test_Printting";
	private CListEmployee m_listEmployee;
	private Employee m_currentEmployee=null;
	private Contract m_currentContract=null;
	private int m_nMonthSelected;
	private int  m_nYearSelected;
	public void setEmployee(Employee employee)
	{
		m_currentEmployee=employee;
	}
	public void setListEmployee(CListEmployee listEmployee)
	{
		
		m_listEmployee=listEmployee;
	}
	public void setContract(Contract currentContract)
	{
		m_currentContract=currentContract;
	}
	public void setMonth(int nMonthSelected)
	{
		m_nMonthSelected=nMonthSelected;
	}
	public void setYear(int nYearSelected)
	{
		m_nYearSelected=nYearSelected;
	}
	public CPrintPreview(String strTitle)
	{
		super(strTitle);
		createEditorPane();
		
		addWindowListener(
				new WindowAdapter() 
				{
					@Override
					public void windowClosing(WindowEvent e) {
						dispose();
					}
				});
	}
	private void createEditorPane()
	{
		editorPane=new CPrintAbleEditorPanel();
		editorPane.setEditable(true);
		editorPane.setContentType("text/html");
		
		Container con=getContentPane();
		con.setLayout(new BorderLayout());
		JPanel pnTitle=new JPanel();
		pnTitle.setLayout(new BorderLayout());
		JPanel pnPrint=new JPanel();
		pnPrint.setLayout(new FlowLayout(FlowLayout.LEFT));
		btnPrint=new JButton("Print Report");
		pnPrint.add(btnPrint);
		pnTitle.add(pnPrint,BorderLayout.WEST);
		
		btnClose=new JButton("Close Report");
		JPanel pnClose=new JPanel();
		pnClose.setLayout(new FlowLayout(FlowLayout.RIGHT));
		pnClose.add(btnClose);
		pnTitle.add(pnClose,BorderLayout.EAST );
		
		con.add(pnTitle,BorderLayout.NORTH);
		con.add(new JScrollPane(editorPane),BorderLayout.CENTER);
		
		btnPrint.addActionListener(new CMyButtonEvent());
		btnClose.addActionListener(new CMyButtonEvent());
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
				strContent+="<td>&nbsp;"+getPostionTitleString(emp.getCurrentContract().getPosition().getTitle())+"</td>";
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
	private String getPostionTitleString(PositionTitle aPostionTitle)
	{
		String strResult="";
		switch(aPostionTitle)
		{
		case ACCOUNTANT:
			strResult="Accountant";
			break;
		case HEAD_ACCOUNTANT:
			strResult="Head Accountant";
			break;
		case CASHIER:
			strResult="Cashier";
			break;
		case DIRECTOR:
			strResult="Director";
			break;
		case CHEF:
			strResult="Chef";
			break;
		case EXECUTIVE_CHEF:
			strResult="Executive Chef";
			break;
		case BUSBOY:
			strResult="Busboy";
			break;
		case DISHWASHER:
			strResult="Dishwasher";
			break;
		case RUNNER:
			strResult="Runner";
			break;
		case SERVER:
			strResult="Server";
			break;
		case HEAD_SERVER:
			strResult="Head Server";
			break;
		}
		return strResult;
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
	public void setContent(String strContent)
	{
		editorPane.setText(strContent);
	}
	public void setJobName(String strJobName)
	{
		m_strJobName=strJobName;
	}
	private void doPrint()
	{
		PrinterJob printer=PrinterJob.getPrinterJob();
		
		printer.setPrintable(editorPane);
		try
		{
			printer.setJobName(m_strJobName);
			if(printer.printDialog())
			{
				printer.print();
			}
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(this, ex.getMessage());
		}
	}
	private class CMyButtonEvent implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Object o=e.getSource();
			if(o.equals(btnPrint))
			{
				doPrint();
			}
			else if(o.equals(btnClose))
			{
				dispose();
			}
		}
		
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		CPrintPreview myUI=new CPrintPreview("Test HTML Printing");
		Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
		myUI.setSize(screenSize.width/2, screenSize.height/2);
		myUI.setDefaultCloseOperation(EXIT_ON_CLOSE);
		myUI.setContent(myUI.createWorkSumaryReport());
		myUI.setLocationRelativeTo(null);
		myUI.setVisible(true);
	}
}
