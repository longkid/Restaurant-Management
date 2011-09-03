package view;

/**
 * Author: Le Duy Phong

 * Purpose of this class: this class is used to create form for adding or modifying employees.
 */
import java.awt.Color;
import java.awt.Font;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import model.Employee;
import model.Sex;
import model.Staff;

public class EmployeeDetailsFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField fullNameTextField;
	private JTextField birthdayTextField;
	private JTextField NoIdentityCardTextField;
	private JTextField issueDateText;
	private JTextField issuePlaceTextField;
	private JTextField permanentAddressTexField;
	private JComboBox certificateOfITcombobox;
	private JTextField educationTextField;
	private JComboBox emailComboBox;
	private JComboBox cellphoneComboBox;
	private JComboBox currAddressComboBox;
	private JComboBox bankAccountComboBox;
	private JComboBox diplomaComboBox;
	private JComboBox languageComboBox;
	private JTextField sexTextField;

	/**
	 * Create the frame.
	 */
	public EmployeeDetailsFrame() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 715, 430);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblBasicInformations = new JLabel("DETAILED INFORMATION");
		lblBasicInformations.setForeground(Color.BLUE);
		lblBasicInformations.setFont(new Font("Dialog", Font.BOLD, 14));
		lblBasicInformations.setBounds(272, 12, 195, 15);
		contentPane.add(lblBasicInformations);

		JLabel lblFullName = new JLabel("Full Name");
		lblFullName.setBounds(12, 47, 70, 15);
		contentPane.add(lblFullName);

		fullNameTextField = new JTextField();
		fullNameTextField.setEditable(false);
		fullNameTextField.setBounds(162, 45, 190, 19);
		contentPane.add(fullNameTextField);
		fullNameTextField.setColumns(10);

		JLabel lblNewLabel = new JLabel("Birthday");
		lblNewLabel.setBounds(390, 45, 70, 15);
		contentPane.add(lblNewLabel);

		birthdayTextField = new JTextField();
		birthdayTextField.setEditable(false);
		birthdayTextField.setBounds(494, 45, 202, 19);
		contentPane.add(birthdayTextField);
		birthdayTextField.setColumns(10);

		JLabel lblSex = new JLabel("Sex");
		lblSex.setBounds(12, 76, 70, 15);
		contentPane.add(lblSex);

		JLabel lblEmailAddress = new JLabel("Email");
		lblEmailAddress.setBounds(12, 136, 99, 15);
		contentPane.add(lblEmailAddress);

		JLabel lblCellphoneNumber = new JLabel("Cellphone");
		lblCellphoneNumber.setBounds(12, 165, 129, 15);
		contentPane.add(lblCellphoneNumber);

		JLabel lblNewLabel_1 = new JLabel("ID Card No.");
		lblNewLabel_1.setBounds(390, 76, 99, 15);
		contentPane.add(lblNewLabel_1);

		NoIdentityCardTextField = new JTextField();
		NoIdentityCardTextField.setEditable(false);
		NoIdentityCardTextField.setBounds(494, 74, 202, 19);
		contentPane.add(NoIdentityCardTextField);
		NoIdentityCardTextField.setColumns(10);

		JLabel lblIssueDate = new JLabel("Issued Date");
		lblIssueDate.setBounds(12, 107, 107, 15);
		contentPane.add(lblIssueDate);

		issueDateText = new JTextField();
		issueDateText.setEditable(false);
		issueDateText.setBounds(162, 105, 190, 19);
		contentPane.add(issueDateText);
		issueDateText.setColumns(10);

		JLabel lblIssuePlace = new JLabel("Issued Place");
		lblIssuePlace.setBounds(390, 107, 99, 15);
		contentPane.add(lblIssuePlace);

		issuePlaceTextField = new JTextField();
		issuePlaceTextField.setEditable(false);
		issuePlaceTextField.setBounds(494, 105, 202, 19);
		contentPane.add(issuePlaceTextField);
		issuePlaceTextField.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Permanent Address");
		lblNewLabel_2.setBounds(12, 194, 147, 15);
		contentPane.add(lblNewLabel_2);

		permanentAddressTexField = new JTextField();
		permanentAddressTexField.setEditable(false);
		permanentAddressTexField.setBounds(162, 192, 534, 19);
		contentPane.add(permanentAddressTexField);
		permanentAddressTexField.setColumns(10);

		JLabel lblCurrentAddress = new JLabel("Current Address");
		lblCurrentAddress.setBounds(12, 223, 138, 15);
		contentPane.add(lblCurrentAddress);

		JLabel lblAcountNo = new JLabel("Bank Account");
		lblAcountNo.setBounds(12, 252, 107, 15);
		contentPane.add(lblAcountNo);

		JLabel lblEducation = new JLabel("Education");
		lblEducation.setBounds(12, 281, 70, 15);
		contentPane.add(lblEducation);

		JLabel lblDiploma = new JLabel("Diploma");
		lblDiploma.setBounds(12, 310, 99, 15);
		contentPane.add(lblDiploma);

		JLabel lblForeignLanguage = new JLabel("Foreign Language");
		lblForeignLanguage.setBounds(12, 339, 129, 15);
		contentPane.add(lblForeignLanguage);

		JLabel lblCertificateOfIt = new JLabel("IT Certificate");
		lblCertificateOfIt.setBounds(12, 368, 99, 15);
		contentPane.add(lblCertificateOfIt);

		certificateOfITcombobox = new JComboBox();
		certificateOfITcombobox.setBounds(162, 363, 534, 24);
		contentPane.add(certificateOfITcombobox);

		emailComboBox = new JComboBox();
		emailComboBox.setBounds(162, 131, 534, 24);
		contentPane.add(emailComboBox);

		cellphoneComboBox = new JComboBox();
		cellphoneComboBox.setBounds(162, 160, 534, 24);
		contentPane.add(cellphoneComboBox);

		currAddressComboBox = new JComboBox();
		currAddressComboBox.setBounds(162, 218, 534, 24);
		contentPane.add(currAddressComboBox);

		bankAccountComboBox = new JComboBox();
		bankAccountComboBox.setBounds(162, 247, 534, 24);
		contentPane.add(bankAccountComboBox);

		educationTextField = new JTextField();
		educationTextField.setEditable(false);
		educationTextField.setBounds(162, 279, 534, 19);
		contentPane.add(educationTextField);
		educationTextField.setColumns(10);

		diplomaComboBox = new JComboBox();
		diplomaComboBox.setBounds(162, 305, 534, 24);
		contentPane.add(diplomaComboBox);

		languageComboBox = new JComboBox();
		languageComboBox.setBounds(162, 334, 534, 24);
		contentPane.add(languageComboBox);
		
		sexTextField = new JTextField();
		sexTextField.setEditable(false);
		sexTextField.setBounds(161, 74, 191, 19);
		contentPane.add(sexTextField);
		sexTextField.setColumns(10);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public void displayEmployee(int index) {
		Employee employee = Staff.getInstance().getEmployees().get(index);

		fullNameTextField.setText(employee.getFullName());
		birthdayTextField.setText(Staff.getDateFormat().format(employee
				.getBirthday()));
		sexTextField.setText(employee.getSex().equals(Sex.MALE) ? "Male" : "Female");
		
		Object[] objects = getReversedArray(employee.getEmails());
		emailComboBox.setModel(new DefaultComboBoxModel(objects));
		
		objects = getReversedArray(employee.getPhoneNumbers());
		cellphoneComboBox.setModel(new DefaultComboBoxModel(objects));
		
		NoIdentityCardTextField
				.setText(employee.getIdentityCard().getCardNum());
		issueDateText.setText(Staff.getDateFormat().format(employee
				.getIdentityCard().getIssuedDate()));
		issuePlaceTextField
				.setText(employee.getIdentityCard().getIssuedPlace());
		permanentAddressTexField.setText(employee.getPermanentAddress());
		
		objects = getReversedArray(employee.getTemporaryAddresses());
		currAddressComboBox.setModel(new DefaultComboBoxModel(objects));
		
		objects = getReversedArray(employee.getAccounts());
		bankAccountComboBox.setModel(new DefaultComboBoxModel(objects));
		
		educationTextField.setText(employee.getEducation());
		
		objects = getReversedArray(employee.getDiplomas());
		diplomaComboBox.setModel(new DefaultComboBoxModel(objects));
		
		objects = getReversedArray(employee.getLanguageCertificates());
		languageComboBox.setModel(new DefaultComboBoxModel(objects));
		
		objects = getReversedArray(employee.getItCertificates());
		certificateOfITcombobox.setModel(new DefaultComboBoxModel(objects));
	}
	
	<E> Object[] getReversedArray(List<E> list) {
		List<Object> objects = Arrays.asList(list.toArray());
		// reverse method affects the input argument
		// => we need to use this method on copied list
		Collections.reverse(objects);
		return objects.toArray();
	}
}