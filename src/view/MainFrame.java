package view;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JEditorPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import controller.StaffController;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;

// 20110512 - LH: I'll add functionality for this frame.
/*
 * This is the frame which is displayed at the beginning.
 * The user can log in with username: admin and password: 111.
 * After logging in successfully, you can use all functionalities
 * of this application.
 *
 * @author Lam Ho
 */
public class MainFrame extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JMenu mnAdministrator = new JMenu("Administrator");
	private JMenuItem mntmCreateAUser = new JMenuItem("Create a user...");
	private JMenuItem mntmLogIn = new JMenuItem("Log in");
	private JMenuItem mntmSignOut = new JMenuItem("Sign out");
	private JMenuItem mntmExit = new JMenuItem("Exit");
	private JMenu mnManage = new JMenu("Manage");
	private JMenuItem mntmManageStaff = new JMenuItem("Manage Staff...");
	private JMenuItem mntmManageWaiters = new JMenuItem("Manage Waiters...");
	private JMenuItem mntmManageDayoffs = new JMenuItem("Manage Day-offs...");
	private JMenu mnTimekeeping = new JMenu("Timekeeping");
	private JMenuItem mntmTimekeeping = new JMenuItem("Timekeeping");
	
	private JMenuItem mnTimekeepingContractManagement=new JMenuItem("Contract Management");
	private JMenuItem mntmViewTimekeepingReport = new JMenuItem("View Report");
	private JMenu mnPayroll = new JMenu("Payroll");
	private JMenuItem mntmViewPayrollReport = new JMenuItem("View Report");
	private JMenu mnHelp = new JMenu("Help");
	private JMenuItem mntmHelp = new JMenuItem("Help...");
	private JMenuItem mntmAboutRestaurant = new JMenuItem("About Restaurant...");
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JEditorPane infoPane = new JEditorPane();
	private JScrollPane scrollPane;
	private JButton btnLogIn = new JButton("Log in");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setTitle("Dream Restaurant");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 450);
		setResizable(false);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		mnAdministrator.setEnabled(false);
		mnAdministrator.setMnemonic('A');
		menuBar.add(mnAdministrator);
		mnAdministrator.add(mntmCreateAUser);
		mnAdministrator.addSeparator();
		mnAdministrator.add(mntmLogIn);
		mnAdministrator.add(mntmSignOut);
		mnAdministrator.addSeparator();
		mnAdministrator.add(mntmExit);

		mnManage.setEnabled(false);
		mnManage.setMnemonic('M');
		menuBar.add(mnManage);
		mnManage.add(mntmManageStaff);
		mntmManageStaff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StaffController.singleton.visible();
			}
		});
		mnManage.add(mntmManageWaiters);
		mnManage.add(mntmManageDayoffs);

		mnTimekeeping.setEnabled(false);
		mnTimekeeping.setMnemonic('T');
		menuBar.add(mnTimekeeping);
		mnTimekeeping.add(mntmTimekeeping);
		mnTimekeeping.add(mntmViewTimekeepingReport);
		mnTimekeeping.addSeparator();
		mnTimekeeping.add(mnTimekeepingContractManagement);
		mntmViewTimekeepingReport.addActionListener(this);
		mnTimekeepingContractManagement.addActionListener(this);
		
		mnPayroll.setEnabled(false);
		mnPayroll.setMnemonic('P');
		menuBar.add(mnPayroll);
		mnPayroll.add(mntmViewPayrollReport);
		mntmViewPayrollReport.addActionListener(this);

		mnHelp.setMnemonic('H');
		menuBar.add(mnHelp);
		mnHelp.add(mntmHelp);
		mnHelp.addSeparator();
		mnHelp.add(mntmAboutRestaurant);

		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(12, 60, 90, 15);
		contentPane.add(lblUsername);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(12, 100, 90, 15);
		contentPane.add(lblPassword);

		usernameField = new JTextField();
		usernameField.setBounds(120, 58, 114, 19);
		contentPane.add(usernameField);
		usernameField.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(120, 98, 114, 19);
		passwordField.addActionListener(this);
		contentPane.add(passwordField);

		btnLogIn.setBounds(120, 138, 114, 25);
		btnLogIn.addActionListener(this);
		contentPane.add(btnLogIn);

		infoPane = createInfoPane();
		scrollPane = new JScrollPane(infoPane);
		scrollPane.setBounds(0, 0, getWidth() - 10, getHeight() - 10);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();

		if (source == passwordField || source == btnLogIn) { // Process the
																// password
			handleLogIn();
		} /*else if (source == mntmViewPayrollReport) {
			new PayrollFrame();
		} */else if (source == mntmViewTimekeepingReport) {
			new WorkingDaysReportFrame();
		}
		else if(source.equals(mnTimekeepingContractManagement))
		{
			CTimeKeepingBookFrame ui=new CTimeKeepingBookFrame("Contract Management");
			ui.doShow();
		}
	}

	private void handleLogIn() {
		char[] input = passwordField.getPassword();
		if (usernameField.getText().equals("admin") && isPasswordCorrect(input)) {
			JOptionPane.showMessageDialog(this, "Log in successfully");
			mnAdministrator.setEnabled(true);
			mnManage.setEnabled(true);
			mnTimekeeping.setEnabled(true);
			mnPayroll.setEnabled(true);
			mnHelp.setEnabled(true);
			displayInfoOnMainFrame();
		} else {
			JOptionPane.showMessageDialog(this,
					"Invalid username or password. Try again.",
					"Error Message", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void displayInfoOnMainFrame() {
		JPanel contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(scrollPane);
		validate();
	}

	private JEditorPane createInfoPane() {
		JEditorPane editorPane = new JEditorPane();
		editorPane.setEditable(false);
		URL infoURL = MainFrame.class.getResource("info.html");
		if (infoURL != null) {
			try {
				editorPane.setPage(infoURL);
			} catch (IOException e) {
				System.err.println("Attempted to read a bad URL: " + infoURL);
			}
		} else {
			System.err.println("Couldn't find file: info.html");
		}

		return editorPane;
	}

	private boolean isPasswordCorrect(char[] input) {
		boolean isCorrect = true;
		char[] correctPassword = { '1', '1', '1' };

		if (input.length != correctPassword.length) {
			isCorrect = false;
		} else {
			isCorrect = Arrays.equals(input, correctPassword);
		}

		return isCorrect;
	}
}
