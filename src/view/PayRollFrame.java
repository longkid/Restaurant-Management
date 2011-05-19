package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JComboBox;

/*
 * @author Tu Thi Xuan Hien
 */
public class PayRollFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtMonth;
	private JTextField txtYear;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PayRollFrame frame = new PayRollFrame();
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
	public PayRollFrame() {
		setTitle("Payroll");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 635, 349);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPay = new JLabel("PAYROLL");
		lblPay.setHorizontalAlignment(SwingConstants.CENTER);
		lblPay.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblPay.setBounds(10, 0, 639, 52);
		contentPane.add(lblPay);
		
		JLabel lblMonth = new JLabel("Month");
		lblMonth.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMonth.setBounds(63, 63, 65, 28);
		contentPane.add(lblMonth);
		
		JLabel lblYear = new JLabel("Year");
		lblYear.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblYear.setBounds(354, 63, 65, 28);
		contentPane.add(lblYear);
		
		txtMonth = new JTextField();
		txtMonth.setBounds(135, 63, 138, 28);
		contentPane.add(txtMonth);
		txtMonth.setColumns(10);
		
		txtYear = new JTextField();
		txtYear.setBounds(414, 63, 138, 28);
		contentPane.add(txtYear);
		txtYear.setColumns(10);
		
		JRadioButton rdButtonAllEmployees = new JRadioButton("View Payroll of all Employees");
		rdButtonAllEmployees.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rdButtonAllEmployees.setBounds(91, 129, 285, 23);
		contentPane.add(rdButtonAllEmployees);
		
		JRadioButton rdButtonOneEmployee = new JRadioButton("View Payroll Report of Employees");
		rdButtonOneEmployee.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rdButtonOneEmployee.setBounds(91, 177, 257, 23);
		contentPane.add(rdButtonOneEmployee);
		
		JButton btnView = new JButton("View");
		btnView.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnView.setBounds(194, 255, 115, 32);
		contentPane.add(btnView);
		
		JButton btnClose = new JButton("Close");
		btnClose.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnClose.setBounds(354, 255, 106, 32);
		contentPane.add(btnClose);
		
		JComboBox cboSelectEmployee = new JComboBox();
		cboSelectEmployee.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cboSelectEmployee.setBounds(359, 174, 126, 28);
		contentPane.add(cboSelectEmployee);
	}
}
