package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;



import model.Employee;

public class CEditContractFrame extends JFrame{
	private JComboBox cboTitle;
	private Employee m_currentEmployee=null;
	private JButton btnAdd, btnSave,btnExit;
	private JTextField txtSalary, txtStartDateContract, txtExpireDateofContract; 
	public CEditContractFrame(String strTitle){
		super(strTitle);
		createUI();
	}
	private void createUI() {
		JPanel pnGeneral=new JPanel();
		JPanel pnGeneralBorder=new JPanel();
		Container con=getContentPane();
		con.add(pnGeneralBorder);
		pnGeneralBorder.add(pnGeneral);
		pnGeneral.setLayout(new BorderLayout());
		
		//create title: Postion Information
		JPanel pnTitle=new JPanel();
		JLabel lblTitle=new JLabel("Edit Contract of ");
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
		/*btnAdd.addActionListener(new CMyProcessButtonEvent());
		cboTitle.addActionListener(new CMyProcessButtonEvent());
		btnSave.addActionListener(new CMyProcessButtonEvent());
		btnExit.addActionListener(new CMyProcessButtonEvent());*/
		
		btnAdd.setMnemonic('A');
		btnSave.setMnemonic('S');
		btnExit.setMnemonic('E');
		
		
		//LockTheTextBox(true);
		btnAdd.setEnabled(true);
		btnExit.setEnabled(true);
		btnSave.setEnabled(false);
		
		
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
	public static void main(String [] args){
		new CEditContractFrame("");
	}
}
