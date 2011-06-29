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





import model.Contract;
import model.Employee;
import model.Position;
import model.PositionList;
import model.PositionTitle;
import model.ProcessFile;

public class ContractFrame extends JFrame {
	private JComboBox cboTitle;
	private JButton btnAdd,btnSave,btnExit;
	private JTextField txtPostionTitle,txtSalary,txtStartDateContract, txtExpireDateofContract ;	
	private PositionList m_PostionList;
	private PositionTitle m_PositionTitle;
	private Position m_CurrentPostion=null;
	private Employee m_currentEmployee=null;
	private Contract m_contract=null;
	
	public ContractFrame(String strTitle){//constructor
		super(strTitle);
		createUI();
		m_PostionList=new PositionList();
		//loadDataFromFile();		
	}
	public ContractFrame(String strTitle,Employee employee){//constructor
		super(strTitle);
		m_currentEmployee=employee;
		createUI();
		m_PostionList=new PositionList();
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
		JLabel lblTitle=new JLabel("New Contract:");
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
		
		
		JPanel pnContractDate=new JPanel();
		JLabel lblContractDate=new JLabel("Start Date Contract:", JLabel.RIGHT);
		txtStartDateContract=new JTextField(15);
		pnContractDate.add(lblContractDate);
		pnContractDate.add(txtStartDateContract);
		pnInputInformation.add(pnContractDate);
		
		JPanel pnCurrencyofContract=new JPanel();
		JLabel lblCurrencyofContract=new JLabel("Expire Date of Contract:");
		txtExpireDateofContract=new JTextField(15);
		pnCurrencyofContract.add(lblCurrencyofContract);
		pnCurrencyofContract.add(txtExpireDateofContract);
		pnInputInformation.add(pnCurrencyofContract);
		
		
		//set JLabels are same size
		lblContractDate.setPreferredSize(lblCurrencyofContract.getPreferredSize());
		lblPostionTitle.setPreferredSize(lblCurrencyofContract.getPreferredSize());
		lblSalary.setPreferredSize(lblCurrencyofContract.getPreferredSize());
		lblPostionTitle.setPreferredSize(lblCurrencyofContract.getPreferredSize());
		
		//create Border for each JPanel
		TitledBorder border=new TitledBorder(BorderFactory.createLineBorder(Color.RED), "Detail Information");
		
		pnInputInformation.setBorder(border);
		
		JPanel pnButton=new JPanel();
		btnAdd=new JButton("Add");
		
		//create Icon for Button
		ImageIcon iconAdd=new ImageIcon("images/add.png");
		btnAdd.setIcon(iconAdd);
		
		btnSave=new JButton("Save");
		ImageIcon iconSave=new ImageIcon("images/Save.png");
		btnSave.setIcon(iconSave);
	
	
		btnExit=new JButton("Exit");
		ImageIcon iconExit=new ImageIcon("images/close.png");
		btnExit.setIcon(iconExit);
		
		pnButton.add(btnAdd);
		pnButton.add(btnSave);
		pnButton.add(btnExit);
		
		TitledBorder borderButtons=new TitledBorder(BorderFactory.createLineBorder(Color.RED), "Choose Action:");
		pnButton.setBorder(borderButtons);			
		pnInformation.add(pnButton);
				
		//assign Event into BUtton
		btnAdd.addActionListener(new CMyProcessButtonEvent());
		cboTitle.addActionListener(new CMyProcessButtonEvent());
		btnSave.addActionListener(new CMyProcessButtonEvent());
		btnExit.addActionListener(new CMyProcessButtonEvent());
		
		btnAdd.setMnemonic('A');
		btnSave.setMnemonic('S');
		btnExit.setMnemonic('E');
		
		
		LockTheTextBox(true);
		btnAdd.setEnabled(true);
		btnExit.setEnabled(true);
		btnSave.setEnabled(false);
		
		
	}
	private void LockTheTextBox(boolean b)
	{
		txtSalary.setEditable(!b);
		txtStartDateContract.setEditable(!b);
		txtExpireDateofContract.setEditable(!b);
		cboTitle.setEnabled(!b);
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
	
	private class CMyProcessButtonEvent implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			Object myObj=e.getSource();
			if(myObj.equals(btnAdd))
			{
				doAdd();	
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

		private void doSave() {
			if(btnAdd.isEnabled())
			{
				//We save Contract here
				if(m_currentEmployee.getContracts()==null){					
					m_contract =new Contract();
					m_currentEmployee.addContract(m_contract);					
				}
				int nSalary=Integer.parseInt(txtSalary.getText());
				int nIndex=cboTitle.getSelectedIndex();
				m_PositionTitle=getPostionTitle(nIndex);
				
			//	m_contract.getStartDate())=;
				boolean bResult=ProcessFile.WriteData(m_PostionList, ProcessFile.FILENAME_POSITION);
				if(bResult)
				{
					JOptionPane.showMessageDialog(null, "Save success");
				}
				else
					JOptionPane.showMessageDialog(null, "Save Failed");
				
				btnAdd.setText("Add");
				btnSave.setEnabled(false);
				LockTheTextBox(true);
				//tblDetail.setEnabled(true);
			}
			else
			{
			
				int nSalary=Integer.parseInt( txtSalary.getText());
				boolean bResult=ProcessFile.WriteData(m_PostionList, ProcessFile.FILENAME_POSITION);
				
				if(!bResult)
				{
					JOptionPane.showMessageDialog(null, "Save success");
				}
				
				
				btnSave.setEnabled(false);
				btnAdd.setEnabled(true);
				LockTheTextBox(true);
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
		

		private void LockTheTextBox(boolean b)
		{
			txtSalary.setEditable(!b);
			txtStartDateContract.setEditable(!b);
			txtExpireDateofContract.setEditable(!b);
			cboTitle.setEnabled(!b);
		}

		private void doAdd() {
			if(btnAdd.getText().equalsIgnoreCase("Add"))
			{
				btnAdd.setText("Cancel");				
				btnSave.setEnabled(true);
				LockTheTextBox(false);
			//	tblDetail.setEnabled(false);
				txtSalary.setText("");
				txtStartDateContract.setText("");
			}
			else
			{
				btnAdd.setText("Add");				
				btnSave.setEnabled(false);				
				LockTheTextBox(true);
				//tblDetail.setEnabled(true);
				if(m_CurrentPostion!=null)
				{
					txtSalary.setText(m_CurrentPostion.getSalary()+"");
					//txtExpireDateofContract.setText(m_CurrentPostion.getOtherSalary()+"");
					cboTitle.setSelectedIndex(m_CurrentPostion.getTitle().ordinal());
				}
			}
			
		}
		
	}
	
	public static void main(String[] args) {
		
		new ContractFrame("ContractFrame");
	}

}
