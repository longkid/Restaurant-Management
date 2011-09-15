package view;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ButtonGroup;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JMenuItem;

import model.Staff;

import controller.PositionController;
import controller.StaffController;
import controller.TimeKeepingController;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;

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
	private JMenuItem mnTimekeepingContractManagement = new JMenuItem(
			"Contract Management");

	private JMenu mnHelp = new JMenu("Help");
	private JMenuItem mntmHelp = new JMenuItem("Help...");
	private JTextArea infoArea = new JTextArea();
	private JMenu mnOptions = new JMenu("Options");
	private JMenu mnDateFormat = new JMenu("Date Format");
	private JRadioButtonMenuItem mntmYyyymmdd = new JRadioButtonMenuItem("yyyy-mm-dd");
	private JRadioButtonMenuItem mntmDdmmyyyy = new JRadioButtonMenuItem("dd-mm-yyyy");

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

		mnOptions.setMnemonic('O');
		menuBar.add(mnOptions);
		mnOptions.add(mnDateFormat);
		mnDateFormat.add(mntmYyyymmdd);
		mnDateFormat.add(mntmDdmmyyyy);
		mntmYyyymmdd.setSelected(true);
		ButtonGroup group = new ButtonGroup();
		group.add(mntmYyyymmdd);
		group.add(mntmDdmmyyyy);
		mntmYyyymmdd.addActionListener(this);
		mntmDdmmyyyy.addActionListener(this);

		mnHelp.setMnemonic('H');
		menuBar.add(mnHelp);
		mnHelp.add(mntmHelp);
		mntmHelp.addActionListener(this);

		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		infoArea.setEditable(false);
		infoArea.setLineWrap(true);
		infoArea.setWrapStyleWord(true);
		infoArea.setFont(new Font("Serif", Font.BOLD, 14));
		JScrollPane areaScrollPane = new JScrollPane(infoArea);
		areaScrollPane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		areaScrollPane.setPreferredSize(this.getPreferredSize());
		showInfo();
		contentPane.add(areaScrollPane, BorderLayout.CENTER);
		setLocationRelativeTo(null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();

		if (source.equals(mntmManageStaff)) {
			StaffController.singleton.visible();
		} else if (source.equals(mntmManagePosition)) {
			PositionController positionController = new PositionController();
			positionController.doShow();
		} else if (source.equals(mnTimekeepingContractManagement)) {
			TimeKeepingController timeKeepingBookController = new TimeKeepingController();
			timeKeepingBookController.doShow();
		} else if (source.equals(mntmYyyymmdd)) {
			Staff.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
		} else if (source.equals(mntmDdmmyyyy)) {
			Staff.setDateFormat(new SimpleDateFormat("dd-MM-yyyy"));
		} else if (source.equals(mntmHelp)) {
			new HelpFrame();
		}
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
}
