package controller;
/*
 * @author Tu Thi Xuan Hien
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.JOptionPane;

import model.Contract;
import model.Duration;
import model.Employee;
import model.Position;
import model.PositionList;
import model.PositionTitle;
import model.ProcessFile;
import view.CContractFrame;

public class CContactController 
{
	private CContractFrame m_contractFrame=null;
	private PositionList m_PostionList;
	private PositionTitle m_PositionTitle;
	private Employee m_currentEmployee=null;
	private  List<Employee> m_listEmployee=null;
	private Contract m_contract=null;
	private boolean m_bIsSave=false;
	public CContactController (String strTitle,String strCaption,Employee employee,List<Employee> listEmployee)
	{
		m_contractFrame=new CContractFrame(strTitle,strCaption);
		m_contractFrame.setEmployeeName(employee.getFullName());
		addPostionTitleForCombobox();
		addDurationForCombobox();
		m_currentEmployee=employee;
		m_listEmployee=listEmployee;
	}
	public boolean isSave()
	{
		return m_bIsSave;
	}
	public void doShow()
	{
		m_contractFrame.setSize(550, 350);
		m_contractFrame.setLocationRelativeTo(null);
		addEventforAllControl();
		m_contractFrame.setModal(true);
		m_contractFrame.setVisible(true);
	}
	public void updateInformationForEdit()
	{
		if(m_currentEmployee==null)
			return;
		Contract con= m_currentEmployee.getCurrentContract();
		if(con==null)
			return;
		m_contractFrame.getTextFieldSalary().setText(con.getPosition().getSalary()+"");
		m_contractFrame.getTextFieldOtherSalary().setText(con.getPosition().getOtherSalary()+"");
		m_contractFrame.getComboBoxDuration().setSelectedIndex(con.getTime().ordinal());
		m_contractFrame.getComboBoxTitle().setSelectedIndex(con.getPosition().getTitle().ordinal());
		
		m_contractFrame.getTextFieldStartDateContractDay().setText(con.getStartDate().getDate()+"");
		m_contractFrame.getTextFieldStartDateContractMonth().setText(con.getStartDate().getMonth()+"");
		if(con.getStartDate().getYear()-1900<0)
			m_contractFrame.getTextFieldStartDateContractYear().setText(con.getStartDate().getYear()+1900+"");
		else
			m_contractFrame.getTextFieldStartDateContractYear().setText(con.getStartDate().getYear()+"");

	}
	private void addPostionTitleForCombobox() {
		m_PostionList=(PositionList)ProcessFile.ReadData(ProcessFile.FILENAME_POSITION);
		if(m_PostionList!=null)
		{
			for(int i=0;i<m_PostionList.size();i++)
			{
				m_contractFrame.getComboBoxTitle().addItem(Position.getPostionTitleString(m_PostionList.get(i).getTitle()));
			}
		}
		
	}
	private void addDurationForCombobox()
	{
		m_contractFrame.getComboBoxDuration().addItem("Two Months");
		m_contractFrame.getComboBoxDuration().addItem("One Year");
		m_contractFrame.getComboBoxDuration().addItem("Three Years");
		m_contractFrame.getComboBoxDuration().addItem("No Limit");
	}
	private Duration getDuration(int nIndex)
	{
		Duration aDuration=Duration.NO_LIMIT;
		switch(nIndex)
		{
		case 0:
			aDuration=Duration.TWO_MONTHS;
			break;
		case 1:
			aDuration=Duration.ONE_YEAR;
			break;
		case 2:
			aDuration=Duration.THREE_YEARS;
			break;
		case 3:
			aDuration=Duration.NO_LIMIT ;
			break;
		}
		return aDuration;
	}
	private Contract createContract()
	{
		m_contract =new Contract();		
		int nSalary=Integer.parseInt(m_contractFrame.getTextFieldSalary().getText());					
		int nOtherSalary=Integer.parseInt(m_contractFrame.getTextFieldOtherSalary().getText());
		int nIndex=m_contractFrame.getComboBoxTitle() .getSelectedIndex();
		
		m_PositionTitle=m_PostionList.get(nIndex).getTitle();		
		Position pos=new Position(m_PositionTitle, nSalary, nOtherSalary);
		
		m_contract.setPosition(pos);
		int day=Integer.parseInt(m_contractFrame.getTextFieldStartDateContractDay().getText());
		int month=Integer.parseInt(m_contractFrame.getTextFieldStartDateContractMonth().getText());
		int year=Integer.parseInt(m_contractFrame.getTextFieldStartDateContractYear().getText());
		Calendar cal=Calendar.getInstance();
		cal.set(year, month, day);
		m_contract.setStartDate(cal.getTime());
		m_contract.setTime(getDuration(m_contractFrame.getComboBoxDuration().getSelectedIndex()));
		
		return m_contract;
		
	}
	private void doSave() {
		if(m_contractFrame.getButtonAdd().isEnabled())
		{
			m_bIsSave=true;
			if(m_contractFrame.getCaption().indexOf("Edit")!=-1)//update
			{
				m_currentEmployee.setCurrentContract(createContract());
			}
			else
			{
				//We save Contract here
				
				if(m_currentEmployee.getCurrentContract()!=null)//move to HISTORY
				{
					ArrayList<Contract> listContract=(ArrayList<Contract>)m_currentEmployee.getContracts();
					if(listContract==null)
						listContract=new ArrayList<Contract>();
					
					listContract.add(m_currentEmployee.getCurrentContract());
					m_currentEmployee.setContracts(listContract);
				}
				//add new current contract
				m_currentEmployee.setCurrentContract(createContract());
			}
			boolean bResult=ProcessFile.WriteData(m_listEmployee, ProcessFile.FILENAME_EMPLOYEE);
			if(bResult)
			{
				JOptionPane.showMessageDialog(null, "Save success");
			}
			else
				JOptionPane.showMessageDialog(null, "Save Failed");		
			m_contractFrame.dispose();
			m_bIsSave=true;
		}
	}

	private void doGetSalary() {
		int nIndex=m_contractFrame.getComboBoxTitle().getSelectedIndex();
		int nSalary=Position.ACCOUNTANT_SALARY;
		nSalary=m_PostionList.get(nIndex).getSalary();
		m_contractFrame.getTextFieldSalary().setText(nSalary+"");		
	}
	public void addEventforAllControl()
	{
		//assign Event into BUtton
		m_contractFrame.getButtonAdd().addActionListener(new CMyProcessButtonEvent());
		m_contractFrame.getComboBoxTitle().addActionListener(new CMyProcessButtonEvent());
		m_contractFrame.getButtonSave().addActionListener(new CMyProcessButtonEvent());
		m_contractFrame.getButtonExit().addActionListener(new CMyProcessButtonEvent());
	}
	private void doClear() 
	{
		m_contractFrame.getTextFieldSalary().setText("");
		m_contractFrame.getTextFieldStartDateContractDay().setText("");
		m_contractFrame.getTextFieldStartDateContractMonth().setText("");
		m_contractFrame.getTextFieldStartDateContractYear().setText("");
		m_contractFrame.getTextFieldSalary().requestFocus();
	}
	private class CMyProcessButtonEvent implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			Object myObj=e.getSource();
			//if(myObj.equals(btnAdd))
			if(myObj.equals(m_contractFrame.getButtonAdd()))	
			{
				doClear();	
			}
			
			//else if(myObj.equals(cboTitle))
			else if(myObj.equals(m_contractFrame.getComboBoxTitle()))	
			{
				doGetSalary();
			}
			//else if(myObj.equals(btnSave))
			else if(myObj.equals(m_contractFrame.getButtonSave()))
			{
				doSave();
			}
			else if(myObj.equals(m_contractFrame.getButtonExit()))
			{
				doExit();
			}
		}

		private void doExit() {
			// TODO Auto-generated method stub
			m_contractFrame.dispose();
		}		
		
	}
}
