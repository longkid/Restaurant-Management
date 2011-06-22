package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.Employee;
import model.Staff;
import javax.swing.JButton;

import controller.StaffController;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StaffFrame extends JFrame {
	private JPanel contentPane;
	//private JTable table;
	private JTable table_1;
	private JButton btnUpdate;

	

	/**
	 * Create the frame.
	 */
	public StaffFrame() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 513, 402);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

//		table = new JTable();
//		table.setBounds(55, 111, 1, 1);
//		contentPane.add(table);

		setTable_1(new JTable());
		
		getTable_1().setBounds(12, 12, 487, 323);
		contentPane.add(getTable_1());

		JButton btnCreate = new JButton("Add new Employee");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StaffController.singleton.create();
			}
		});
		btnCreate.setBounds(12, 345, 113, 25);
		contentPane.add(btnCreate);

		btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StaffController.singleton.update();
			}
		});
		btnUpdate.setBounds(132, 345, 97, 25);
		contentPane.add(btnUpdate);
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
