package view;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.awt.Dimension;

/*
 * @author Lam Ho
 */
public class HelpFrame extends JFrame implements TreeSelectionListener {
	private static final String INIT_HELP = "This guide will show you how to manage staff, positions in restaurant, and contracts of all employees. " +
					"You are also guided through the timekeeping process.";
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JEditorPane htmlPane;
	private JTree tree;

	public HelpFrame() {
		super("User Guide");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// Create the nodes.
		DefaultMutableTreeNode top = new DefaultMutableTreeNode("User Guide");
		createNodes(top);

		// Create a tree that allows one selection at a time.
		tree = new JTree(top);
		tree.getSelectionModel().setSelectionMode(
				TreeSelectionModel.SINGLE_TREE_SELECTION);
		tree.addTreeSelectionListener(this);
		JScrollPane treeView = new JScrollPane(tree);

		htmlPane = new JEditorPane();
		htmlPane.setEditable(false);
		htmlPane.setContentType("text/html");
		htmlPane.setText(INIT_HELP);
		JScrollPane htmlView = new JScrollPane(htmlPane);

		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		splitPane.setLeftComponent(treeView);
		splitPane.setRightComponent(htmlView);

		splitPane.setDividerLocation(350);
		splitPane.setPreferredSize(new Dimension(700, 350));
		setContentPane(splitPane);
		pack();
		setVisible(true);
	}

	public void valueChanged(TreeSelectionEvent e) {
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree
				.getLastSelectedPathComponent();

		if (node == null)
			return;

		Object nodeInfo = node.getUserObject();
		if (node.isRoot()) {
			htmlPane.setText(INIT_HELP);
		} else {
			HelpInfo hInfo = (HelpInfo) nodeInfo;
			if (hInfo.getInfo() != null) {
				htmlPane.setText(hInfo.getInfo());
			} else {
				htmlPane.setText("");
			}
		}
	}

	private void createNodes(DefaultMutableTreeNode top) {
		DefaultMutableTreeNode category = null;
		DefaultMutableTreeNode item = null;
		HelpInfo hInfo = null;
		String info = null;

		hInfo = new HelpInfo("How to login the system?");
		info = "The user can create a username and a password to login the system. The password may be empty or contain any characters with non-zero length. " +
				"Then you click the button <em><strong>Login</em></strong> to use the application. You can also ask the system to remember your password by " +
				"checking the corresponding checkbox. Later on, you need to provide your username and password whenever you want to use the application. " +
				"If your login information is different from the information you provided, an error message &quot;Login Failed&quot; will be appeared.";
		hInfo.setInfo(info);
		category = new DefaultMutableTreeNode(hInfo);
		top.add(category);
		
		hInfo = new HelpInfo("How to manage staff?");
		info = "To use this functionality, you choose <em><strong>Manage Staff</em></strong> item in <em><strong>Manage</em></strong> menu.";
		hInfo.setInfo(info);
		category = new DefaultMutableTreeNode(hInfo);
		top.add(category);

		hInfo = new HelpInfo("Add a new employee", "data/addemployee.html");
		item = new DefaultMutableTreeNode(hInfo);
		category.add(item);

		hInfo = new HelpInfo("Update an existed employee",
				"data/editemployee.html");
		item = new DefaultMutableTreeNode(hInfo);
		category.add(item);

		hInfo = new HelpInfo("Delete an employee", "data/deleteemployee.html");
		item = new DefaultMutableTreeNode(hInfo);
		category.add(item);

		hInfo = new HelpInfo("View details of an employee",
				"data/viewemployee.html");
		item = new DefaultMutableTreeNode(hInfo);
		category.add(item);

		hInfo = new HelpInfo("How to manage positions in restaurant?");
		info = "For the positions in restaurant, you cannot add and delete some position. "
				+ "The reason is that all positions are necessary in the restaurant staff. "
				+ "Therefore, you can just update information of one position by choosing "
				+ "<em><strong>Manage Positions</em></strong> item in <em><strong>Manage</em></strong> menu.";
		hInfo.setInfo(info);
		category = new DefaultMutableTreeNode(hInfo);
		top.add(category);

		hInfo = new HelpInfo("Update a position", "data/updateposition.html");
		item = new DefaultMutableTreeNode(hInfo);
		category.add(item);

		hInfo = new HelpInfo(
				"How to manage contracts of all employees in restaurant?");
		info = "To use this functionality, you choose <em><strong>Contract Management</em></strong> item in <em><strong>Timekeeping</em></strong> menu.";
		hInfo.setInfo(info);
		category = new DefaultMutableTreeNode(hInfo);
		top.add(category);

		hInfo = new HelpInfo("Add a new contract", "data/addcontract.html");
		item = new DefaultMutableTreeNode(hInfo);
		category.add(item);

		hInfo = new HelpInfo("Edit an existed contract",
				"data/editcontract.html");
		item = new DefaultMutableTreeNode(hInfo);
		category.add(item);

		hInfo = new HelpInfo("Delete a contract", "data/deletecontract.html");
		item = new DefaultMutableTreeNode(hInfo);
		category.add(item);

		hInfo = new HelpInfo("How to do the timekeeping and view the payroll?");
		info = "To use this functionality, you choose <em><strong>Contract Management</em></strong> item in <em><strong>Timekeeping</em></strong> menu.";
		hInfo.setInfo(info);
		category = new DefaultMutableTreeNode(hInfo);
		top.add(category);

		hInfo = new HelpInfo("Do the timekeeping", "data/timekeeping.html");
		item = new DefaultMutableTreeNode(hInfo);
		category.add(item);

		hInfo = new HelpInfo("View the payroll", "data/payroll.html");
		item = new DefaultMutableTreeNode(hInfo);
		category.add(item);

		hInfo = new HelpInfo("Options");
		category = new DefaultMutableTreeNode(hInfo);
		top.add(category);

		hInfo = new HelpInfo("Change the date format", "data/dateformat.html");
		item = new DefaultMutableTreeNode(hInfo);
		category.add(item);
	}

	private class HelpInfo {
		public String name;
		public String filePath;
		public String info;

		public HelpInfo(String name) {
			this.name = name;
			filePath = null;
			info = null;
		}

		public HelpInfo(String name, String filePath) {
			this.name = name;
			this.filePath = filePath;
			if (filePath != null) {
				info = getInfoFromFile();
			}
		}

		public String getInfoFromFile() {
			String str = "";
			FileReader file;
			try {
				file = new FileReader(filePath);
				BufferedReader buff = new BufferedReader(file);
				boolean eof = false;
				while (!eof) {
					String line = buff.readLine();
					if (line == null) {
						eof = true;
					} else {
						str += line;
					}
				}
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			return str;
		}

		public String getInfo() {
			return info;
		}

		public void setInfo(String info) {
			this.info = info;
		}

		@Override
		public String toString() {
			return name;
		}
	}
}
