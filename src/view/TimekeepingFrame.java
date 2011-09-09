package view;

/*
 * @author Tu Thi Xuan Hien
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.Calendar;
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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

@SuppressWarnings("deprecation")
public class TimekeepingFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JMenuBar menuBar;
	private JMenu menuSystem;
	private JMenuItem menuSystemExit;
	private JToolBar toolBar;
	private JButton btnAddNewContract, btnModifyContract, btnCalcPayroll,
			btnReport, btnTrash, btnSearch, btnShowAll, btnSaveTimeKeeping,
			btnClose;
	private JTextField txtSearch;
	JPanel pnTree;
	JPanel pnTable;
	private DefaultMutableTreeNode root = null;
	private JTree myTree = null;
	private DefaultTableModel tblModelTimeKeeping, tblModelEmployee;
	private JTable tblTimeKeeping, tblEmployee;
	private JComboBox cboMonth, cboYear;

	public TimekeepingFrame(String strTitle) {
		setTitle(strTitle);
		createMenu();
		createToolBar();
		Container con = getContentPane();
		con.setLayout(new BorderLayout());
		con.add(toolBar, BorderLayout.NORTH);
		JPanel pnCenterGeneral = new JPanel();
		con.add(pnCenterGeneral, BorderLayout.CENTER);
		pnCenterGeneral.setLayout(new BorderLayout());

		pnTree = new JPanel();
		pnTree.setLayout(new BorderLayout());
		pnTree.setBackground(Color.RED);
		pnTree.setPreferredSize(new Dimension(400, 0));

		pnTable = new JPanel();
		pnTable.setBackground(Color.BLUE);
		pnTable.setLayout(new BorderLayout());

		JPanel pnChooseDate = new JPanel();
		pnChooseDate.setPreferredSize(new Dimension(0, 70));

		TitledBorder borderChooseDate = new TitledBorder(
				BorderFactory.createLineBorder(Color.BLUE),
				"Select Month & Year to check Time Keeping");
		borderChooseDate.setTitleColor(Color.RED);
		pnChooseDate.setBorder(borderChooseDate);
		pnTable.add(pnChooseDate, BorderLayout.NORTH);
		JLabel lblMonth = new JLabel("Month:");
		pnChooseDate.add(lblMonth);
		cboMonth = new JComboBox();
		for (int i = 1; i <= 12; i++) {
			cboMonth.addItem(i);
		}
		pnChooseDate.add(cboMonth);
		cboYear = new JComboBox();
		Calendar cal = Calendar.getInstance();
		java.util.Date date = cal.getTime();

		int d = date.getYear() + 1900;
		for (; d >= 2000; d--) {
			cboYear.addItem(d);
		}
		JLabel lblYear = new JLabel("Year:");
		pnChooseDate.add(lblYear);
		pnChooseDate.add(cboYear);
		btnSaveTimeKeeping = new JButton("Save");
		pnChooseDate.add(btnSaveTimeKeeping);
		btnSaveTimeKeeping.setIcon(new ImageIcon("images/savek.png"));
		tblModelTimeKeeping = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				if (column == 0 || column == 3) {
					return true;
				}
				return false;
			}
		};

		tblTimeKeeping = new JTable(tblModelTimeKeeping);
		JPanel pnTableTimeKeeping = new JPanel();
		pnTableTimeKeeping.setLayout(new BorderLayout());
		pnTableTimeKeeping.add(new JScrollPane(tblTimeKeeping),
				BorderLayout.CENTER);
		pnTable.add(pnTableTimeKeeping, BorderLayout.CENTER);

		TitledBorder borderTimeKeeping = new TitledBorder(
				BorderFactory.createLineBorder(Color.BLUE),
				"Time Keeping Table");
		pnTableTimeKeeping.setBorder(borderTimeKeeping);

		JSplitPane splt = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, pnTree,
				pnTable);
		splt.setOneTouchExpandable(true);
		pnCenterGeneral.add(splt, BorderLayout.CENTER);

		JPanel pnSearch = new JPanel();
		pnSearch.setLayout(new FlowLayout(FlowLayout.LEFT));
		JPanel pnInputSearch = new JPanel();
		pnInputSearch.setLayout(new BoxLayout(pnInputSearch, BoxLayout.Y_AXIS));
		pnSearch.add(pnInputSearch);
		JPanel pnSearchName = new JPanel();
		pnSearchName.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblSearch = new JLabel("Input Name's Employee :");
		JPanel pnInputName = new JPanel();
		pnInputName.setLayout(new FlowLayout(FlowLayout.LEFT));
		pnInputName.add(lblSearch);
		pnInputSearch.add(pnInputName);
		txtSearch = new JTextField(18);
		btnSearch = new JButton("Search");
		pnSearchName.add(txtSearch);
		pnInputSearch.add(pnSearchName);
		JPanel pnSearchButton = new JPanel();
		pnSearchButton.setLayout(new FlowLayout(FlowLayout.LEFT));
		pnSearchButton.add(btnSearch);
		btnShowAll = new JButton("Show All");
		ImageIcon iconAll = new ImageIcon("images/showall.png");
		btnShowAll.setIcon(iconAll);
		btnShowAll.setSize(20, 10);
		pnSearchButton.add(btnShowAll);
		pnInputSearch.add(pnSearchButton);
		pnSearchButton.setLayout(new FlowLayout(FlowLayout.LEFT));
		ImageIcon iconSearch = new ImageIcon("images/search.png");
		btnSearch.setIcon(iconSearch);
		TitledBorder borderSearch = new TitledBorder(
				BorderFactory.createLineBorder(Color.BLUE), "Searching Box");
		pnSearch.setBorder(borderSearch);

		tblModelEmployee = new DefaultTableModel();
		tblModelEmployee.addColumn("Full Name");
		tblModelEmployee.addColumn("Birthday");
		tblModelEmployee.addColumn("Sex");
		tblModelEmployee.addColumn("Had contract?");
		tblEmployee = new JTable(tblModelEmployee);
		tblEmployee.getColumn("Had contract?").setCellRenderer(
				new DefaultTableCellRenderer() {
					private static final long serialVersionUID = 1L;

					public Component getTableCellRendererComponent(
							JTable table, Object value, boolean isSelected,
							boolean hasFocus, int row, int column) {
						this.setForeground(Color.RED);
						return super.getTableCellRendererComponent(table,
								value, isSelected, hasFocus, row, column);
					}
				});

		TitledBorder borderTable = new TitledBorder(
				BorderFactory.createLineBorder(Color.RED), "Employee List");
		JPanel pnEmployeeTable = new JPanel();
		pnEmployeeTable.setLayout(new BorderLayout());
		pnEmployeeTable.setBorder(borderTable);
		JScrollPane sremployee = new JScrollPane(tblEmployee);
		pnEmployeeTable.add(sremployee, BorderLayout.CENTER);

		pnTree.add(pnEmployeeTable, BorderLayout.CENTER);
		pnTree.add(pnSearch, BorderLayout.NORTH);

		root = new DefaultMutableTreeNode("Contract History");
		DefaultTreeModel tree = new DefaultTreeModel(root);
		myTree = new JTree(tree);

		JPanel pnTreeDetail = new JPanel();
		pnTreeDetail.setLayout(new BorderLayout());
		pnTreeDetail.add(new JScrollPane(myTree), BorderLayout.CENTER);
		pnTreeDetail.setPreferredSize(new Dimension(400, 250));

		TitledBorder borderContract = new TitledBorder(
				BorderFactory.createLineBorder(Color.BLUE), "Contract History");
		pnTreeDetail.setBorder(borderContract);
		pnTree.add(pnTreeDetail, BorderLayout.SOUTH);
	}

	private void createMenu() {
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		menuSystem = new JMenu("System");
		ImageIcon icon = new ImageIcon("images/system.png");
		menuSystem.setIcon(icon);
		menuSystemExit = new JMenuItem("Close");
		icon = new ImageIcon("images/systemclose.png");
		menuSystemExit.setIcon(icon);
		menuBar.add(menuSystem);
		menuSystem.add(menuSystemExit);
	}

	private void createToolBar() {
		toolBar = new JToolBar("Contract Toolbar");
		btnAddNewContract = new JButton("New Contract");
		btnModifyContract = new JButton("Edit Contract");
		btnCalcPayroll = new JButton("Calc Payroll");
		btnReport = new JButton("Report");
		btnTrash = new JButton("Delete Contract");
		toolBar.add(btnAddNewContract);
		toolBar.addSeparator(new Dimension(20, 0));
		toolBar.add(btnModifyContract);
		toolBar.addSeparator(new Dimension(20, 0));
		toolBar.add(btnTrash);
		toolBar.addSeparator(new Dimension(20, 0));
		toolBar.add(btnCalcPayroll);

		btnClose = new JButton("Close");
		ImageIcon icon = new ImageIcon("images/customer.png");
		btnAddNewContract.setIcon(icon);

		icon = new ImageIcon("images/modify.png");
		btnModifyContract.setIcon(icon);
		icon = new ImageIcon("images/calc.png");
		btnCalcPayroll.setIcon(icon);
		icon = new ImageIcon("images/report.png");
		btnReport.setIcon(icon);
		icon = new ImageIcon("images/trash.png");
		btnTrash.setIcon(icon);

		icon = new ImageIcon("images/close.png");
		btnClose.setIcon(icon);
		toolBar.addSeparator(new Dimension(20, 0));
		JPanel pnSeparator = new JPanel();
		pnSeparator.setBackground(Color.LIGHT_GRAY);

		JLabel lblTitle = new JLabel("Time Keeping Management");
		Font font = new Font("Arial", Font.BOLD, 25);
		lblTitle.setFont(font);
		lblTitle.setForeground(Color.BLUE);
		pnSeparator.add(lblTitle);
		toolBar.add(pnSeparator);
		toolBar.addSeparator(new Dimension(20, 0));
		toolBar.add(btnClose);
	}

	public JMenuItem getMenuItemSystemExit() {
		return menuSystemExit;
	}

	public DefaultMutableTreeNode getRootNode() {
		return root;
	}

	public JTextField getTextFieldSearch() {
		return txtSearch;
	}

	public JComboBox getComboBoxMonth() {
		return cboMonth;
	}

	public JComboBox getComboBoxYear() {
		return cboYear;
	}

	public JButton getButtonAddNewContract() {
		return btnAddNewContract;
	}

	public JButton getButtonModifyContract() {
		return btnModifyContract;
	}

	public JButton getButtonCalcPayroll() {
		return btnCalcPayroll;
	}

	public JButton getButtonReport() {
		return btnReport;
	}

	public JButton getButtonTrash() {
		return btnTrash;
	}

	public JButton getButtonSearch() {
		return btnSearch;
	}

	public JButton getButtonShowAll() {
		return btnShowAll;
	}

	public JButton getButtonSaveTimeKeeping() {
		return btnSaveTimeKeeping;
	}

	public JButton getButtonClose() {
		return btnClose;
	}

	public JTable getTableTimeKeeping() {
		return tblTimeKeeping;
	}

	public JTable getTableEmployee() {
		return tblEmployee;
	}

	public DefaultTableModel getTableModelTimeKeeping() {
		return tblModelTimeKeeping;
	}

	public DefaultTableModel getTableModelEmployee() {
		return tblModelEmployee;
	}

	public JTree getTreeViewContract() {
		return myTree;
	}

	public JPanel getPanelLeft() {
		return pnTree;
	}

	public JPanel getPanelRight() {
		return pnTable;
	}
}
