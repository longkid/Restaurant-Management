package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * @author Tu Thi Xuan Hien
 */
public class PayrollFrame extends JFrame implements ActionListener {

	private JTextField txtMonth = new JTextField();
	private JTextField txtYear = new JTextField();
	private JButton btnView = new JButton("View");
	private JButton btnClose = new JButton("Close");
	private JComboBox cboSelectEmployee = new JComboBox();
	private JRadioButton rdbtnViewAllEmployees = new JRadioButton(
			"View report of all Employees");
	private JRadioButton rdbtnViewOneEmployee = new JRadioButton(
			"View report of employee");

	// 2011-06-03 LH: This method is only used to test the functionality of this
	// frame. It'll be deleted at final version.
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PayrollFrame frame = new PayrollFrame();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PayrollFrame() {
		setResizable(false);
		setBounds(100, 100, 635, 349);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblPay = new JLabel("PAYROLL");
		lblPay.setHorizontalAlignment(SwingConstants.CENTER);
		lblPay.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblPay.setBounds(10, 0, 607, 52);
		contentPane.add(lblPay);

		JLabel lblMonth = new JLabel("Month");
		lblMonth.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMonth.setBounds(63, 63, 65, 28);
		contentPane.add(lblMonth);

		JLabel lblYear = new JLabel("Year");
		lblYear.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblYear.setBounds(354, 63, 65, 28);
		contentPane.add(lblYear);

		txtMonth.setBounds(135, 63, 138, 28);
		contentPane.add(txtMonth);
		txtMonth.setColumns(10);

		txtYear.setBounds(414, 63, 138, 28);
		contentPane.add(txtYear);
		txtYear.setColumns(10);

		btnView.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnView.setBounds(194, 255, 115, 32);
		contentPane.add(btnView);

		btnClose.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnClose.setBounds(354, 255, 106, 32);
		btnClose.addActionListener(this);
		contentPane.add(btnClose);

		cboSelectEmployee.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cboSelectEmployee.setBounds(306, 172, 246, 28);
		contentPane.add(cboSelectEmployee);

		rdbtnViewAllEmployees.setBounds(63, 127, 235, 28);
		contentPane.add(rdbtnViewAllEmployees);

		rdbtnViewOneEmployee.setBounds(63, 174, 235, 25);
		contentPane.add(rdbtnViewOneEmployee);

		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnViewAllEmployees);
		group.add(rdbtnViewOneEmployee);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object source = arg0.getSource();

		if (source == btnClose) {
			dispose();
		}
	}
}
