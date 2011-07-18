package controller;
/*
 * @author Tu Thi Xuan Hien
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.JOptionPane;

import model.Position;
import model.PositionList;
import model.PositionTitle;
import model.ProcessFile;
import view.CPostionFrame;

public class CPostionController 
{
	private CPostionFrame m_postionFrame=null;
	private PositionList m_postionList=null;
	private Position m_currentPostion=null;
	public CPostionController()
	{
		m_postionFrame=new CPostionFrame("Postion Management");
	}
	public void doShow()
	{
		m_postionFrame.setSize(600, 560);
		m_postionFrame.setVisible(true);
		m_postionFrame.setLocationRelativeTo(null);
		doLoadData();
		addEventforAllControl();
	}
	public void doLoadData()
	{
		loadDataFromFile();
		loadDataOnTable();
	}
	private void loadDataFromFile()
	{
		m_postionList=(PositionList) ProcessFile.ReadData(ProcessFile.FILENAME_POSITION);
		if(m_postionList==null)
			m_postionList=new PositionList();
	}
	private void loadDataOnTable()
	{
		if(m_postionList==null)
			return;
		m_postionFrame.getTableModel().setRowCount(0);//remove all row in the table
		for(int i=0;i<m_postionList.size();i++)
		{
			Position aPostion=m_postionList.get(i);
			Vector<String>vec=new Vector<String>();
			vec.add(Position.getPostionTitleString(aPostion.getTitle()));
			vec.add(aPostion.getSalary()+"");
			vec.add(aPostion.getOtherSalary()+"");
			m_postionFrame.getTableModel().addRow(vec);
		}
		
	}
	public void addEventforAllControl()
	{
		//assign Event into BUtton
		m_postionFrame.getButtonAdd().addActionListener(new CMyProcessButtonEvent());
		m_postionFrame.getButtonUpdate().addActionListener(new CMyProcessButtonEvent());
		m_postionFrame.getButtonDelete().addActionListener(new CMyProcessButtonEvent());
		m_postionFrame.getComboBoxTitle().addActionListener(new CMyProcessButtonEvent());
		m_postionFrame.getButtonSave().addActionListener(new CMyProcessButtonEvent());

		m_postionFrame.getButtonExit().addActionListener(new CMyProcessButtonEvent());
		m_postionFrame.getDetailTable().addMouseListener(new CMyProcessMouseEvent());
		
		LockTheTextBox(true);
		m_postionFrame.getButtonAdd().setEnabled(true);
		m_postionFrame.getButtonUpdate().setEnabled(false);
		m_postionFrame.getButtonDelete().setEnabled(false);
		m_postionFrame.getButtonSave().setEnabled(false);
	}
	private void LockTheTextBox(boolean b)
	{
		m_postionFrame.getTextFieldSalary().setEditable(!b);
		m_postionFrame.getTextFieldOtherSalary().setEditable(!b);
		m_postionFrame.getComboBoxTitle().setEnabled(!b);
	}
	private void doAdd()
	{
		if(m_postionFrame.getButtonAdd().getText().equalsIgnoreCase("Add"))
		{
			m_postionFrame.getButtonAdd().setText("Cancel");
			m_postionFrame.getButtonUpdate().setEnabled(false);
			m_postionFrame.getButtonSave().setEnabled(true);
			m_postionFrame.getButtonDelete().setEnabled(false);
			LockTheTextBox(false);
			m_postionFrame.getDetailTable().setEnabled(false);
			m_postionFrame.getTextFieldSalary().setText("");
			m_postionFrame.getTextFieldOtherSalary().setText("");
		}
		else
		{
			m_postionFrame.getButtonAdd().setText("Add");
			m_postionFrame.getButtonUpdate().setEnabled(false);
			m_postionFrame.getButtonSave().setEnabled(false);
			m_postionFrame.getButtonDelete().setEnabled(false);
			LockTheTextBox(true);
			m_postionFrame.getDetailTable().setEnabled(true);
			if(m_currentPostion!=null)
			{
				m_postionFrame.getTextFieldSalary().setText(m_currentPostion.getSalary()+"");
				m_postionFrame.getTextFieldOtherSalary().setText(m_currentPostion.getOtherSalary()+"");
				m_postionFrame.getComboBoxTitle().setSelectedIndex(m_currentPostion.getTitle().ordinal());
			}
		}
		
	}
	private void doUpdate()
	{
		if(m_postionFrame.getButtonUpdate().getText().equalsIgnoreCase("Update"))
		{
			m_postionFrame.getButtonUpdate().setText("Cancel");
			
			m_postionFrame.getButtonAdd().setEnabled(false);
			m_postionFrame.getButtonUpdate().setEnabled(true);
			m_postionFrame.getButtonSave().setEnabled(true);
			m_postionFrame.getButtonDelete().setEnabled(false);
			LockTheTextBox(false);
		}
		else
		{
			m_postionFrame.getButtonUpdate().setText("Update");
			m_postionFrame.getButtonAdd().setEnabled(true);
			m_postionFrame.getButtonUpdate().setEnabled(true);
			m_postionFrame.getButtonSave().setEnabled(false);
			m_postionFrame.getButtonDelete().setEnabled(true);
			LockTheTextBox(true);
			
			if(m_currentPostion!=null)
			{
				m_postionFrame.getTextFieldSalary().setText(m_currentPostion.getSalary()+"");
				m_postionFrame.getTextFieldOtherSalary().setText(m_currentPostion.getOtherSalary()+"");
				m_postionFrame.getComboBoxTitle().setSelectedIndex(m_currentPostion.getTitle().ordinal());
			}
		}
	}
	private void doDelete()
	{
		int nRow=m_postionFrame.getDetailTable().getSelectedRow();
		if(nRow>=0)
		{
			int ret=JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this item?","Delete Item",JOptionPane.YES_NO_OPTION);
			if(ret==JOptionPane.YES_OPTION)
			{
				m_postionList.removeAt(nRow);
				loadDataOnTable();
			}
		}
	
	}
	private void doExit()
	{
		if(JOptionPane.showConfirmDialog(m_postionFrame, "Are you sure you want to close this fuction", "Close Position Title", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
		{
			m_postionFrame.dispose();
		}
	}
	private void doSelectedOnTable()
	{
		int nRow=m_postionFrame.getDetailTable().getSelectedRow();
		if(nRow>=0 && !m_postionFrame.getButtonAdd().getText().equalsIgnoreCase("Cancel"))
		{
			m_postionFrame.getButtonUpdate().setEnabled(true);
			m_postionFrame.getButtonDelete().setEnabled(true);
			Position aPostion=m_postionList.get(nRow);
			m_currentPostion=aPostion;
			m_postionFrame.getTextFieldSalary().setText(aPostion.getSalary()+"");
			m_postionFrame.getTextFieldOtherSalary().setText(aPostion.getOtherSalary()+"");
			m_postionFrame.getComboBoxTitle().setSelectedIndex(aPostion.getTitle().ordinal());
		}
	}
	private void doGetSalary()
	{
		
		int nIndex=m_postionFrame.getComboBoxTitle().getSelectedIndex();
		
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
		
		m_postionFrame.getTextFieldSalary().setText(nSalary+"");
	}
	private void doSave()
	{
		if(m_postionFrame.getButtonAdd().isEnabled())
		{
			//We save Position title here
			if(m_postionList==null)
				m_postionList=new PositionList();
			int nSalary=Integer.parseInt(m_postionFrame.getTextFieldSalary().getText());
			int nOtherSalary=Integer.parseInt(m_postionFrame.getTextFieldOtherSalary().getText());
			
			int nIndex=m_postionFrame.getComboBoxTitle().getSelectedIndex();
			
			PositionTitle posTitle= Position.getPostionTitle(nIndex);
			
			//posTitle.
			Position aPostion=new Position(posTitle,nSalary, nOtherSalary);
			boolean bcheckExist=m_postionList.checkExist(aPostion);
			if(bcheckExist)
			{
				JOptionPane.showMessageDialog(null, "Duplicate Postion Title");
				return;
			}
			m_postionList.add(aPostion);
			Vector<String>vec=new Vector<String>();
			vec.add(m_postionFrame.getComboBoxTitle().getSelectedItem().toString());
			vec.add(m_postionFrame.getTextFieldSalary().getText());
			vec.add(m_postionFrame.getTextFieldOtherSalary().getText());
			
			m_postionFrame.getTableModel().addRow(vec);
			m_postionFrame.getComboBoxTitle().requestFocus();
			boolean bResult=ProcessFile.WriteData(m_postionList, ProcessFile.FILENAME_POSITION);
			if(bResult)
			{
				JOptionPane.showMessageDialog(null, "Save success");
			}
			else
				JOptionPane.showMessageDialog(null, "Save Failed");
			
			m_postionFrame.getButtonAdd().setText("Add");
			m_postionFrame.getButtonSave().setEnabled(false);
			LockTheTextBox(true);
			m_postionFrame.getDetailTable().setEnabled(true);
		}
		else
		{
			//We update Position title here
			int nRow=m_postionFrame.getDetailTable().getSelectedRow();
			
			Position aPostion=m_postionList.get(nRow);
			int nSalary=Integer.parseInt( m_postionFrame.getTextFieldSalary().getText());
			aPostion.setSalary(nSalary);
			
			int nOtherSalary=Integer.parseInt( m_postionFrame.getTextFieldOtherSalary().getText());
			aPostion.setOtherSalary(nOtherSalary);
			
			m_postionList.update(nRow, aPostion);
			boolean bResult=ProcessFile.WriteData(m_postionList, ProcessFile.FILENAME_POSITION);
			
			if(!bResult)
			{
				JOptionPane.showMessageDialog(null, "Update success");
			}
			loadDataOnTable();
			m_postionFrame.getButtonUpdate().setText("Update");
			m_postionFrame.getButtonSave().setEnabled(false);
			m_postionFrame.getButtonAdd().setEnabled(true);
			LockTheTextBox(true);
		}
	}
	private class CMyProcessButtonEvent implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			Object myObj=e.getSource();
			if(myObj.equals(m_postionFrame.getButtonAdd()))
			{
				doAdd();	
			}
			else if(myObj.equals(m_postionFrame.getButtonUpdate()))
			{
				doUpdate();
			}
			else if(myObj.equals(m_postionFrame.getButtonDelete()))
			{
				doDelete();
			}
			else if(myObj.equals(m_postionFrame.getComboBoxTitle()))
			{
				doGetSalary();
			}
			else if(myObj.equals(m_postionFrame.getButtonSave()))
			{
				doSave();
			}
			else if(myObj.equals(m_postionFrame.getButtonExit()))
			{
				doExit();
			}
		}
		
	}
	
	private class CMyProcessMouseEvent implements MouseListener
	{

		@Override
		public void mouseClicked(MouseEvent e) 
		{
			// TODO Auto-generated method stub
			if(m_postionFrame.getDetailTable()!=null)
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
	//I would like to test main function here
	public static void main(String[] args) {
		CPostionController postionController=new CPostionController();
		postionController.doShow();
	}
}
