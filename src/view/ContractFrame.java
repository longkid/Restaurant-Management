package view;

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





import model.Employee;
import model.Position;
import model.PositionList;

public class ContractFrame extends JFrame {
	private JComboBox cboTitle;
	private JButton btnAdd,btnUpdate,btnDelete,btnSave,btnExit;
	private JTextField txtPostionTitle,txtSalary,txtContractDate, txtCurrencyofContract ;	
	private JTable tblDetail;
	private DefaultTableModel tblModel;
	private PositionList m_PostionList;
	private Position m_CurrentPostion=null;
	private Employee m_currentEmployee=null;
	public ContractFrame(String strTitle){
		super(strTitle);
		createUI();
		//setSize(650, 600);
		//setVisible(true);
		//setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		m_PostionList=new PositionList();
		//setLocationRelativeTo(null);
		loadDataFromFile();
		//loadDataOnTable();
		
	}
	public ContractFrame(String strTitle,Employee employee){
		super(strTitle);
		m_currentEmployee=employee;
		createUI();
		//setSize(650, 600);
		//setVisible(true);
		//setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		m_PostionList=new PositionList();
		//setLocationRelativeTo(null);
		loadDataFromFile();
		//JOptionPane.showMessageDialog(null, "You choosen: "+m_currentEmployee.getFullName());
		//loadDataOnTable();
		
	}

