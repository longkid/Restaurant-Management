package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JButton;

import controller.StaffController;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StaffFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table_1;
	private JButton btnUpdate;
	private JButton btnViewDetails;

	/**
	 * Create the frame.
	 */
	public StaffFrame() {
		setTitle("Staff Management");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 560, 410);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setTable_1(new JTable());
		
		getTable_1().setBounds(12, 12, 487, 323);
		contentPane.add(getTable_1());

		JButton btnCreate = new JButton("Add an Employee");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StaffController.singleton.create();
			}
		});
		btnCreate.setBounds(12, 345, 155, 25);
		contentPane.add(btnCreate);

		btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StaffController.singleton.update();
			}
		});
		btnUpdate.setBounds(190, 345, 90, 25);
		contentPane.add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StaffController.singleton.delete();
			}
		});
		btnDelete.setBounds(305, 345, 90, 25);
		contentPane.add(btnDelete);
		
		btnViewDetails = new JButton("View Details");
		btnViewDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StaffController.singleton.viewDetails();
			}
		});
		btnViewDetails.setBounds(415, 345, 130, 25);
		contentPane.add(btnViewDetails);
		setLocationRelativeTo(null);
	}

	public void setTable_1(JTable table_1) {
		this.table_1 = table_1;
		table_1.setColumnSelectionAllowed(true);
		table_1.setCellSelectionEnabled(true);
	}

	public JTable getTable_1() {
		return table_1;
	}
}
