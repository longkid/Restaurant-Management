package view;
/*
 * @author Tu Thi Xuan Hien
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class CContractFrame extends JDialog {
	private static final long serialVersionUID = 1L;
	private JComboBox cboTitle,cboDuration;
	private JButton btnAdd,btnSave,btnExit;
	private JTextField txtSalary,txtOtherSalary ;
	private JTextField txtStartDateContractDay,txtStartDateContractMonth,txtStartDateContractYear;
	JLabel lblEmployeeName;
	private String m_strCaption="";
	public CContractFrame(String strTitle,String strCaption){//constructor
		setTitle(strTitle);
		m_strCaption=strCaption;
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
		JLabel lblTitle=new JLabel(m_strCaption);
		
		lblEmployeeName=new JLabel();
		
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
		pnCurrencyofContract.add(lblCurrencyofContract);
		cboDuration=new JComboBox();
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
				
		btnAdd.setMnemonic('C');
		btnSave.setMnemonic('S');
		btnExit.setMnemonic('E');
		
		
		//LockTheTextBox(true);
		btnAdd.setEnabled(true);
		btnExit.setEnabled(true);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	}
	public JTextField getTextFieldStartDateContractYear()
	{
		return txtStartDateContractYear;
	}
	public JTextField getTextFieldStartDateContractMonth()
	{
		return txtStartDateContractMonth;
	}
	public JTextField getTextFieldStartDateContractDay()
	{
		return txtStartDateContractDay;
	}
	public JTextField getTextFieldSalary()
	{
		return txtSalary;
	}
	public JTextField getTextFieldOtherSalary()
	{
		return txtOtherSalary;
	}
	public JComboBox getComboBoxTitle()
	{
		return cboTitle;
	}
	public JComboBox getComboBoxDuration()
	{
		return cboDuration;
	}
	public JButton getButtonAdd()
	{
		return btnAdd;
	}
	public JButton getButtonSave()
	{
		return btnSave;
	}
	public JButton getButtonExit()
	{
		return btnExit;
	}
	public void setEmployeeName(String strName)
	{
		lblEmployeeName.setText(strName);
	}
	public String getCaption()
	{
		return m_strCaption;
	}
}
