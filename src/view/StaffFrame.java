package view;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
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
	private JTable staffTable;
	private JButton btnCreate;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JButton btnViewDetails;

	/**
	 * Create the frame.
	 */
	public StaffFrame() {
		setTitle("Staff Management");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setSize(560, 410);
		//setBounds(100, 100, 560, 410);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		staffTable = new JTable();
		JScrollPane scrollPane = new JScrollPane(staffTable);
		//scrollPane.setBounds(12, 0, 533, 335);
		contentPane.add(scrollPane);

		btnCreate = new JButton("Add an Employee");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StaffController.singleton.create();
			}
		});
		JPanel buttonPanel = new JPanel();
		//btnCreate.setBounds(12, 345, 155, 25);
		//contentPane.add(btnCreate);
		buttonPanel.add(btnCreate);

		btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StaffController.singleton.update();
			}
		});
		//btnUpdate.setBounds(190, 345, 90, 25);
		//contentPane.add(btnUpdate);
		buttonPanel.add(btnUpdate);
		
		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StaffController.singleton.delete();
			}
		});
		//btnDelete.setBounds(305, 345, 90, 25);
		//contentPane.add(btnDelete);
		buttonPanel.add(btnDelete);
		
		btnViewDetails = new JButton("View Details");
		btnViewDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StaffController.singleton.viewDetails();
			}
		});
		//btnViewDetails.setBounds(415, 345, 130, 25);
		//contentPane.add(btnViewDetails);
		buttonPanel.add(btnViewDetails);
		contentPane.add(buttonPanel);
		setLocationRelativeTo(null);
	}

	public void setStaffTable(JTable staffTable) {
		this.staffTable = staffTable;
		staffTable.setColumnSelectionAllowed(true);
		staffTable.setCellSelectionEnabled(true);
	}

	public JTable getStaffTable() {
		return staffTable;
	}
}
