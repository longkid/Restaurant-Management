package controller;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.event.MouseInputListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import view.CMyTableCellEditor;
import view.CMyTableCellRenderer;
import view.CTimeKeepingBookFrame;
import model.CTimeKeepingBook;
import model.CTimeKeepingDetailInfor;
import model.CTimeKeepingSheet;
import model.Contract;
import model.Employee;
import model.ProcessFile;
import model.Sex;
enum eSHOW
{
	NEWCONTRACT,
	EDITCONTRACT,
	DELETECONTRACT,
	CALCPAYROLL,
	CALCWORKDAYREPORT,
}
public class CTimeKeepingBookController
{
	private CTimeKeepingBookFrame m_timeKeepingBookFrame=null;
	private List<Employee> m_listEmployee;
	private Employee m_currentEmployee=null;
	private Contract m_currentContract=null;
	private int m_nMonthSelected,m_nYearSelected,m_nNumberDayOfMonth;
	public CTimeKeepingBookController()
	{
		m_timeKeepingBookFrame=new CTimeKeepingBookFrame("Time Keeping Book Management");
	}
	public void simulatorEmployeeData()
	{
		List<Employee> list= new ArrayList<Employee>();
		for(int i=0;i<10;i++)
		{
			Employee emp=new Employee();
			emp.setFullName("Teo _ " +i);
			emp.setBirthday(new Date(1983,1,1));
			if(i%2==0)
				emp.setSex(Sex.MALE);
			else
				emp.setSex(Sex.FEMALE);
			list.add(emp);
		}
		ProcessFile.WriteData(list, ProcessFile.FILENAME_EMPLOYEE);
	}
	public void doShow()
	{
		Dimension screenSize= Toolkit.getDefaultToolkit().getScreenSize();
		m_timeKeepingBookFrame.setSize(screenSize.width,screenSize.height-30);
		m_timeKeepingBookFrame.setVisible(true);
		m_timeKeepingBookFrame.setLocationRelativeTo(null);
		addEventforAllControl();
		doLoadData();
	}
	public void doLoadData()
	{
		//Load Data Employee
		m_listEmployee=(ArrayList<Employee>) ProcessFile.ReadData(ProcessFile.FILENAME_EMPLOYEE);
		loadDataIntoTable(m_listEmployee);
		enableControlForContract();
		if(m_currentEmployee!=null)
		{
			doProcessMonthSelection();
			doGetListContractForEmployee(m_currentEmployee);
			m_timeKeepingBookFrame.getTableEmployee().changeSelection(0, 0, false, false);
			if(m_currentEmployee.getContracts()!=null)
				m_timeKeepingBookFrame.getTreeViewContract().expandRow(2);
		}
		int month=Integer.parseInt(m_timeKeepingBookFrame.getComboBoxMonth().getSelectedItem().toString());
		int year=Integer.parseInt(m_timeKeepingBookFrame.getComboBoxYear().getSelectedItem().toString());
		m_nMonthSelected=month;
		m_nYearSelected=year;
	}
	public void addEventforAllControl()
	{
		m_timeKeepingBookFrame.getTreeViewContract().addTreeSelectionListener(new CTreeEvent());
		m_timeKeepingBookFrame.getTableEmployee().addMouseListener(new CProcessMouseEvent());
		m_timeKeepingBookFrame.getComboBoxMonth().addActionListener(new CButtonEvent());
		m_timeKeepingBookFrame.getComboBoxYear().addActionListener(new CButtonEvent());
		m_timeKeepingBookFrame.getButtonSaveTimeKeeping().addActionListener(new CButtonEvent());
		m_timeKeepingBookFrame.getButtonSearch().addActionListener(new CButtonEvent());
		m_timeKeepingBookFrame.getButtonShowAll().addActionListener(new CButtonEvent());
		m_timeKeepingBookFrame.getButtonTrash().addActionListener(new CButtonEvent());
		m_timeKeepingBookFrame.getButtonAddNewContract().addActionListener(new CButtonEvent());
		m_timeKeepingBookFrame.getButtonModifyContract().addActionListener(new CButtonEvent());
		m_timeKeepingBookFrame.getButtonCalcPayroll().addActionListener(new CButtonEvent());
		m_timeKeepingBookFrame.getButtonReport().addActionListener(new CButtonEvent());
		m_timeKeepingBookFrame.getButtonClose().addActionListener(new CButtonEvent());
		m_timeKeepingBookFrame.getMenuItemSystemExit().addActionListener(new CButtonEvent());
	}
	private void enableControlForContract()
	{
		boolean bEnable=true;
		if(m_currentContract==null)
		{
			bEnable=false;
		}
		m_timeKeepingBookFrame.getButtonModifyContract().setEnabled(bEnable);
		m_timeKeepingBookFrame.getButtonCalcPayroll().setEnabled(bEnable);
		m_timeKeepingBookFrame.getButtonTrash().setEnabled(bEnable);
		m_timeKeepingBookFrame.getComboBoxMonth().setEnabled(bEnable);
		m_timeKeepingBookFrame.getComboBoxYear().setEnabled(bEnable);
		m_timeKeepingBookFrame.getButtonSaveTimeKeeping().setEnabled(bEnable);
		m_timeKeepingBookFrame.getPanelRight().setEnabled(bEnable);
		m_timeKeepingBookFrame.getTableTimeKeeping().setVisible(bEnable);
		if(!bEnable)
			m_timeKeepingBookFrame.getTableModelTimeKeeping().setColumnCount(0);
	}
	private void loadDataIntoTable(List<Employee> listEmployee)
	{
		if(listEmployee!=null)
		{
			m_timeKeepingBookFrame.getTableModelEmployee().setRowCount(0);
			for(int i =0 ; i< listEmployee.size();i++)
			{
				Employee emp =listEmployee.get(i);
				m_timeKeepingBookFrame.getTableModelEmployee().addRow(emp.getVector());
			}
			if(listEmployee.size()>0)
				{
					m_currentEmployee=listEmployee.get(0);
					m_currentContract=m_currentEmployee.getCurrentContract();
				}
		}
	}
	private boolean isLeapYear(int year)
	{
		if((year%4==0 && year%100!=0) || year %400==0)
			return true;
		return false;
	}
	private int numberDayOfMonth(int month,int year)
	{
		int n=31;
		boolean bLeap=isLeapYear(year);
		switch(month)
		{
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			n=31;
			break;
		case 4:
		case 6:
		case 9:
		case 11:
			n=30;
			break;
		case 2:
			n=28;
			if(bLeap==true)
				n=29;
		}
		return n;
	}
	private void doActionFromToolBar(eSHOW eShow)
	{
		switch(eShow)
		{
		case NEWCONTRACT:
			CContactController newContactController=new CContactController("New contract","New contract:", m_currentEmployee, m_listEmployee);
			newContactController.doShow();
			if(newContactController.isSave())
			{
				processMouseClickOnEmployeeTable();
				int nRow=m_timeKeepingBookFrame.getTableEmployee().getSelectedRow();
				loadDataIntoTable(m_listEmployee);
				m_timeKeepingBookFrame.getTableEmployee().changeSelection(nRow, 0, false, false);
			}
			break;
		case EDITCONTRACT:
			CContactController editContactController=new CContactController("Edit contract","Edit contract:", m_currentEmployee, m_listEmployee);
			editContactController.doShow();
			editContactController.updateInformationForEdit();
			if(editContactController.isSave())
			{
				processMouseClickOnEmployeeTable();
			}
			break;
		case DELETECONTRACT:
			//JOptionPane.showMessageDialog(null,"Delete this contract");
			DefaultMutableTreeNode node= (DefaultMutableTreeNode )m_timeKeepingBookFrame.getTreeViewContract().getLastSelectedPathComponent();
			if(node==null)
			{
				JOptionPane.showMessageDialog(null, "please choose contract to delete");
				return;
			}
			Object obj= node.getUserObject();
			if(obj instanceof Contract)
			{
				Object[] options = { "Yes", "No"};
				ImageIcon icon=new ImageIcon("images/trash.png");
				int ret=JOptionPane.showOptionDialog(null, "Are you sure you want to delete ["+obj.toString()+"]?", "Delete Contract",

				            JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,

				            icon, options, options[0]);
				if(ret!=0)
					return;
				if(m_currentEmployee!=null)
				{
					ArrayList<Contract> listContracts=(ArrayList<Contract>) m_currentEmployee.getContracts();
					if(listContracts.contains(obj))
					{
						listContracts.remove(obj);
					}
					else
					{
						m_currentEmployee.setContracts(null);
					}
					m_currentEmployee.setContracts(listContracts);
					ProcessFile.WriteData(m_listEmployee, ProcessFile.FILENAME_EMPLOYEE);
				}
				processMouseClickOnEmployeeTable();
			}
			
			break;
		case CALCPAYROLL:
			calcPayroll();
			break;
		case CALCWORKDAYREPORT:
			calcWorkdayReport();
			break;
		
		}
	}
	private void calcPayroll()
	{
		CPrintPreviewController printPreviewController=new CPrintPreviewController();
		printPreviewController.setTittle("Printing Calc Payroll");
		printPreviewController.setJobName("calcPayroll");
		printPreviewController.setListEmployee(m_listEmployee);
		printPreviewController.setMonth(m_nMonthSelected);
		printPreviewController.setYear(m_nYearSelected);
		printPreviewController.setContent(printPreviewController.createPayRollReport());
		printPreviewController.doShow();
	}
	private void calcWorkdayReport()
	{
		/*CPrintPreview print=new CPrintPreview("Printing Workday Report");
		Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
		print.setSize(screenSize.width/2, screenSize.height/2);
		print.setLocationRelativeTo(this);
		print.setContent(print.createWorkSumaryReport());
		print.setJobName("calcWorkdayReport");
		print.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		print.setVisible(true);*/
	}
	private void processTreeSelection()
	{
		DefaultMutableTreeNode node= (DefaultMutableTreeNode )m_timeKeepingBookFrame.getTreeViewContract().getLastSelectedPathComponent();
		Object obj= node.getUserObject();
		if(obj instanceof Contract)
		{
			JOptionPane.showMessageDialog(null, obj.toString());	
		}
	}
	private void doGetListContractForEmployee(Employee currentEmployee)
	{
		Contract currentContract=currentEmployee.getCurrentContract();
		ArrayList<Contract> listContracts=(ArrayList<Contract>) currentEmployee.getContracts();
		//Need remove all child node
		m_timeKeepingBookFrame.getRootNode().removeAllChildren();
		if(currentContract!=null)
		{
			//Add Current Contract here
			DefaultMutableTreeNode currentNodeContract=new DefaultMutableTreeNode(currentContract);
			m_timeKeepingBookFrame.getRootNode().add(currentNodeContract);
		}
		if(listContracts!=null)
		{
			//update history here
			DefaultMutableTreeNode oldNodeContract=new DefaultMutableTreeNode("Old Contract");
			m_timeKeepingBookFrame.getRootNode().add(oldNodeContract);
			for(Contract con: listContracts)
			{
				DefaultMutableTreeNode oldNodeContractSub=new DefaultMutableTreeNode(con);
				oldNodeContract.add(oldNodeContractSub);
			}
		}
		m_timeKeepingBookFrame.getTreeViewContract().updateUI();
	}
	private void doSearch()
	{
		String strName=m_timeKeepingBookFrame.getTextFieldSearch().getText();
		List<Employee> listSearch = new ArrayList<Employee>();
		
		for(int i=0;i<m_listEmployee.size();i++)
		{
			Employee employee=m_listEmployee.get(i);
			if(employee.getFullName().indexOf(strName)!=-1)
			{
				listSearch.add(employee);
			}
		}
		loadDataIntoTable(listSearch);
	}
	private void doShowAll()
	{
		loadDataIntoTable(m_listEmployee);
	}
	private void processMouseClickOnEmployeeTable()
	{
		int row=m_timeKeepingBookFrame.getTableEmployee().getSelectedRow();
		m_currentEmployee=m_listEmployee.get(row);
		m_currentContract=m_currentEmployee.getCurrentContract();
		if(m_currentContract!=null)
		{
			doProcessMonthSelection();
			doGetListContractForEmployee(m_currentEmployee);
			if(m_currentEmployee.getContracts()!=null)
				m_timeKeepingBookFrame.getTreeViewContract().expandRow(2);
		}
		else
		{
			m_timeKeepingBookFrame.getRootNode().removeAllChildren();
			m_timeKeepingBookFrame.getTreeViewContract().updateUI();
		}
		enableControlForContract();
	}
	private void doProcessSaveTimeKeeping()
	{
		if(m_currentEmployee!=null)
		{
			if(m_currentContract!=null)
			{
				CTimeKeepingBook keepBook=m_currentContract.getTimeKeeping();
				if(keepBook==null)
					keepBook=new CTimeKeepingBook();
				if(keepBook!=null)
				{
					CTimeKeepingSheet keepSheet=keepBook.get(m_nMonthSelected, m_nYearSelected);
					if(keepSheet==null)
					{
						keepSheet=new CTimeKeepingSheet();
						Date dateKeepSheet=new Date(m_nYearSelected ,m_nMonthSelected,1);
						keepSheet.setLastModified(dateKeepSheet);
					}
					keepSheet.clear();
					for(int i=0;i<m_nNumberDayOfMonth;i++)
					{
						CTimeKeepingDetailInfor keepDetail=new CTimeKeepingDetailInfor();
						Boolean bcheck=(Boolean ) ((Vector<Object>)m_timeKeepingBookFrame.getTableModelTimeKeeping().getDataVector().elementAt(i)).elementAt(0);
						Date date=new Date(m_nYearSelected,m_nMonthSelected,i+1);
						String strWho=(String) ((Vector<Object>)m_timeKeepingBookFrame.getTableModelTimeKeeping().getDataVector().elementAt(i)).elementAt(2);
						String strReason=(String) ((Vector<Object>)m_timeKeepingBookFrame.getTableModelTimeKeeping().getDataVector().elementAt(i)).elementAt(3);
						keepDetail.setDateWorking(date);
						keepDetail.setIsWorking(bcheck);
						keepDetail.setWhoCheck(strWho);
						keepDetail.setReason(strReason);
						keepSheet.add(keepDetail);
					}
					keepBook.add(keepSheet);
					m_currentContract.setTimeKeeping(keepBook);
					m_currentEmployee.setCurrentContract(m_currentContract);
					ProcessFile.WriteData(m_listEmployee, ProcessFile.FILENAME_EMPLOYEE);
					JOptionPane.showMessageDialog(null, "Save Data success!");
				}
			}
		}				
	}
	private void updateDataTableTimeKeeping(int n,int month,int year)
	{
		//Updating Status foreach Keeptime details
		 Object [][]objData=new Object[n][];
		 Object []objColumn=new Object[]{"Working", "Date", "Who is check?", "Reason" };
		 if(m_currentEmployee!=null)
			{
				if(m_currentContract!=null)
				{
					CTimeKeepingBook keepBook=m_currentContract.getTimeKeeping();
					if(keepBook!=null)
					{
						CTimeKeepingSheet keepSheet=keepBook.get(month, year);
						if(keepSheet==null)
						{
							for(int i=0;i<n;i++)
							{
								Object []objValue=new Object[]{ new Boolean(false), (i+1)+"/"+month+"/"+year, "Xuan Hien",
						                "" };
								objData[i]=objValue;
							}
						}
						else
						{
							for(int i=0;i<keepSheet.size();i++)
							{
								CTimeKeepingDetailInfor infor=keepSheet.get(i);
								Object []objValue=new Object[]{ new Boolean(infor.getIsWorking()), (i+1)+"/"+month+"/"+year, infor.getWhoCheck(),
										infor.getReason() };
								objData[i]=objValue;								
							}
						}
						
					}
				}
			}
		 else
		 {
			 for(int i=0;i<n;i++)
				{
					Object []objValue=new Object[]{ new Boolean(false), (i+1)+"/"+month+"/"+year, "Xuan Hien",
			                "" };
					objData[i]=objValue;
				}
		 }
		m_timeKeepingBookFrame.getTableModelTimeKeeping().setDataVector(objData,objColumn);
		m_timeKeepingBookFrame.getTableTimeKeeping().getColumn("Working").setCellRenderer(new CMyTableCellRenderer() );
		m_timeKeepingBookFrame.getTableTimeKeeping().getColumn("Working").setCellEditor(new CMyTableCellEditor());
	}
	private void doProcessYearSelection()
	{
		doProcessMonthSelection();
	}
	private void doProcessMonthSelection()
	{
		int month=Integer.parseInt(m_timeKeepingBookFrame.getComboBoxMonth().getSelectedItem().toString());
		int year=Integer.parseInt(m_timeKeepingBookFrame.getComboBoxYear().getSelectedItem().toString());
		int n=numberDayOfMonth(month, year);
		m_nMonthSelected=month;
		m_nYearSelected=year;
		m_nNumberDayOfMonth=n;
		updateDataTableTimeKeeping(n,month,year);
	}
	private void doExit()
	{
		Object[] options = { "I Say Yes", "I Say No"};
		ImageIcon icon=new ImageIcon("images/exit.png");
		int ret=JOptionPane.showOptionDialog(null, "Are you sure you want to exit?", "Exit",

		            JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,

		            icon, options, options[0]);
		if(ret==0)
			m_timeKeepingBookFrame.dispose();

	}
	private class CProcessMouseEvent implements MouseListener, MouseMotionListener, MouseInputListener
	{

		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub
			Object o=arg0.getSource();
			if(o.equals(m_timeKeepingBookFrame.getTableEmployee()))
			{
				processMouseClickOnEmployeeTable();
			}
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseDragged(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseMoved(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		
	}
	private class CButtonEvent implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Object o=e.getSource();
			if(o.equals(m_timeKeepingBookFrame.getButtonAddNewContract()))
			{
				doActionFromToolBar(eSHOW.NEWCONTRACT);
			}
			
			else if(o.equals(m_timeKeepingBookFrame.getButtonModifyContract()))
			{
				doActionFromToolBar(eSHOW.EDITCONTRACT);
			}
			else if(o.equals(m_timeKeepingBookFrame.getButtonTrash()))
			{
				doActionFromToolBar(eSHOW.DELETECONTRACT);
			}
			
			else if(o.equals(m_timeKeepingBookFrame.getButtonCalcPayroll()))
			{
				doActionFromToolBar(eSHOW.CALCPAYROLL);
			}
			else if(o.equals(m_timeKeepingBookFrame.getMenuItemSystemExit()))
			{
				doExit();
			}
			else if(o.equals(m_timeKeepingBookFrame.getComboBoxMonth()))
			{
				doProcessMonthSelection();
			}
			else if(o.equals(m_timeKeepingBookFrame.getComboBoxYear()))
			{
				doProcessYearSelection();
			}
			else if(o.equals(m_timeKeepingBookFrame.getButtonSaveTimeKeeping()))
			{
				doProcessSaveTimeKeeping();
			}
			else if(o.equals(m_timeKeepingBookFrame.getButtonClose()))
			{
				doExit();
			}
			else if(o.equals(m_timeKeepingBookFrame.getButtonSearch()))
			{
				doSearch();
			}
			else if(o.equals(m_timeKeepingBookFrame.getButtonShowAll()))
			{
				doShowAll();
			}
		}
		
	}
	private class CTreeEvent implements TreeSelectionListener
	{

		@Override
		public void valueChanged(TreeSelectionEvent e) {
			// TODO Auto-generated method stub
			processTreeSelection();
		}
		
	}
	public static void main(String[] args) 
	{
		CTimeKeepingBookController timeKeepingBookController=new CTimeKeepingBookController();
		timeKeepingBookController.doShow();		
	}
}
