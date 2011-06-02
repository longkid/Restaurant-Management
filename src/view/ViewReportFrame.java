package view;
/*
 * @author Tu Thi Xuan Hien
 */

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;

public class ViewReportFrame extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField txtMonth=new JTextField();
	private JTextField txtFrom = new JTextField();
	private JTextField txtTo = new JTextField();
	private JTextField txtSummarizeMonth = new JTextField();
	private JRadioButton rdbtnEmployeeName = new JRadioButton("Employee name");
	private JComboBox cboEmployeeName = new JComboBox();
	private JRadioButton rdbtnFrom = new JRadioButton("From");
	private JRadioButton rdbtnSummarize = new JRadioButton("Summarize the working day for all in the month");
	private JButton btnCloseForm = new JButton("Close form");
	private JButton btnView = new JButton("View");
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewReportFrame frame = new ViewReportFrame();
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
	public ViewReportFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 662, 346);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblViewReport = new JLabel("View Report");
		lblViewReport.setHorizontalAlignment(SwingConstants.CENTER);
		lblViewReport.setFont(new Font("Cambria", Font.BOLD, 28));
		lblViewReport.setBounds(12, 13, 648, 44);
		contentPane.add(lblViewReport);
		
		
		rdbtnEmployeeName.setFont(new Font("Cambria", Font.PLAIN, 16));
		rdbtnEmployeeName.setBounds(41, 86, 149, 25);
		contentPane.add(rdbtnEmployeeName);
		
		
		cboEmployeeName.setBounds(198, 88, 149, 22);
		contentPane.add(cboEmployeeName);
		
		
		txtMonth.setBounds(447, 86, 137, 24);
		contentPane.add(txtMonth);
		txtMonth.setColumns(10);
		
		JLabel lblMonth = new JLabel("Month");
		lblMonth.setFont(new Font("Cambria", Font.PLAIN, 16));
		lblMonth.setBounds(379, 90, 56, 16);
		contentPane.add(lblMonth);
		
		
		rdbtnFrom.setFont(new Font("Cambria", Font.PLAIN, 16));
		rdbtnFrom.setBounds(41, 134, 127, 25);
		contentPane.add(rdbtnFrom);
		
		
		txtFrom.setBounds(198, 135, 149, 25);
		contentPane.add(txtFrom);
		txtFrom.setColumns(10);
		
		JLabel lblTo = new JLabel("To");
		lblTo.setFont(new Font("Cambria", Font.PLAIN, 16));
		lblTo.setBounds(379, 138, 56, 16);
		contentPane.add(lblTo);
		
		
		txtTo.setBounds(447, 135, 137, 25);
		contentPane.add(txtTo);
		txtTo.setColumns(10);
		
		
		rdbtnSummarize.setFont(new Font("Cambria", Font.PLAIN, 16));
		rdbtnSummarize.setBounds(43, 184, 374, 25);
		contentPane.add(rdbtnSummarize);
		
		
		txtSummarizeMonth.setBounds(447, 185, 137, 25);
		contentPane.add(txtSummarizeMonth);
		txtSummarizeMonth.setColumns(10);
		ButtonGroup group=new ButtonGroup();
		group.add(rdbtnEmployeeName);
		group.add(rdbtnFrom);
		group.add(rdbtnSummarize);
		
		
		btnView.setFont(new Font("Cambria", Font.PLAIN, 16));
		btnView.setBounds(198, 250, 127, 25);		
		contentPane.add(btnView);
		
		
		btnCloseForm.setFont(new Font("Cambria", Font.PLAIN, 16));
		btnCloseForm.setBounds(337, 250, 127, 25);
		btnCloseForm.addActionListener(this);
		contentPane.add(btnCloseForm);
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object source=arg0.getSource();
		if(source==btnCloseForm){
			dispose();
		}
			
	}
}
