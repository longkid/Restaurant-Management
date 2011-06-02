package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.SwingConstants;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class PayRollFrame extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField txtMonth=new JTextField();
	private JTextField txtYear=new JTextField();
	private JButton btnView = new JButton("View");
	private JButton btnClose = new JButton("Close");
	private JComboBox cboSelectEmployee = new JComboBox();
	private JRadioButton rdbtnViewAllEmployee = new JRadioButton("View PayRoll report of all Employees");
	private JRadioButton rdbtnViewOneEmployee = new JRadioButton("View PayRoll report of employee");
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
		cboSelectEmployee.setBounds(398, 171, 126, 28);
		contentPane.add(cboSelectEmployee);
		
		
		rdbtnViewAllEmployee.setBounds(62, 127, 273, 28);
		contentPane.add(rdbtnViewAllEmployee);
		
		
		rdbtnViewOneEmployee.setBounds(63, 174, 233, 25);
		contentPane.add(rdbtnViewOneEmployee);
		
		ButtonGroup group=new ButtonGroup();
		group.add(rdbtnViewAllEmployee);
		group.add(rdbtnViewOneEmployee);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object source = arg0.getSource();
		
		if (source == btnClose) {
			dispose();
		}
	}
}
