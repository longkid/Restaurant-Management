package view;
/*
 * @author Tu Thi Xuan Hien
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
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
import javax.swing.table.TableColumn;

import model.Position;
import model.PositionList;
import model.PositionTitle;
import model.ProcessFile;

public class CPostionFrame extends JFrame{
	private JComboBox cboTitle;
	private JTextField txtPostionTitle,txtSalary,txtOtherSalary;
	private JButton btnAdd,btnUpdate,btnDelete;
	private JTable tblDetail;
	private DefaultTableModel tblModel;
	private PositionList m_PostionList;
	public CPostionFrame(String strTitle)
	{
		super(strTitle);
		createUI();
		setSize(500, 500);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		m_PostionList=new PositionList();
		setLocationRelativeTo(null);
		loadDataFromFile();
		loadDataOnTable();
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
		txtSalary.setEditable(false);
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
		btnUpdate=new JButton("Update");
		ImageIcon iconEdit=new ImageIcon("images/modify.png");
		btnUpdate.setIcon(iconEdit);
		btnDelete=new JButton("Delete");
		
		ImageIcon iconDelete=new ImageIcon("images/trash.png");
		btnDelete .setIcon(iconDelete);
		
		pnButton.add(btnAdd);
		pnButton.add(btnUpdate);
		pnButton.add(btnDelete);
		TitledBorder borderButtons=new TitledBorder(BorderFactory.createLineBorder(Color.RED), "Choose Action:");
		
		pnButton.setBorder(borderButtons);
		
		
		pnInformation.add(pnButton);
		
		//create Table
		JPanel pnTable=new JPanel();
		pnTable.setLayout(new BorderLayout());
		pnInformation.add(pnTable,BorderLayout.SOUTH);
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
		//diaplay JScrollPane on interface
		pnTable.add(sc,BorderLayout.CENTER);
		TitledBorder borderTable=new TitledBorder(BorderFactory.createLineBorder(Color.RED), "Postion List:");
		pnTable.setBorder(borderTable);
		
		//assign Event into BUtton
		btnAdd.addActionListener(new CMyProcessButtonEvent());
		btnUpdate.addActionListener(new CMyProcessButtonEvent());
		btnDelete.addActionListener(new CMyProcessButtonEvent());
		cboTitle.addActionListener(new CMyProcessButtonEvent());
		tblDetail.addMouseListener(new CMyProcessMouseEvent());
		
		btnAdd.setMnemonic('A');
		btnUpdate.setMnemonic('E');
		btnDelete.setMnemonic('D');
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
	private void doAdd()
	{
		//JOptionPane.showMessageDialog(this, "Click Add");
		if(m_PostionList==null)
			m_PostionList=new PositionList();
		int nSalary=Integer.parseInt(txtSalary.getText());
		int nOtherSalary=Integer.parseInt(txtOtherSalary.getText());
		
		int nIndex=cboTitle.getSelectedIndex();
		
		PositionTitle posTitle=getPostionTitle(nIndex);
		
		//posTitle.
		Position aPostion=new Position(posTitle,nSalary, nOtherSalary);
		boolean bcheckExist=m_PostionList.checkExist(aPostion);
		if(bcheckExist)
		{
			JOptionPane.showMessageDialog(null, "Duplicate Postion Title");
			return;
		}
		m_PostionList.add(aPostion);
		Vector<String>vec=new Vector<String>();
		vec.add(cboTitle.getSelectedItem().toString());
		vec.add(txtSalary.getText());
		vec.add(txtOtherSalary.getText());
		
		tblModel.addRow(vec);
		cboTitle.requestFocus();
		boolean bResult=ProcessFile.WriteData(m_PostionList, ProcessFile.FILENAME);
		if(bResult)
		{
			JOptionPane.showMessageDialog(null, "Save success");
		}
		else
			JOptionPane.showMessageDialog(null, "Save Failed");
	}
	private void loadDataFromFile()
	{
		m_PostionList=null;
		m_PostionList=ProcessFile.ReadData(ProcessFile.FILENAME);
		if(m_PostionList==null)
			m_PostionList=new PositionList();
		//call method read FILE to take the information for m_PostionList
	}
	private void loadDataOnTable()
	{
		if(m_PostionList==null)
			return;
		tblModel.setRowCount(0);//remove all row in the table
		for(int i=0;i<m_PostionList.Count();i++)
		{
			Position aPostion=m_PostionList.get(i);
			Vector<String>vec=new Vector<String>();
			vec.add(getPostionTitleString(aPostion.getTitle()));
			vec.add(aPostion.getSalary()+"");
			vec.add(aPostion.getOtherSalary()+"");
			tblModel.addRow(vec);
		}
		
	}
	private void doUpdate()
	{
		int nRow=tblDetail.getSelectedRow();
				
		Position aPostion=m_PostionList.get(nRow);
		
		int nOtherSalary=Integer.parseInt( txtOtherSalary.getText());
		aPostion.setOtherSalary(nOtherSalary);
		m_PostionList.update(nRow, aPostion);
		
		loadDataOnTable();
	}
	private void doDelete()
	{
		int nRow=tblDetail.getSelectedRow();
		m_PostionList.removeAt(nRow);
		
		loadDataOnTable();
	}
	private void doGetSalary()
	{
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
		}
		
	}
	private void doSelectedOnTable()
	{
		int nRow=tblDetail.getSelectedRow();
		
		Position aPostion=m_PostionList.get(nRow);
		txtSalary.setText(aPostion.getSalary()+"");
		txtOtherSalary.setText(aPostion.getOtherSalary()+"");
		cboTitle.setSelectedIndex(aPostion.getTitle().ordinal());
	}
	
	private class CMyProcessMouseEvent implements MouseListener
	{

		@Override
		public void mouseClicked(MouseEvent e) 
		{
			// TODO Auto-generated method stub
			if(tblDetail!=null)
			{
				doSelectedOnTable();
				
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CPostionFrame myUI=new CPostionFrame("PositionFrame");
	}

}
