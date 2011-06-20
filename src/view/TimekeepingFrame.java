package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;

import model.Employee;
import model.Staff;

/*
 * This frame is used to record the present time of employee.
 * This recorded time will be used for calculating the salary of
 * all employees.
 *
 * @author Lam Ho
 */
public class TimekeepingFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JTextField fullNameField = new JTextField();
	private JTextField birthDayField = new JTextField();
	private JTextField positionField = new JTextField();
	private JTextField addressField = new JTextField();
	private JTextField phoneNumberField = new JTextField();
	private JTextField dateField = new JTextField();
	private JComboBox workingTimeList = new JComboBox();
	private DefaultTableModel tblModelEmployee = new DefaultTableModel();
	private JTable tblEmployeeList = new JTable(tblModelEmployee);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new TimekeepingFrame();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TimekeepingFrame() {
		setTitle("Timekeeping");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panelPersonalInfo = new JPanel();
		contentPane.add(panelPersonalInfo, BorderLayout.NORTH);
		panelPersonalInfo.setLayout(new BoxLayout(panelPersonalInfo, BoxLayout.Y_AXIS));
		TitledBorder tb = new TitledBorder(BorderFactory.createLineBorder(Color.blue), "Personal Information");
		panelPersonalInfo.setBorder(tb);
		
		int fieldSize = 40;
		JPanel panel1 = new JPanel();
		JLabel lblFullname = new JLabel("Full name", JLabel.RIGHT);
		panel1.add(lblFullname);
		fullNameField.setEditable(false);
		fullNameField.setColumns(fieldSize);
		panel1.add(fullNameField);
		panelPersonalInfo.add(panel1);
		
		JPanel panel2 = new JPanel();
		JLabel lblBirthday = new JLabel("Birthday", JLabel.RIGHT);
		panel2.add(lblBirthday);
		birthDayField.setEditable(false);
		birthDayField.setColumns(fieldSize);
		panel2.add(birthDayField);
		panelPersonalInfo.add(panel2);
		
		JPanel panel3 = new JPanel();
		JLabel lblPosition = new JLabel("Position", JLabel.RIGHT);
		panel3.add(lblPosition);
		positionField.setEditable(false);
		positionField.setColumns(fieldSize);
		panel3.add(positionField);
		panelPersonalInfo.add(panel3);
		
		JPanel panel4 = new JPanel();
		JLabel lblAddress = new JLabel("Address", JLabel.RIGHT);
		panel4.add(lblAddress);
		addressField.setEditable(false);
		addressField.setColumns(fieldSize);
		panel4.add(addressField);
		panelPersonalInfo.add(panel4);
		
		JPanel panel5 = new JPanel();
		JLabel lblPhone = new JLabel("Phone", JLabel.RIGHT);
		panel5.add(lblPhone);
		phoneNumberField.setEditable(false);
		phoneNumberField.setColumns(fieldSize);
		panel5.add(phoneNumberField);
		panelPersonalInfo.add(panel5);
		
		lblBirthday.setPreferredSize(lblFullname.getPreferredSize());
		lblPosition.setPreferredSize(lblFullname.getPreferredSize());
		lblAddress.setPreferredSize(lblFullname.getPreferredSize());
		lblPhone.setPreferredSize(lblFullname.getPreferredSize());
		
		JPanel panel6 = new JPanel();
		panelPersonalInfo.add(panel6);
		JLabel lblDate = new JLabel("Date");
		panel6.add(lblDate);
		dateField = new JTextField();
		panel6.add(dateField);
		dateField.setColumns(10);
		updateDateField();
		JLabel lblWorkingTime = new JLabel("Working Time");
		panel6.add(lblWorkingTime);
		String[] timeStrings = { "Day shift", "Night shift", "Office hours" };
        workingTimeList = new JComboBox(timeStrings);
		panel6.add(workingTimeList);
		JButton btnPresent = new JButton("Present");
		panel6.add(btnPresent);
		
		tblModelEmployee.addColumn("Full name");
		tblModelEmployee.addColumn("Position");
		tblModelEmployee.addColumn("Recorded time");
		loadEmployeesList();
		tblEmployeeList.addMouseListener(new MyMouseListener());
		JScrollPane scrollPane = new JScrollPane(tblEmployeeList);
		contentPane.add(scrollPane, BorderLayout.CENTER);
		TitledBorder tb1 = new TitledBorder(BorderFactory.createLineBorder(Color.red), "List of employee");
		scrollPane.setBorder(tb1);
		setVisible(true);
	}

	private void updateDateField() {
		Calendar time = Calendar.getInstance();
		int year = time.get(Calendar.YEAR);
		int month = time.get(Calendar.MONTH) + 1;
		int day = time.get(Calendar.DAY_OF_MONTH);
		String yyyy = String.valueOf(year);
		String mm = (month < 10) ? ("0" + String.valueOf(month)) : String.valueOf(month);
		String dd = (day < 10) ? ("0" + String.valueOf(day)) : String.valueOf(day);
		String date = yyyy + "-" + mm + "-" + dd;
		dateField.setText(date);
	}
	
	/*
	 * 20110618 - LH: At this time, I just load all employees and don't
	 * care about the working time. Later on, I'll load the employees
	 * who work in some working time.
	 */
	private void loadEmployeesList() {
		Staff staff = Staff.getInstance();
		List<Employee> employees = staff.getEmployees();
		tblModelEmployee.setRowCount(0);
		if (employees != null) {
			for (Employee emp : employees) {
				tblModelEmployee.addRow(emp.getVector());
			}
		}
		showPersonalInfo(employees.get(0));
	}
	
	private void showPersonalInfo(Employee employee) {
		fullNameField.setText(employee.getFullName());
		birthDayField.setText(Staff.dateFormat.format(employee.getBirthday()));
		// 20110618 - LH: I'm waiting for the answer email of Prof.
		//positionField.setText(null)
		addressField.setText(employee.getPermanentAddress());
		phoneNumberField.setText(employee.getLatestPhoneNumber().getPhoneNumber());
	}
	
	private class MyMouseListener extends MouseAdapter {
		public void mouseClicked(MouseEvent arg0) {
			int row = tblEmployeeList.getSelectedRow();
			List<Employee> employees = Staff.getInstance().getEmployees();
			Employee employee = employees.get(row);
			showPersonalInfo(employee);
		}
	}
}
