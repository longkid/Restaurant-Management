package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
import model.Position;

public class CEditContractFrame extends JFrame{
	private JComboBox cboTitle;
	private Employee m_currentEmployee=null;
	private JButton btnEdit, btnSave,btnExit;
	private Position m_CurrentPostion=null;
	private JTextField txtSalary, txtOtherSalary, txtStartDate, txtExpireDateofContract; 
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
		JLabel lblTitle=new JLabel("Edit Contract");
		//JLabel lblEmployeeName=new JLabel(m_currentEmployee.getFullName());
		//lblEmployeeName.setForeground(Color.RED);
		lblTitle.setForeground(Color.BLUE);
		Font font=new Font("Arial", Font.BOLD, 20);
		lblTitle.setFont(font);
		//lblEmployeeName.setFont(font);
		pnTitle.add(lblTitle);
		//pnTitle.add(lblEmployeeName);
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

		JPanel pnStartDate=new JPanel();
		JLabel lblStartDate=new JLabel("Start Date of Contract:");
		txtStartDate=new JTextField(15);
		pnStartDate.add(lblStartDate);
		pnStartDate.add(txtStartDate);
		pnInputInformation.add(pnStartDate);

		JPanel pnExpiretDate=new JPanel();
		JLabel lblExpiretDate=new JLabel("Expire Date of Contract:");
		txtExpireDateofContract=new JTextField(15);
		pnExpiretDate.add(lblExpiretDate);
		pnExpiretDate.add(txtExpireDateofContract);
		pnInputInformation.add(pnExpiretDate);

		//set JLabels are same size
		lblOtherSalary.setPreferredSize(lblExpiretDate.getPreferredSize());
		lblPostionTitle.setPreferredSize(lblExpiretDate.getPreferredSize());
		lblSalary.setPreferredSize(lblExpiretDate.getPreferredSize());
		lblExpiretDate.setPreferredSize(lblExpiretDate.getPreferredSize());
		lblStartDate.setPreferredSize(lblExpiretDate.getPreferredSize());
		lblPostionTitle.setPreferredSize(lblExpiretDate.getPreferredSize());

		//create Border for each JPanel
		TitledBorder border=new TitledBorder(BorderFactory.createLineBorder(Color.RED), "Detail Information");

		pnInputInformation.setBorder(border);

		JPanel pnButton=new JPanel();
		btnEdit=new JButton("Edit");

		//create Icon for Button
		ImageIcon iconAdd=new ImageIcon("images/add.png");
		btnEdit.setIcon(iconAdd);

		btnSave=new JButton("Save");
		ImageIcon iconSave=new ImageIcon("images/Save.png");
		btnSave.setIcon(iconSave);


		btnExit=new JButton("Exit");
		ImageIcon iconExit=new ImageIcon("images/close.png");
		btnExit.setIcon(iconExit);

		pnButton.add(btnEdit);
		pnButton.add(btnSave);
		pnButton.add(btnExit);

		TitledBorder borderButtons=new TitledBorder(BorderFactory.createLineBorder(Color.RED), "Choose Action:");
		pnButton.setBorder(borderButtons);			
		pnInformation.add(pnButton);

		//assign Event into BUtton
		btnEdit.addActionListener(new CMyProcessButtonEvent());
		cboTitle.addActionListener(new CMyProcessButtonEvent());
		btnSave.addActionListener(new CMyProcessButtonEvent());
		btnExit.addActionListener(new CMyProcessButtonEvent());

		/*btnAdd.setMnemonic('A');
		btnSave.setMnemonic('S');
		btnExit.setMnemonic('E');
		 */

		LockTheTextBox(true);
		btnEdit.setEnabled(true);
		btnExit.setEnabled(true);
		btnSave.setEnabled(false);


	}

	private void LockTheTextBox(boolean b) {
		txtSalary.setEditable(!b);
		txtOtherSalary.setEditable(!b);
		txtStartDate.setEditable(!b);
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
			if(myObj.equals(btnEdit))
			{
				doEdit();	
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
			dispose();

		}

		private void doSave() {
			// TODO Auto-generated method stub

		}

		private void doGetSalary() {
			// TODO Auto-generated method stub

		}

		private void doEdit() {
			if(btnEdit.getText().equalsIgnoreCase("Edit"))
			{
				btnEdit.setText("Cancel");				
				btnSave.setEnabled(true);
				LockTheTextBox(false);
				//	tblDetail.setEnabled(false);
				txtSalary.setText("");
				txtOtherSalary.setText("");
				txtStartDate.setText("");
				txtExpireDateofContract.setText("");
			}
			else
			{
				btnEdit.setText("Edit");				
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

	public static void main(String [] args){
		new CEditContractFrame("");
	}
}
