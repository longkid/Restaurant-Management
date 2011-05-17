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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

// 20110512 - LH: I'll add functionality for this frame.
/*
 * This is the frame which is displayed at the beginning.
 * The user can log in with username: admin and password: 12345.
 * After logging in successfully, you can use all functionalities
 * of this application.
 *
 * @author Lam Ho
 */
public class MainFrame extends JFrame implements ActionListener {

	private static String LOGIN = "Log in";

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
	private JMenuItem mntmViewReport = new JMenuItem("View Report");
	private JMenu mnPayroll = new JMenu("Payroll");
	private JMenuItem mntmViewReport_1 = new JMenuItem("View Report");
	private JMenu mnHelp = new JMenu("Help");
	private JMenuItem mntmHelp = new JMenuItem("Help...");
	private JMenuItem mntmAboutRestaurant = new JMenuItem("About Restaurant...");
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JEditorPane infoPane = new JEditorPane();
	private JScrollPane scrollPane;

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
		setBounds(100, 100, 600, 400);
		setResizable(false);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		mnAdministrator.setEnabled(false);
		menuBar.add(mnAdministrator);

		mnAdministrator.add(mntmCreateAUser);
		mnAdministrator.addSeparator();
		mnAdministrator.add(mntmLogIn);
		mnAdministrator.add(mntmSignOut);
		mnAdministrator.addSeparator();
		mnAdministrator.add(mntmExit);

		mnManage.setEnabled(false);
		menuBar.add(mnManage);
		mnManage.add(mntmManageStaff);
		mnManage.add(mntmManageWaiters);
		mnManage.add(mntmManageDayoffs);

		mnTimekeeping.setEnabled(false);
		menuBar.add(mnTimekeeping);
		mnTimekeeping.add(mntmTimekeeping);
		mnTimekeeping.add(mntmViewReport);

		mnPayroll.setEnabled(false);
		menuBar.add(mnPayroll);
		mnPayroll.add(mntmViewReport_1);

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
		passwordField.setActionCommand("Log in");
		passwordField.addActionListener(this);
		contentPane.add(passwordField);

		JButton btnLogIn = new JButton("Log in");
		btnLogIn.setBounds(120, 138, 114, 25);
		btnLogIn.setActionCommand("Log in");
		btnLogIn.addActionListener(this);
		contentPane.add(btnLogIn);
		
		infoPane = createInfoPane();
		scrollPane = new JScrollPane(infoPane);
		scrollPane.setSize(getSize());
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if (LOGIN.equals(cmd)) { // Process the password
			char[] input = passwordField.getPassword();
			if (usernameField.getText().equals("admin")
					&& isPasswordCorrect(input)) {
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

	}

	private void displayInfoOnMainFrame() {
		JPanel contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(scrollPane);
		validate();
	}

	private JEditorPane createInfoPane() {
		JEditorPane editorPane = new JEditorPane("application/rtf", "");
		try {
			editorPane.read(new FileInputStream("data/info.rtf"), null);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		editorPane.setEditable(false);
		return editorPane;
	}

	private boolean isPasswordCorrect(char[] input) {
		boolean isCorrect = true;
		char[] correctPassword = { '1', '2', '3', '4', '5' };

		if (input.length != correctPassword.length) {
			isCorrect = false;
		} else {
			isCorrect = Arrays.equals(input, correctPassword);
		}

		return isCorrect;

	}
}