	private void loadDataFromFile() {
		// TODO Auto-generated method stub
		
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
		
		//create Postion Title - dung Combobox 
		JPanel pnPositionTitle=new JPanel();
		JLabel lblPostionTitle=new JLabel("Postion Title:", JLabel.RIGHT);
		cboTitle=new JComboBox();
		
		//Call method add PostionTitle in Combobox
		addPostionTitleForCombobox();
		
		//txtPostionTitle=new JTextField(15);
		pnPositionTitle.add(lblPostionTitle);
		pnPositionTitle.add(cboTitle); //display combobox on form
		pnGeneral.add(pnInformation,BorderLayout.CENTER);
		pnInputInformation.add(pnPositionTitle);
		
		
		JPanel pnSalary=new JPanel();
		JLabel lblSalary=new JLabel("Salary:", JLabel.RIGHT);
		txtSalary=new JTextField(15);
		pnSalary.add(lblSalary);
		pnSalary.add(txtSalary);
		//txtSalary.setEditable(false);
		pnInputInformation.add(pnSalary);
		
		
		JPanel pnContractDate=new JPanel();
		JLabel lblContractDate=new JLabel("Start Date Contract:", JLabel.RIGHT);
		txtContractDate=new JTextField(15);
		pnContractDate.add(lblContractDate);
		pnContractDate.add(txtContractDate);
		pnInputInformation.add(pnContractDate);
		
		JPanel pnCurrencyofContract=new JPanel();
		JLabel lblCurrencyofContract=new JLabel("Expire Date of Contract:");
		txtCurrencyofContract=new JTextField(15);
		pnCurrencyofContract.add(lblCurrencyofContract);
		pnCurrencyofContract.add(txtCurrencyofContract);
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
		
		btnUpdate=new JButton("Update");
		ImageIcon iconEdit=new ImageIcon("images/modify.png");
		btnUpdate.setIcon(iconEdit);
		btnDelete=new JButton("Delete");
		
		ImageIcon iconDelete=new ImageIcon("images/trash.png");
		btnDelete .setIcon(iconDelete);
		
		pnButton.add(btnAdd);
		pnButton.add(btnSave);
		pnButton.add(btnUpdate);
		pnButton.add(btnDelete);
		TitledBorder borderButtons=new TitledBorder(BorderFactory.createLineBorder(Color.RED), "Choose Action:");
		pnButton.setBorder(borderButtons);			
		pnInformation.add(pnButton);
		
		//create Table
		JPanel pnTable=new JPanel();
		pnTable.setLayout(new BorderLayout());
		//pnInformation.add(pnTable);
		//must be DefaultTableModel
		tblModel=new DefaultTableModel();
		
		//Add Column for TableModel
		tblModel.addColumn("Postion Title");
		tblModel.addColumn("Salary");
		tblModel.addColumn("Contract date");
		tblModel.addColumn("Current of Contract");
		//sau do dua vao Jtable
		tblDetail=new JTable(tblModel);
		
		//must be JScrollPane to see information in JTable
		JScrollPane sc=new JScrollPane(tblDetail);
		sc.setPreferredSize(new Dimension(530, 200));
		
		//diaplay JScrollPane on interface
		pnTable.add(sc,BorderLayout.CENTER);
		TitledBorder borderTable=new TitledBorder(BorderFactory.createLineBorder(Color.RED), "Postion List:");
		pnTable.setBorder(borderTable);
		btnExit=new JButton("Exit");
		JPanel pnSouth=new JPanel();
		pnSouth.setLayout(new FlowLayout(FlowLayout.RIGHT));
		ImageIcon iconExit=new ImageIcon("images/close.png");
		btnExit.setIcon(iconExit);
		pnSouth.add(btnExit);
		pnGeneral.add(pnSouth,BorderLayout.SOUTH);
		
		//assign Event into BUtton
		btnAdd.addActionListener(new CMyProcessButtonEvent());
		btnUpdate.addActionListener(new CMyProcessButtonEvent());
		btnDelete.addActionListener(new CMyProcessButtonEvent());
		cboTitle.addActionListener(new CMyProcessButtonEvent());
		btnSave.addActionListener(new CMyProcessButtonEvent());

		btnExit.addActionListener(new CMyProcessButtonEvent());
		//tblDetail.addMouseListener(new CMyProcessMouseEvent());
		
		btnAdd.setMnemonic('A');
		btnUpdate.setMnemonic('E');
		btnDelete.setMnemonic('D');
		btnSave.setMnemonic('S');
		
		
	//	LockTheTextBox(true);
		btnAdd.setEnabled(true);
		btnUpdate.setEnabled(false);
		btnDelete.setEnabled(false);
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
			else if(myObj.equals(btnUpdate))
			{
				doUpdate();
			}
			else if(myObj.equals(btnDelete))
			{
				doDelete();
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
			// TODO Auto-generated method stub
			
		}

		private void doGetSalary() {
			// TODO Auto-generated method stub
			
		}

		private void doDelete() {
			// TODO Auto-generated method stub
			
		}

		private void doUpdate() {
			// TODO Auto-generated method stub
			
		}
		private void LockTheTextBox(boolean b)
		{
			txtSalary.setEditable(!b);
			txtContractDate.setEditable(!b);
			txtCurrencyofContract.setEditable(!b);
			cboTitle.setEnabled(!b);
		}

		private void doAdd() {
			if(btnAdd.getText().equalsIgnoreCase("Add"))
			{
				btnAdd.setText("Cancel");
				btnUpdate.setEnabled(false);
				btnSave.setEnabled(true);
				btnDelete.setEnabled(false);
				LockTheTextBox(false);
				tblDetail.setEnabled(false);
				txtSalary.setText("");
				txtContractDate.setText("");
			}
			else
			{
				btnAdd.setText("Add");
				btnUpdate.setEnabled(false);
				btnSave.setEnabled(false);
				btnDelete.setEnabled(false);
				LockTheTextBox(true);
				tblDetail.setEnabled(true);
				if(m_CurrentPostion!=null)
				{
					txtSalary.setText(m_CurrentPostion.getSalary()+"");
					txtCurrencyofContract.setText(m_CurrentPostion.getOtherSalary()+"");
					cboTitle.setSelectedIndex(m_CurrentPostion.getTitle().ordinal());
				}
			}
			
		}
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ContractFrame("ContractFrame");
	}

}
