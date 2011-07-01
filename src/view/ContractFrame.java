package view;
/*
 * @author Tu Thi Xuan Hien
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;





import model.CListEmployee;
import model.Contract;
import model.Duration;
import model.Employee;
import model.Position;
import model.PositionList;
import model.PositionTitle;
import model.ProcessFile;

public class ContractFrame extends JFrame {
	private JComboBox cboTitle,cboDuration;
	private JButton btnAdd,btnSave,btnExit;
	private JTextField txtPostionTitle,txtSalary,txtOtherSalary, txtExpireDateofContract ;
	private JTextField txtStartDateContractDay,txtStartDateContractMonth,txtStartDateContractYear;
	private PositionList m_PostionList;
	private PositionTitle m_PositionTitle;
	private Position m_CurrentPostion=null;
	private Employee m_currentEmployee=null;
	private  CListEmployee m_listEmployee=null;
	public boolean m_bIsSave=false;
	private Contract m_contract=null;
	private String m_strCaption="";
	public ContractFrame(String strTitle){//constructor
		super(strTitle);
		createUI();
		m_PostionList=new PositionList();
		//loadDataFromFile();		
	}
	public ContractFrame(String strTitle,String strCaption,Employee employee){//constructor
		setTitle(strTitle);
		m_strCaption=strCaption;
		m_currentEmployee=employee;
		createUI();
		m_PostionList=new PositionList();
		
		//loadDataFromFile();
			
	}
	public ContractFrame(String strTitle,String strCaption,Employee employee, CListEmployee listEmployee){//constructor
		super(strTitle);
		m_strCaption=strCaption;
		m_currentEmployee=employee;
		createUI();
		m_PostionList=new PositionList();
		m_listEmployee=listEmployee;
		//loadDataFromFile();
			
	}
	/*private void loadDataFromFile() {
		// TODO Auto-generated method stub
		
	}*/

	private void createUI() {
		JPanel pnGeneral=new JPanel();
		JPanel pnGeneralBorder=new JPanel();
		Container con=getContentPane();
		con.add(pnGeneralBorder);
		pnGeneralBorder.add(pnGeneral);
		pnGeneral.setLayout(new BorderLayout());
		
		//create title: Postion Information
		JPanel pnTitle=new JPanel();
		JLabel lblTitle=new JLabel(m_strCaption);
		JLabel lblEmployeeName=new JLabel(m_currentEmployee.getFullName());
		
		lblEmployeeName.setForeground(Color.RED);
		lblTitle.setForeground(Color.BLUE);
		Font font=new Font("Arial", Font.BOLD, 20);
		lblTitle.setFont(font);
		lblEmployeeName.setFont(font);
		pnTitle.add(lblTitle);
		pnTitle.add(lblEmployeeName);
		pnGeneral.add(pnTitle,BorderLayout.NORTH);
		
		JPanel pnInformation=new JPanel();
		JPanel pnInputInformation=new JPanel();
		
		pnInformation.setLayout(new BoxLayout(pnInformation, BoxLayout.Y_AXIS));
		pnInputInformation.setLayout(new BoxLayout(pnInputInformation, BoxLayout.Y_AXIS));
		pnInformation.add(pnInputInformation);
		
		//create Postion Title - use Combobox 
		JPanel pnPositionTitle=new JPanel();
		JLabel lblPostionTitle=new JLabel("Postion Title:", JLabel.RIGHT);
		cboTitle=new JComboBox();
		
		//Call method add PostionTitle in Combobox
		addPostionTitleForCombobox();
		
		pnPositionTitle.add(lblPostionTitle);
		pnPositionTitle.add(cboTitle); //display combobox on form
		pnGeneral.add(pnInformation,BorderLayout.CENTER);
		pnInputInformation.add(pnPositionTitle);
		
		
		JPanel pnSalary=new JPanel();
		JLabel lblSalary=new JLabel("Salary:", JLabel.RIGHT);
		txtSalary=new JTextField(15);
		pnSalary.add(lblSalary);
		pnSalary.add(txtSalary);		
		pnInputInformation.add(pnSalary);
		
		
		JPanel pnOtherSalary=new JPanel();
		JLabel lblOtherSalary=new JLabel("Other Salary:", JLabel.RIGHT);
		txtOtherSalary=new JTextField(15);
		pnOtherSalary.add(lblOtherSalary);
		pnOtherSalary.add(txtOtherSalary);		
		pnInputInformation.add(pnOtherSalary);
		
		
		JPanel pnContractDate=new JPanel();
		JLabel lblContractDate=new JLabel("Start Date Contract:", JLabel.RIGHT);
		pnContractDate.add(lblContractDate);
		
		txtStartDateContractDay=new JTextField(2);
		pnContractDate.add(txtStartDateContractDay);
		txtStartDateContractMonth=new JTextField(2);
		pnContractDate.add(txtStartDateContractMonth);
		txtStartDateContractYear=new JTextField(3);
		pnContractDate.add(txtStartDateContractYear);
		JLabel lblNoteDate=new JLabel("(dd/mm/yyyy)");
		Font fontNote=new Font("arial", Font.PLAIN, 9);
		lblNoteDate.setForeground(Color.RED);
		lblNoteDate.setFont(fontNote);
		pnContractDate.add(lblNoteDate);
		
		pnInputInformation.add(pnContractDate);
		
		JPanel pnCurrencyofContract=new JPanel();
		JLabel lblCurrencyofContract=new JLabel("Expire Date of Contract:");
		txtExpireDateofContract=new JTextField(15);
		pnCurrencyofContract.add(lblCurrencyofContract);
		addDurationForCombobox();
		pnCurrencyofContract.add(cboDuration);
		pnInputInformation.add(pnCurrencyofContract);
		
		
		//set JLabels are same size
		lblContractDate.setPreferredSize(lblCurrencyofContract.getPreferredSize());
		lblPostionTitle.setPreferredSize(lblCurrencyofContract.getPreferredSize());
		lblSalary.setPreferredSize(lblCurrencyofContract.getPreferredSize());
		lblOtherSalary.setPreferredSize(lblCurrencyofContract.getPreferredSize());
		lblPostionTitle.setPreferredSize(lblCurrencyofContract.getPreferredSize());
		
		
		//create Border for each JPanel
		TitledBorder border=new TitledBorder(BorderFactory.createLineBorder(Color.RED), "Detail Information");
		
		pnInputInformation.setBorder(border);
		
		JPanel pnButton=new JPanel();
		btnAdd=new JButton("Clear");
		
		//create Icon for Button
		ImageIcon iconAdd=new ImageIcon("images/add.png");
		btnAdd.setIcon(iconAdd);
		
		btnSave=new JButton("Save");
		ImageIcon iconSave=new ImageIcon("images/Save.png");
		btnSave.setIcon(iconSave);
	
	
		btnExit=new JButton("Close");
		ImageIcon iconExit=new ImageIcon("images/close.png");
		btnExit.setIcon(iconExit);
		pnButton.add(btnSave);
		pnButton.add(btnAdd);
		
		pnButton.add(btnExit);
		
		TitledBorder borderButtons=new TitledBorder(BorderFactory.createLineBorder(Color.RED), "Choose Action:");
		pnButton.setBorder(borderButtons);			
		pnInformation.add(pnButton);
				
		//assign Event into BUtton
		btnAdd.addActionListener(new CMyProcessButtonEvent());
		cboTitle.addActionListener(new CMyProcessButtonEvent());
		btnSave.addActionListener(new CMyProcessButtonEvent());
		btnExit.addActionListener(new CMyProcessButtonEvent());
		
		btnAdd.setMnemonic('C');
		btnSave.setMnemonic('S');
		btnExit.setMnemonic('E');
		
		
		//LockTheTextBox(true);
		btnAdd.setEnabled(true);
		btnExit.setEnabled(true);
		
		if(m_strCaption.indexOf("Edit")!=-1)
		{
			updateInformationForEdit();
		}
		
	}
	private void updateInformationForEdit()
	{
		if(m_currentEmployee==null)
			return;
		Contract con= m_currentEmployee.getCurrentContract();
		if(con==null)
			return;
		txtSalary.setText(con.getPosition().getSalary()+"");
		txtOtherSalary.setText(con.getPosition().getOtherSalary()+"");
		cboDuration.setSelectedIndex(con.getTime().ordinal());
		cboTitle.setSelectedIndex(con.getPosition().getTitle().ordinal());
		
		txtStartDateContractDay.setText(con.getStartDate().getDay()+"");
		txtStartDateContractMonth.setText(con.getStartDate().getMonth()+"");
		if(con.getStartDate().getYear()-1900<0)
			txtStartDateContractYear.setText(con.getStartDate().getYear()+1900+"");
		else
			txtStartDateContractYear.setText(con.getStartDate().getYear()+"");

	}
	private void addPostionTitleForCombobox() {
		if(cboTitle==null)
			cboTitle=new JComboBox();
		cboTitle.addItem("Accountant");
		cboTitle.addItem("Head Accountant");
		cboTitle.addItem("Cashier");
		cboTitle.addItem("Director");
		cboTitle.addItem("Chef");
		cboTitle.addItem("Executive Chef");
		cboTitle.addItem("Busboy");
		cboTitle.addItem("Dishwasher");
		cboTitle.addItem("Runner");
		cboTitle.addItem("Server");
		cboTitle.addItem("Head Server");
		
	}
	private void addDurationForCombobox()
	{
		if(cboDuration==null)
			cboDuration=new JComboBox();
		cboDuration.addItem("Two Months");
		cboDuration.addItem("One Year");
		cboDuration.addItem("Three Years");
		cboDuration.addItem("No Limit");
	}
	private String getDurationString(Duration aDuration)
	{
		String strResult="";
		switch(aDuration)
		{
		case TWO_MONTHS:
			strResult="Two Months";
			break;
		case ONE_YEAR:
			strResult="One Year";
			break;
		case THREE_YEARS:
			strResult="Three Years";
			break;
		case NO_LIMIT:
			strResult="No Limit";
			break;
		}
		return strResult;
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
		
		int nSalary=Integer.parseInt(txtSalary.getText());					
		int nOtherSalary=Integer.parseInt(txtOtherSalary.getText());
		int nIndex=cboTitle.getSelectedIndex();
		m_PositionTitle=getPostionTitle(nIndex);
		
		
		Position pos=new Position(m_PositionTitle, nSalary, nOtherSalary);
		
		m_contract.setPosition(pos);
		int day=Integer.parseInt(txtStartDateContractDay.getText());
		int month=Integer.parseInt(txtStartDateContractMonth.getText());
		int year=Integer.parseInt(txtStartDateContractYear .getText());
		m_contract.setStartDate(new Date(year,month,day ));
		m_contract.setTime(getDuration(cboDuration.getSelectedIndex()));
		
		return m_contract;
		
	}
	private void doSave() {
		if(btnAdd.isEnabled())
		{
			m_bIsSave=true;
			if(m_strCaption.indexOf("Edit")!=-1)//update
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
			dispose();
			m_bIsSave=true;

		}
		
	}
	private PositionTitle getPostionTitle(int nIndex)
	{
		PositionTitle posTitle=PositionTitle.ACCOUNTANT;
		switch(nIndex)
		{
		case 0:
			posTitle=PositionTitle.ACCOUNTANT;
			break;
		case 1:
			posTitle=PositionTitle.HEAD_ACCOUNTANT;
			break;
		case 2:
			posTitle=PositionTitle.CASHIER;
			break;
		case 3:
			posTitle=PositionTitle.DIRECTOR;
			break;
		case 4:
			posTitle=PositionTitle.CHEF;
			break;
		case 5:
			posTitle=PositionTitle.EXECUTIVE_CHEF;
			break;
		case 6:
			posTitle=PositionTitle.BUSBOY ;
			break;
		case 7:
			posTitle=PositionTitle.DISHWASHER;
			break;
		case 8:
			posTitle=PositionTitle.RUNNER;
			break;
		case 9:
			posTitle=PositionTitle.SERVER;
			break;
		case 10:
			posTitle=PositionTitle.HEAD_SERVER;
			break;
		}
		return posTitle;
	}
	

	private void doGetSalary() {
		int nIndex=cboTitle.getSelectedIndex();
		
		int nSalary=Position.ACCOUNTANT_SALARY;
		switch(nIndex)
		{
		case 0:
			nSalary=Position.ACCOUNTANT_SALARY;
			break;
		case 1:
			nSalary=Position.HEAD_ACCOUNTANT_SALARY;
			break;
		case 2:
			nSalary=Position.CASHIER_SALARY;
			break;
		case 3:
			nSalary=Position.DIRECTOR_SALARY;
			break;
		case 4:
			nSalary=Position.CHEF_SALARY;
			break;
		case 5:
			nSalary=Position.EXECUTIVE_CHEF_SALARY;
			break;
		case 6:
			nSalary=Position.BUSBOY_SALARY ;
			break;
		case 7:
			nSalary=Position.RUNNER_SALARY;
			break;
		case 8:
			nSalary=Position.SERVER_SALARY;
			break;
		case 9:
			nSalary=Position.HEAD_SERVER_SALARY;
			break;
		}
		
		txtSalary.setText(nSalary+"");
		
	}
	
	private void doClear() 
	{
		txtSalary.setText("");
		txtExpireDateofContract.setText("");
		txtStartDateContractDay.setText("");
		txtStartDateContractMonth.setText("");
		txtStartDateContractYear.setText("");
		txtSalary.requestFocus();
	}
	private class CMyProcessButtonEvent implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			Object myObj=e.getSource();
			if(myObj.equals(btnAdd))
			{
				doClear();	
			}
			
			else if(myObj.equals(cboTitle))
			{
				doGetSalary();
			}
			else if(myObj.equals(btnSave))
			{
				doSave();
			}
			else if(myObj.equals(btnExit))
			{
				doExit();
			}
		}

		private void doExit() {
			// TODO Auto-generated method stub
			dispose();
		}

		
		
	}
	
	public static void main(String[] args) {
		
		new ContractFrame("ContractFrame");
	}

}
