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
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.border.TitledBorder;
import javax.swing.event.MouseInputListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import model.CListEmployee;
import model.CTimeKeepingBook;
import model.CTimeKeepingDetailInfor;
import model.CTimeKeepingSheet;
import model.Contract;
import model.Employee;
import model.ProcessFile;
import model.Sex;
@SuppressWarnings("deprecation")
public class CTimeKeepingBookFrame extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JMenuBar menuBar;
	private JMenu menuSystem;
	private JMenuItem menuSystemExit;
	
	private JToolBar toolBar;
	
	private JButton btnAddNewContract,btnModifyContract,btnCalcPayroll,btnReport,btnTrash,btnSearch,btnShowAll,btnSaveTimeKeeping,btnClose;
	private JTextField txtSearch;
	JPanel pnTree;
	JPanel pnTable;
	private DefaultMutableTreeNode root=null;
	private JTree myTree=null;
	
	private DefaultTableModel tblModelTimeKeeping,tblModelEmployee;
	private JTable tblTimeKeeping,tblEmployee;
	private PopupMenu myPopup;
	private JComboBox cboMonth,cboYear;
	private CListEmployee m_listEmployee;
	private Employee m_currentEmployee=null;
	private Contract m_currentContract=null;
	private int m_nMonthSelected,m_nYearSelected,m_nNumberDayOfMonth;
	enum eSHOW
	{
		NEWCONTRACT,
		EDITCONTRACT,
		DELETECONTRACT,
		CALCPAYROLL,
		CALCWORKDAYREPORT,
	}
	public void simulatorEmployeeData()
	{
		CListEmployee list=new CListEmployee();
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
	public CTimeKeepingBookFrame(String strTitle)
	{
		//simulatorEmployeeData();
		setTitle(strTitle);
		createMenu();
		createToolBar();
		Container con= getContentPane();
		con.setLayout(new BorderLayout());
		con.add(toolBar,BorderLayout.NORTH);
		JPanel pnCenterGeneral=new JPanel();
		con.add(pnCenterGeneral,BorderLayout.CENTER);
		pnCenterGeneral.setLayout(new BorderLayout());
		
		pnTree=new JPanel();
		pnTree.setLayout(new BorderLayout());
		pnTree.setBackground(Color.RED);
		pnTree.setPreferredSize(new Dimension(400, 0));
		
		pnTable=new JPanel();
		pnTable.setBackground(Color.BLUE);
		pnTable.setLayout(new BorderLayout());
		
		JPanel pnChooseDate=new JPanel();
		pnChooseDate.setPreferredSize(new Dimension(0, 70));
		
		TitledBorder borderChooseDate=new TitledBorder(BorderFactory.createLineBorder(Color.BLUE), "Select Month & Year to check Time Keeping");
		borderChooseDate.setTitleColor(Color.RED);
		pnChooseDate.setBorder(borderChooseDate);
		pnTable.add(pnChooseDate,BorderLayout.NORTH);
		JLabel lblMonth=new JLabel("Month:");
		pnChooseDate.add(lblMonth);
		cboMonth=new JComboBox();
		for(int i=1;i<=12;i++)
		{
			cboMonth.addItem(i);
		}
		pnChooseDate.add(cboMonth);
		cboYear =new JComboBox();
		Calendar cal=Calendar.getInstance();
		java.util.Date date= cal.getTime();
		
		int d=date.getYear()+1900;
		System.out.println(d+"");
		for(; d>=2000;d--)
		{
			cboYear.addItem(d);
		}
		JLabel lblYear=new JLabel("Year:");
		pnChooseDate.add(lblYear);
		pnChooseDate.add(cboYear);
		btnSaveTimeKeeping=new JButton("Save");
		pnChooseDate.add(btnSaveTimeKeeping);
		btnSaveTimeKeeping.setIcon(new ImageIcon("images/savek.png"));
		tblModelTimeKeeping = new DefaultTableModel() {
		     
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
		    	  if (column == 0 || column == 3) {
		          return true;
		        }
		        return false;
		      }
		    };
	
		tblTimeKeeping=new JTable(tblModelTimeKeeping);
		JPanel pnTableTimeKeeping=new JPanel();
		pnTableTimeKeeping.setLayout(new BorderLayout());
		pnTableTimeKeeping.add(new JScrollPane(tblTimeKeeping),BorderLayout.CENTER);
		pnTable.add(pnTableTimeKeeping,BorderLayout.CENTER);
		
		TitledBorder borderTimeKeeping=new TitledBorder(BorderFactory.createLineBorder(Color.BLUE), "Time Keeping Table");
		pnTableTimeKeeping.setBorder(borderTimeKeeping);
		
		JSplitPane splt=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, pnTree, pnTable);
		splt.setOneTouchExpandable(true);
		pnCenterGeneral.add(splt,BorderLayout.CENTER);
		
		JPanel pnSearch=new JPanel();
		pnSearch.setLayout(new FlowLayout(FlowLayout.LEFT));
		JPanel pnInputSearch=new JPanel();
		pnInputSearch.setLayout(new BoxLayout(pnInputSearch,BoxLayout.Y_AXIS));
		pnSearch.add(pnInputSearch);
		JPanel pnSearchName=new JPanel();
		pnSearchName.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblSearch=new JLabel("Input Name's Employee :");
		JPanel pnInputName=new JPanel();
		pnInputName.setLayout(new FlowLayout(FlowLayout.LEFT));
		pnInputName.add(lblSearch);
		pnInputSearch.add(pnInputName);
		txtSearch=new JTextField(18);
		btnSearch=new JButton("Search");
		pnSearchName.add(txtSearch);
		pnInputSearch.add(pnSearchName);
		JPanel pnSearchButton=new JPanel();
		pnSearchButton.setLayout(new FlowLayout(FlowLayout.LEFT));
		pnSearchButton.add(btnSearch);
		btnShowAll=new JButton("Show All");
		ImageIcon iconAll=new ImageIcon("images/showall.png");
		btnShowAll.setIcon(iconAll);
		btnShowAll.setSize(20, 10);
		pnSearchButton.add(btnShowAll);
		pnInputSearch.add(pnSearchButton);
		pnSearchButton.setLayout(new FlowLayout(FlowLayout.LEFT));
		ImageIcon iconSearch=new ImageIcon("images/search.png");
		btnSearch.setIcon(iconSearch);
		TitledBorder borderSearch=new TitledBorder(BorderFactory.createLineBorder(Color.BLUE), "Searching Box");
		pnSearch.setBorder(borderSearch);
		
		
		tblModelEmployee=new DefaultTableModel();
		tblModelEmployee.addColumn("Full Name");
		tblModelEmployee.addColumn("Birthday");
		tblModelEmployee.addColumn("Sex");
		tblEmployee=new JTable(tblModelEmployee);
		TitledBorder borderTable=new TitledBorder(BorderFactory.createLineBorder(Color.RED), "Employee List");
		JPanel pnEmployeeTable=new JPanel();
		pnEmployeeTable.setLayout(new BorderLayout());
		pnEmployeeTable.setBorder(borderTable);
		JScrollPane sremployee=new JScrollPane(tblEmployee);
		pnEmployeeTable.add(sremployee,BorderLayout.CENTER);
		//Load Data Employee
		m_listEmployee=(CListEmployee) ProcessFile.ReadData(ProcessFile.FILENAME_EMPLOYEE);
		loadDataIntoTable(m_listEmployee);
	
		pnTree.add(pnEmployeeTable,BorderLayout.CENTER);		
		pnTree.add(pnSearch,BorderLayout.NORTH);
		
		root=new DefaultMutableTreeNode("Contract History");
		DefaultTreeModel tree=new DefaultTreeModel(root);
		myTree=new JTree(tree);
		myTree.addTreeSelectionListener(new CTreeEvent());
		JPanel pnTreeDetail=new JPanel();
		pnTreeDetail.setLayout(new BorderLayout());
		pnTreeDetail.add(new JScrollPane(myTree),BorderLayout.CENTER);
		pnTreeDetail.setPreferredSize(new Dimension(400, 250));
		
		TitledBorder borderContract=new TitledBorder(BorderFactory.createLineBorder(Color.BLUE), "Contract History");
		pnTreeDetail.setBorder(borderContract);
		pnTree.add(pnTreeDetail,BorderLayout.SOUTH);
		//createContextMenu();
		myPopup=new PopupMenu("test");
		MenuItem mnitema=new MenuItem("a"); 
		MenuItem mnitemb=new MenuItem("b");
		myPopup.add(mnitema);
		myPopup.add(mnitemb);
		tblEmployee.add(myPopup);
		tblEmployee.addMouseListener(new CProcessMouseEvent());
		cboMonth.addActionListener(new CButtonEvent());
		cboYear.addActionListener(new CButtonEvent());
		btnSaveTimeKeeping.addActionListener(new CButtonEvent());
		btnSearch.addActionListener(new CButtonEvent());
		btnShowAll.addActionListener(new CButtonEvent());
		int month=Integer.parseInt(cboMonth.getSelectedItem().toString());
		int year=Integer.parseInt(cboYear.getSelectedItem().toString());
		m_nMonthSelected=month;
		m_nYearSelected=year;
	}
	private void loadDataIntoTable(CListEmployee listEmployee)
	{
		if(listEmployee!=null)
		{
			tblModelEmployee.setRowCount(0);
			for(int i =0 ; i< listEmployee.size();i++)
			{
				Employee emp =listEmployee.get(i);
				tblModelEmployee.addRow(emp.getVector());
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
			//JOptionPane.showMessageDialog(null,"new contract");
			ContractFrame cframe=new ContractFrame("New contract","New contract:",m_currentEmployee,m_listEmployee);
			cframe.setSize(550, 350);
			cframe.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
			cframe.setLocationRelativeTo(null);
			cframe.setVisible(true);
			break;
		case EDITCONTRACT:
			ContractFrame cframeEdit=new ContractFrame("Edit contract","Edit contract:",m_currentEmployee,m_listEmployee);
			cframeEdit.setSize(550,350);
			cframeEdit.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
			cframeEdit.setLocationRelativeTo(null);
			cframeEdit.setVisible(true);
			break;
		case DELETECONTRACT:
			JOptionPane.showMessageDialog(null,"Delete this contract");
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
		CPrintPreview print=new CPrintPreview("Printing Calc Payroll");
		Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
		print.setSize(screenSize.width/2, screenSize.height/2);
		print.setLocationRelativeTo(this);
		print.setJobName("calcPayroll");
		print.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		print.setEmployee(m_currentEmployee);
		print.setListEmployee(m_listEmployee);
		print.setContract(m_currentContract);
		print.setMonth(m_nMonthSelected);
		print.setYear(m_nYearSelected);
		print.setContent(print.createPayRollReport());
		print.setVisible(true);
	}
	private void calcWorkdayReport()
	{
		CPrintPreview print=new CPrintPreview("Printing Workday Report");
		Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
		print.setSize(screenSize.width/2, screenSize.height/2);
		print.setLocationRelativeTo(this);
		print.setContent(print.createWorkSumaryReport());
		print.setJobName("calcWorkdayReport");
		print.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		print.setVisible(true);
	}
	private void processTreeSelection()
	{
		DefaultMutableTreeNode node= (DefaultMutableTreeNode )myTree.getLastSelectedPathComponent();
		Object obj= node.getUserObject();
		JOptionPane.showMessageDialog(null, obj.toString());
	}
	public void doShow()
	{
		Dimension screenSize= Toolkit.getDefaultToolkit().getScreenSize();
		setSize(screenSize.width,screenSize.height-30);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	private void createMenu()
	{
		menuBar=new JMenuBar();
		setJMenuBar(menuBar);
		menuSystem=new JMenu("System");
		ImageIcon icon=new ImageIcon("images/system.png");
		menuSystem.setIcon(icon);
		menuSystemExit=new JMenuItem("Exit");
		icon=new ImageIcon("images/systemclose.png");
		menuSystemExit.setIcon(icon);
		menuBar.add(menuSystem);
		menuSystem.add(menuSystemExit);
		menuSystemExit.addActionListener(new CButtonEvent());
		
	}
	private void createToolBar()
	{
		toolBar=new JToolBar("Contract Toolbar");
		btnAddNewContract=new JButton("New Contract");
		btnModifyContract=new JButton("Edit Contract");
		btnCalcPayroll=new JButton("Calc Payroll");
		btnReport=new JButton("Report");
		btnTrash=new JButton("Delete Contract");
		toolBar.add(btnAddNewContract);
		toolBar.addSeparator(new Dimension(20, 0));
		toolBar.add(btnModifyContract);
		toolBar.addSeparator(new Dimension(20, 0));
		toolBar.add(btnTrash );
		toolBar.addSeparator(new Dimension(20, 0));
		toolBar.add(btnCalcPayroll);
		
		btnClose=new JButton("Close");
		ImageIcon icon=new ImageIcon("images/customer.png");
		btnAddNewContract.setIcon(icon);
		
		icon=new ImageIcon("images/modify.png");
		btnModifyContract.setIcon(icon);
		icon=new ImageIcon("images/calc.png");
		btnCalcPayroll.setIcon(icon);
		icon=new ImageIcon("images/report.png");
		btnReport.setIcon(icon);
		icon=new ImageIcon("images/trash.png");
		btnTrash.setIcon(icon);
		
		icon=new ImageIcon("images/close.png");
		btnClose.setIcon(icon);
		toolBar.addSeparator(new Dimension(20, 0));
		JPanel pnSeparator=new JPanel();
		pnSeparator.setBackground(Color.LIGHT_GRAY);
	
		JLabel lblTitle=new JLabel("Time Keeping Management");
		Font font=new Font("Arial", Font.BOLD, 25);
		lblTitle.setFont(font);
		lblTitle.setForeground(Color.BLUE);
		pnSeparator.add(lblTitle);
		toolBar.add(pnSeparator);
		toolBar.addSeparator(new Dimension(20, 0));
		toolBar.add(btnClose);
		
		btnTrash.addActionListener(new CButtonEvent());
		btnAddNewContract.addActionListener(new CButtonEvent());
		btnModifyContract.addActionListener(new CButtonEvent());
		btnCalcPayroll.addActionListener(new CButtonEvent());
		btnReport.addActionListener(new CButtonEvent());
		btnClose.addActionListener(new CButtonEvent());
	}
	private void doExit()
	{
		Object[] options = { "I Say Yes", "I Say No"};
		ImageIcon icon=new ImageIcon("images/exit.png");
		int ret=JOptionPane.showOptionDialog(null, "Are you sure you want to exit?", "Exit",

		            JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,

		            icon, options, options[0]);
		if(ret==0)
			dispose();

	}
	private void doProcessYearSelection()
	{
		doProcessMonthSelection();
	}
	private void doProcessMonthSelection()
	{
		int month=Integer.parseInt(cboMonth.getSelectedItem().toString());
		int year=Integer.parseInt(cboYear.getSelectedItem().toString());
		int n=numberDayOfMonth(month, year);
		m_nMonthSelected=month;
		m_nYearSelected=year;
		m_nNumberDayOfMonth=n;
		updateDataTableTimeKeeping(n,month,year);
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
		
		tblModelTimeKeeping.setDataVector(objData,objColumn);
		tblTimeKeeping.getColumn("Working").setCellRenderer(new MultiRenderer());
		tblTimeKeeping.getColumn("Working").setCellEditor(new MultiEditor());
	}
	private void processMouseClickOnEmployeeTable()
	{
		int row=tblEmployee.getSelectedRow();
		m_currentEmployee=m_listEmployee.get(row);
		m_currentContract=m_currentEmployee.getCurrentContract();
		if(m_currentContract==null)
			m_currentContract=new Contract();
		doProcessMonthSelection();
		
		doGetListContractForEmployee(m_currentEmployee);
	}
	private String parseNodeTitle(Contract con)
	{
		int day=con.getStartDate().getDay();
		int month=con.getStartDate().getMonth();
		int year=con.getStartDate().getYear();
		 if(year-1900<0)
			 year=year+1900;
		String strNode="("+con.getPosition().getTitle().toString()+")-"+day+"/"+month+"/"+(year);
		
		return strNode;
	}
	private void doGetListContractForEmployee(Employee currentEmployee)
	{
		Contract currentContract=currentEmployee.getCurrentContract();
		ArrayList<Contract> listContracts=(ArrayList<Contract>) currentEmployee.getContracts();
		//Need remove all child node
		root.removeAllChildren();
		if(currentContract!=null)
		{
			//Add Current Contract here
			String strNode=parseNodeTitle(currentContract);
			DefaultMutableTreeNode currentNodeContract=new DefaultMutableTreeNode("Current-"+strNode);
			root.add(currentNodeContract);
		}
		if(listContracts!=null)
		{
			//update history here
			DefaultMutableTreeNode oldNodeContract=new DefaultMutableTreeNode("Old Contract");
			root.add(oldNodeContract);
			for(Contract con: listContracts)
			{
				String strNode=parseNodeTitle(con);
				DefaultMutableTreeNode oldNodeContractSub=new DefaultMutableTreeNode("Old-"+strNode);
				oldNodeContract.add(oldNodeContractSub);
			}
		}
		myTree.updateUI();
	}
	private void doSearch()
	{
		String strName=txtSearch.getText();
		CListEmployee listSearch= m_listEmployee.searchData(strName);
		loadDataIntoTable(listSearch);
	}
	private void doShowAll()
	{
		loadDataIntoTable(m_listEmployee);
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
						Boolean bcheck=(Boolean ) ((Vector<Object>)tblModelTimeKeeping.getDataVector().elementAt(i)).elementAt(0);
						Date date=new Date(m_nYearSelected,m_nMonthSelected,i+1);
						String strWho=(String) ((Vector<Object>)tblModelTimeKeeping.getDataVector().elementAt(i)).elementAt(2);
						String strReason=(String) ((Vector<Object>)tblModelTimeKeeping.getDataVector().elementAt(i)).elementAt(3);
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
	private class CProcessMouseEvent implements MouseListener, MouseMotionListener, MouseInputListener
	{

		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub
			Object o=arg0.getSource();
			if(o.equals(tblEmployee))
			{
				processMouseClickOnEmployeeTable();
			}
			else
			{
				if(arg0.getButton()==3)
				{
					
					myPopup.show(tblEmployee,arg0.getX(),arg0.getY());
				}	
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
			if(o.equals(btnAddNewContract))
			{
				doActionFromToolBar(eSHOW.NEWCONTRACT);
			}
			
			else if(o.equals(btnModifyContract))
			{
				doActionFromToolBar(eSHOW.EDITCONTRACT);
			}
			else if(o.equals(btnTrash))
			{
				doActionFromToolBar(eSHOW.DELETECONTRACT);
			}
			
			else if(o.equals(btnCalcPayroll))
			{
				doActionFromToolBar(eSHOW.CALCPAYROLL);
			}
			else if(o.equals(btnReport))
			{
				doActionFromToolBar(eSHOW.CALCWORKDAYREPORT);
			}
			else if(o.equals(menuSystemExit))
			{
				
				doExit();
				
			}
			else if(o.equals(cboMonth))
			{
				doProcessMonthSelection();
			}
			else if(o.equals(cboYear))
			{
				doProcessYearSelection();
			}
			else if(o.equals(btnSaveTimeKeeping))
			{
				doProcessSaveTimeKeeping();
			}
			else if(o.equals(btnClose))
			{
				
				doExit();
				
			}
			else if(o.equals(btnSearch))
			{
				doSearch();
			}
			else if(o.equals(btnShowAll))
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
		/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		CTimeKeepingBookFrame ui=new CTimeKeepingBookFrame("TimeSheet Management");
		ui.doShow();
	}

}
