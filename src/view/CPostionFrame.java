package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
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

public class CPostionFrame extends JFrame{
	private JTextField txtPostionTitle,txtSalary,txtOtherSalary;
	private JButton btnAdd,btnUpdate,btnDelete;
	private JTable tblDetail;
	private DefaultTableModel tblModel; 
	public CPostionFrame(String strTitle)
	{
		super(strTitle);
		createUI();
		setSize(500, 500);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	private void createUI()
	{
		JPanel pnGeneral=new JPanel();
		JPanel pnGeneralBorder=new JPanel();
		Container con=getContentPane();
		con.add(pnGeneralBorder);
		pnGeneralBorder.add(pnGeneral);
		pnGeneral.setLayout(new BorderLayout());
		JPanel pnTitle=new JPanel();
		JLabel lblTitle=new JLabel("Postion");
		lblTitle.setForeground(Color.BLUE);
		Font font=new Font("Arial", Font.BOLD, 35);
		lblTitle.setFont(font);
		pnTitle.add(lblTitle);
		pnGeneral.add(pnTitle,BorderLayout.NORTH);
		
		JPanel pnInformation=new JPanel();
		pnInformation.setLayout(new BoxLayout(pnInformation, BoxLayout.Y_AXIS));
		
		JPanel pnPositionTitle=new JPanel();
		JLabel lblPostionTitle=new JLabel("Postion Title");
		txtPostionTitle=new JTextField(15);
		pnPositionTitle.add(lblPostionTitle);
		pnPositionTitle.add(txtPostionTitle);
		pnGeneral.add(pnInformation,BorderLayout.CENTER);
		pnInformation.add(pnPositionTitle);
		
		
		JPanel pnSalary=new JPanel();
		JLabel lblSalary=new JLabel("Salary");
		txtSalary=new JTextField(15);
		pnSalary.add(lblSalary);
		pnSalary.add(txtSalary);
		pnInformation.add(pnSalary);
		
		JPanel pnOtherSalary=new JPanel();
		JLabel lblOtherSalary=new JLabel("Other Salary");
		txtOtherSalary=new JTextField(15);
		pnOtherSalary.add(lblOtherSalary);
		pnOtherSalary.add(txtOtherSalary);
		pnInformation.add(pnOtherSalary);
		
		lblSalary.setPreferredSize(lblOtherSalary.getPreferredSize());
		lblPostionTitle.setPreferredSize(lblOtherSalary.getPreferredSize());
		
		JPanel pnButton=new JPanel();
		btnAdd=new JButton("Add");
		btnUpdate=new JButton("Update");
		btnDelete=new JButton("Delete");
		pnButton.add(btnAdd);
		pnButton.add(btnUpdate);
		pnButton.add(btnDelete);
		
		pnInformation.add(pnButton);
		TitledBorder border=new TitledBorder(BorderFactory.createLineBorder(Color.RED), "Detail Information");
		
		pnGeneral.setBorder(border);
		
		JPanel pnTable=new JPanel();
		tblModel=new DefaultTableModel();
		tblModel.addColumn("Postion Title");
		tblModel.addColumn("Salary");
		tblModel.addColumn("Other Salary");
		tblDetail=new JTable(tblModel);
		JScrollPane sc=new JScrollPane(tblDetail);
		pnGeneral.add(sc,BorderLayout.SOUTH);
		
		
		btnAdd.addActionListener(new CMyProcessButtonEvent());
		btnUpdate.addActionListener(new CMyProcessButtonEvent());
		btnDelete.addActionListener(new CMyProcessButtonEvent());
	}
	private void doAdd()
	{
		//JOptionPane.showMessageDialog(this, "Click Add");
		
		Vector<String>vec=new Vector<String>();
		vec.add(txtPostionTitle.getText());
		vec.add(txtSalary.getText());
		vec.add(txtOtherSalary.getText());
		
		tblModel.addRow(vec);
		
		txtPostionTitle.requestFocus();
	}
	private void doUpdate()
	{
		JOptionPane.showMessageDialog(this, "Click Update");
		int r=tblDetail.getSelectedRow();
		Object pt=tblDetail.getValueAt(r, 0);
		Object sl=tblDetail.getValueAt(r, 1);
		Object osl=tblDetail.getValueAt(r, 2);
		txtPostionTitle.setText((String) pt);
		txtSalary.setText((String) sl);
		txtOtherSalary.setText((String) osl);
	}
	private void doDelete()
	{
		JOptionPane.showMessageDialog(this, "Click Delete");
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
