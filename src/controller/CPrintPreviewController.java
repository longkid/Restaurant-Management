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
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import model.CTimeKeepingBook;
import model.CTimeKeepingSheet;
import model.Contract;
import model.Employee;
import model.PositionTitle;
import view.CPrintPreview;

public class CPrintPreviewController {
	private CPrintPreview m_printPreview;
	private List<Employee> m_listEmployee;
	private int m_nMonthSelected;
	private int m_nYearSelected;

	public CPrintPreviewController() {
		m_printPreview = new CPrintPreview();
		m_printPreview.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				m_printPreview.dispose();
			}
		});
	}

	public void setTittle(String strTitle) {
		m_printPreview.setTitle(strTitle);
	}

	public void setListEmployee(List<Employee> listEmployee) {

		m_listEmployee = listEmployee;
	}

	public void setMonth(int nMonthSelected) {
		m_nMonthSelected = nMonthSelected;
	}

	public void setYear(int nYearSelected) {
		m_nYearSelected = nYearSelected;
	}

	public String createPayRollReport() {
		// String strContent="";
		String strContent = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">";
		strContent += "<html xmlns=\"http://www.w3.org/1999/xhtml\">";
		strContent += "<head>";
		strContent += "<meta http-equiv=\"Content-Type\" content=\"text/html\"; charset=\"UTF-8\" />";
		strContent += "<title>Payroll</title>";
		strContent += "</head>";

		strContent += "<body style=\"margin: 0;	padding: 0;\">";
		strContent += "<div class=\"wrapper\">";
		strContent += "<div style=\"background:#FF9933;border:1px\"></div>";
		strContent += "<h1 align=\"center\"  style=\"color: #779A00;font-size: 36px;font-weight: bold;\">Payroll </h1><center>";
		strContent += "<div style=\"background:#FC9D36\"></div>";
		strContent += "<form id=\"form1\" name=\"form1\" method=\"post\" action=\"\">";
		strContent += "Month: ";
		strContent += "<font color=\"red\" style=\"font-weight:bold;\">"
				+ m_nMonthSelected + "</font>";
		strContent += " Year: ";
		strContent += "<font color=\"red\"  style=\"font-weight:bold;\">"
				+ m_nYearSelected + "</font>";
		strContent += "</form></center>";
		strContent += "<table width=\"100%\" border=\"1\" cellpadding=\"0\" cellspacing=\"0\">";
		strContent += "<tr>";
		strContent += "<th width=\"18%\">Full Name</th>";
		strContent += "<th width=\"19%\">Position</th>";
		strContent += "<th width=\"17%\">Salary (USD)</th>";
		strContent += "<th width=\"17%\">Other Salary (USD)</th>";
		strContent += "<th width=\"22%\">Working-day Number</th>";
		strContent += "<th width=\"24%\">Final Salary (USD)</th>";
		strContent += "</tr>";
		int nSumSalary = 0;
		if (m_listEmployee != null) {
			for (int i = 0; i < m_listEmployee.size(); i++) {
				Employee emp = m_listEmployee.get(i);
				Contract suitableContract = emp.searchCorrespondingContract(
						m_nYearSelected, m_nMonthSelected);
				if (suitableContract == null)
					continue; // skip this employee

				int salary = suitableContract.getPosition().getSalary();
				int otherSalary = suitableContract.getPosition()
						.getOtherSalary();

				strContent += "<tr>";
				strContent += "<td>&nbsp;" + emp.getFullName() + "</td>";
				strContent += "<td>&nbsp;"
						+ PositionTitle.getTitleString(suitableContract
								.getPosition().getTitle()) + "</td>";
				strContent += "<td>&nbsp;" + salary + "</td>";
				strContent += "<td>&nbsp;" + otherSalary + "</td>";

				CTimeKeepingBook book = suitableContract.getTimeKeeping();
				CTimeKeepingSheet sheet = book.get(m_nMonthSelected,
						m_nYearSelected);
				int numberOfWorkingDays = 0;
				int numberOfDaysInMonth = CTimeKeepingBookController.getNumberOfDaysInMonth(
						m_nMonthSelected, m_nYearSelected);
				if (sheet != null)
					numberOfWorkingDays = sheet.getWorkingDay();
				strContent += "<td>&nbsp;" + numberOfWorkingDays + "</td>";
				int finalSalary = (numberOfWorkingDays == 0) ? 0
						: calculateSalary(salary, otherSalary, numberOfWorkingDays,
						numberOfDaysInMonth);
				strContent += "<td>&nbsp;" + finalSalary + "</td>";
				strContent += "</tr>";
				nSumSalary += finalSalary;
			}
		}
		strContent += "<tr>";
		strContent += "<td colspan=\"5\" align=\"center\" style=\"color:red;font-weight:bold\">&nbsp; Total Salary</td>";
		strContent += "<td>&nbsp;" + nSumSalary + "</td>";
		strContent += "</tr>";
		strContent += "</table>";
		strContent += "<p style=\"font-weight:bold;text-align:center\">Designed by <font color=\"blue\">Tu Thi Xuan Hien</font></p>";
		strContent += "</div>";
		strContent += "</body>";
		strContent += "</html>";
		return strContent;
	}

	public int calculateSalary(int salary, int otherSalary,
			int numberOfWorkingDays, int numberOfDaysInMonth) {
		return Math.round(((float) salary / numberOfDaysInMonth)
				* numberOfWorkingDays + otherSalary);
	}

	public void addEventforAllControl() {
		m_printPreview.getButtonClose().addActionListener(new CMyButtonEvent());
	}

	public void setContent(String strContent) {
		m_printPreview.getPrintAbleEditor().setText(strContent);
	}

	public void doShow() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		m_printPreview.setSize(screenSize.width / 2, screenSize.height / 2);
		m_printPreview.setLocationRelativeTo(null);
		m_printPreview.setVisible(true);
		addEventforAllControl();
	}

	private class CMyButtonEvent implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Object o = e.getSource();
			if (o.equals(m_printPreview.getButtonClose())) {
				m_printPreview.dispose();
			}
		}

	}
}
