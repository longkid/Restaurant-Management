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
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		staffTable = new JTable();
		JScrollPane scrollPane = new JScrollPane(staffTable);
		contentPane.add(scrollPane);

		btnCreate = new JButton("Add an Employee");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StaffController.singleton.create();
			}
		});
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(btnCreate);

		btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StaffController.singleton.update();
			}
		});
		buttonPanel.add(btnUpdate);
		
		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StaffController.singleton.delete();
			}
		});
		buttonPanel.add(btnDelete);
		
		btnViewDetails = new JButton("View Details");
		btnViewDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StaffController.singleton.viewDetails();
			}
		});
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
