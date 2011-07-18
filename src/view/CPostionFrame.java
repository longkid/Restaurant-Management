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
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class CPostionFrame extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JComboBox cboTitle;
	private JButton btnAdd,btnUpdate,btnDelete,btnSave,btnExit;
	
	private JTextField txtSalary,txtOtherSalary;
	private JTable tblDetail;
	private DefaultTableModel tblModel;
	public CPostionFrame(String strTitle)
	{
		super(strTitle);
		createUI();
		setDefaultCloseOperation(HIDE_ON_CLOSE);
	}
	private void createUI()
	{
		JPanel pnGeneral=new JPanel();
		JPanel pnGeneralBorder=new JPanel();
		Container con=getContentPane();
		con.add(pnGeneralBorder);
		pnGeneralBorder.add(pnGeneral);
		pnGeneral.setLayout(new BorderLayout());
		
		//create title: Postion Information
		JPanel pnTitle=new JPanel();
		JLabel lblTitle=new JLabel("Postion Information");
		lblTitle.setForeground(Color.BLUE);
		Font font=new Font("Arial", Font.BOLD, 25);
		lblTitle.setFont(font);
		pnTitle.add(lblTitle);
		pnGeneral.add(pnTitle,BorderLayout.NORTH);
		
		JPanel pnInformation=new JPanel();
		JPanel pnInputInformation=new JPanel();
		
		pnInformation.setLayout(new BoxLayout(pnInformation, BoxLayout.Y_AXIS));
		pnInputInformation.setLayout(new BoxLayout(pnInputInformation, BoxLayout.Y_AXIS));
		pnInformation.add(pnInputInformation);
		//create Postion Title - dung Combobox 
		JPanel pnPositionTitle=new JPanel();
		JLabel lblPostionTitle=new JLabel("Postion Title");
		cboTitle=new JComboBox();
		//Call method add PostionTitle in Combobox
		addPostionTitleForCombobox();
		//txtPostionTitle=new JTextField(15);
		pnPositionTitle.add(lblPostionTitle);
		pnPositionTitle.add(cboTitle); //display combobox on form
		pnGeneral.add(pnInformation,BorderLayout.CENTER);
		pnInputInformation.add(pnPositionTitle);
		
		
		JPanel pnSalary=new JPanel();
		JLabel lblSalary=new JLabel("Salary");
		txtSalary=new JTextField(15);
		pnSalary.add(lblSalary);
		pnSalary.add(txtSalary);
		//txtSalary.setEditable(false);
		pnInputInformation.add(pnSalary);
		
		
		JPanel pnOtherSalary=new JPanel();
		JLabel lblOtherSalary=new JLabel("Other Salary");
		txtOtherSalary=new JTextField(15);
		pnOtherSalary.add(lblOtherSalary);
		pnOtherSalary.add(txtOtherSalary);
		pnInputInformation.add(pnOtherSalary);
		
		//set JLabels are same size
		lblSalary.setPreferredSize(lblPostionTitle.getPreferredSize());
		lblPostionTitle.setPreferredSize(lblOtherSalary.getPreferredSize());
		
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
		pnInformation.add(pnTable);
		//must be DefaultTableModel
		tblModel=new DefaultTableModel();
		//Add Column for TableModel
		tblModel.addColumn("Postion Title");
		tblModel.addColumn("Salary");
		tblModel.addColumn("Other Salary");
		//sau do dua vao Jtable
		tblDetail=new JTable(tblModel);
		//must be JScrollPane to see information in JTable
		JScrollPane sc=new JScrollPane(tblDetail);
		sc.setPreferredSize(new Dimension(500, 200));
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
		
		btnAdd.setMnemonic('A');
		btnUpdate.setMnemonic('E');
		btnDelete.setMnemonic('D');
		btnSave.setMnemonic('S');
	}
	private void addPostionTitleForCombobox()
	{
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
	public JButton getButtonAdd()
	{
		return btnAdd;
	}
	public JButton getButtonUpdate()
	{
		return btnUpdate;
	}
	public JButton getButtonDelete()
	{
		return btnDelete;
	}
	public JButton getButtonSave()
	{
		return btnSave;
	}
	public JButton getButtonExit()
	{
		return btnExit;
	}
	public JComboBox getComboBoxTitle()
	{
		return cboTitle;
	}
	public JTable getDetailTable()
	{
		return tblDetail;
	}
	public DefaultTableModel getTableModel()
	{
		return tblModel;
	}
	public JTextField getTextFieldSalary()
	{
		return txtSalary;
	}
	public JTextField getTextFieldOtherSalary()
	{
		return txtOtherSalary;
	}
}
