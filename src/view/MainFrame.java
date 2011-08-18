package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JMenuItem;

import controller.CTimeKeepingBookController;
import controller.PositionController;
import controller.StaffController;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
	private JMenu mnManage = new JMenu("Manage");
	private JMenuItem mntmManageStaff = new JMenuItem("Manage Staff...");
	private JMenuItem mntmManagePosition = new JMenuItem("Manage Positions...");
	private JMenuItem mntmManageWaiters = new JMenuItem("Manage Waiters...");
	private JMenuItem mntmManageDayoffs = new JMenuItem("Manage Day-offs...");
	
	private JMenu mnTimekeeping = new JMenu("Timekeeping");
	private JMenuItem mnTimekeepingContractManagement=new JMenuItem("Contract Management");
		
	private JMenu mnHelp = new JMenu("Help");
	private JMenuItem mntmHelp = new JMenuItem("Help...");
	private JMenuItem mntmAboutRestaurant = new JMenuItem("About Restaurant...");
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JTextArea infoArea = new JTextArea();
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
		setBounds(100, 100, 700, 500);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		mnManage.setMnemonic('M');
		menuBar.add(mnManage);
		mntmManageStaff.addActionListener(this);		
		mntmManagePosition.addActionListener(this);
		mnManage.add(mntmManageStaff);
		mnManage.add(mntmManagePosition);
		mnManage.add(mntmManageWaiters);
		mnManage.add(mntmManageDayoffs);

		mnTimekeeping.setMnemonic('T');
		menuBar.add(mnTimekeeping);
		mnTimekeeping.addSeparator();
		mnTimekeeping.add(mnTimekeepingContractManagement);
		mnTimekeepingContractManagement.addActionListener(this);
		
		mnHelp.setMnemonic('H');
		menuBar.add(mnHelp);
		mnHelp.add(mntmHelp);
		mnHelp.addSeparator();
		mnHelp.add(mntmAboutRestaurant);

		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		/*JLabel lblUsername = new JLabel("Username");
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
		
		mnAdministrator.setEnabled(true);
		mnManage.setEnabled(true);
		mnTimekeeping.setEnabled(true);
		mnPosition.setEnabled(true);
		mnHelp.setEnabled(true);
		displayInfoOnMainFrame();*/
		infoArea.setEditable(false);
		infoArea.setLineWrap(true);
		infoArea.setWrapStyleWord(true);
		infoArea.setFont(new Font("Serif", Font.BOLD, 14));
        JScrollPane areaScrollPane = new JScrollPane(infoArea);
        areaScrollPane.setVerticalScrollBarPolicy(
                        JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        areaScrollPane.setPreferredSize(this.getPreferredSize());
        showInfo();
		contentPane.add(areaScrollPane, BorderLayout.CENTER);
		setLocationRelativeTo(null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();

		if (source == passwordField || source == btnLogIn) { // Process the
																// password
			handleLogIn();
		} else if (source.equals(mntmManageStaff)) {
			StaffController.singleton.visible();
		} else if (source.equals(mntmManagePosition)){
			PositionController positionController=new PositionController();
			positionController.doShow();
			
		}
		else if(source.equals(mnTimekeepingContractManagement))
		{
			CTimeKeepingBookController timeKeepingBookController=new CTimeKeepingBookController();
			timeKeepingBookController.doShow();
		}
	}

	private void handleLogIn() {
		char[] input = passwordField.getPassword();
		if (usernameField.getText().equals("admin") && isPasswordCorrect(input)) {
			JOptionPane.showMessageDialog(this, "Log in successfully");
			mnManage.setEnabled(true);
			mnTimekeeping.setEnabled(true);
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
	private void showInfo() {
		try {
			FileReader file = new FileReader("data/info.txt");
			BufferedReader buff = new BufferedReader(file);
			boolean eof = false;
			
			while (!eof) {
				String line = buff.readLine();
				if (line != null) {
					infoArea.append("\n" + line);
				} else {
					eof = true;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
