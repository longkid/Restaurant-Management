package view;

/**
 * Author: Le Duy Phong

 * Purpose of this class: this class is used to create form for adding or modifying employees.
 */
import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

import controller.EmployeeController;
import model.Staff;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.List;

public class EmployeeFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static List<String> ITCertificateItems = Arrays.asList("",
			"Level A", "Level B", "Level C");
	public static List<String> EducationItems = Arrays.asList("<5/12", "5/12",
			"6/12", "7/12", "8/12", "9/12", "10/12", "11/12", "12/12");
	private JPanel contentPane;
	private JTextField fullNameTextField;
	private JTextField birthdayTextField;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField emailAddressTextField;
	private JTextField cellPhoneNumberTextField;
	private JTextField NoIdentityCardTextField;
	private JTextField issueDateText;
	private JTextField issuePlaceTextField;
	private JTextField permanentAddressTexField;
	private JTextField currentAddressTextField;
	private JTextField accountNoTextField;
	private JTextField nameOfBankTextField;
	private JTextField diplomaTextField;
	private JRadioButton sexradioMale;
	private JRadioButton sexradioFemale;
	private JComboBox educationComboBox;
	private JComboBox certificateOfITcombobox;
	private JTextField foreignLanguageTextField;

	/**
	 * Create the frame.
	 */
	public EmployeeFrame() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 725, 427);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblBasicInformations = new JLabel("BASIC INFORMATION");
		lblBasicInformations.setForeground(Color.BLUE);
		lblBasicInformations.setFont(new Font("Dialog", Font.BOLD, 14));
		lblBasicInformations.setBounds(272, 12, 158, 15);
		contentPane.add(lblBasicInformations);

		JLabel lblFullName = new JLabel("Full Name (*)");
		lblFullName.setBounds(12, 47, 138, 15);
		contentPane.add(lblFullName);

		fullNameTextField = new JTextField();
		fullNameTextField.setBounds(177, 43, 190, 19);
		contentPane.add(fullNameTextField);
		fullNameTextField.setColumns(10);

		JLabel lblNewLabel = new JLabel("Birthday (*)");
		lblNewLabel.setBounds(381, 47, 113, 15);
		contentPane.add(lblNewLabel);

		birthdayTextField = new JTextField();
		birthdayTextField.setText(Staff.getDateFormat().toPattern());
		birthdayTextField.setBounds(503, 43, 202, 19);
		contentPane.add(birthdayTextField);
		birthdayTextField.setColumns(10);

		JLabel lblSex = new JLabel("Sex (*)");
		lblSex.setBounds(12, 76, 70, 15);
		contentPane.add(lblSex);

		sexradioMale = new JRadioButton("Male");
		sexradioMale.setSelected(true);
		buttonGroup.add(sexradioMale);
		sexradioMale.setBounds(177, 70, 70, 23);
		contentPane.add(sexradioMale);

		sexradioFemale = new JRadioButton("Female");
		buttonGroup.add(sexradioFemale);
		sexradioFemale.setBounds(251, 70, 76, 23);
		contentPane.add(sexradioFemale);

		JLabel lblEmailAddress = new JLabel("Email");
		lblEmailAddress.setBounds(381, 76, 99, 15);
		contentPane.add(lblEmailAddress);

		emailAddressTextField = new JTextField();
		emailAddressTextField.setBounds(503, 72, 202, 19);
		contentPane.add(emailAddressTextField);
		emailAddressTextField.setColumns(10);

		JLabel lblCellphoneNumber = new JLabel("Cellphone (*)");
		lblCellphoneNumber.setBounds(12, 105, 147, 15);
		contentPane.add(lblCellphoneNumber);

		cellPhoneNumberTextField = new JTextField();
		cellPhoneNumberTextField.setBounds(177, 101, 190, 19);
		contentPane.add(cellPhoneNumberTextField);
		cellPhoneNumberTextField.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("ID Card No. (*)");
		lblNewLabel_1.setBounds(381, 105, 113, 15);
		contentPane.add(lblNewLabel_1);

		NoIdentityCardTextField = new JTextField();
		NoIdentityCardTextField.setBounds(503, 101, 202, 19);
		contentPane.add(NoIdentityCardTextField);
		NoIdentityCardTextField.setColumns(10);

		JLabel lblIssueDate = new JLabel("Issued Date (*)");
		lblIssueDate.setBounds(12, 134, 107, 15);
		contentPane.add(lblIssueDate);

		issueDateText = new JTextField();
		issueDateText.setText(Staff.getDateFormat().toPattern());
		issueDateText.setBounds(177, 130, 190, 19);
		contentPane.add(issueDateText);
		issueDateText.setColumns(10);

		JLabel lblIssuePlace = new JLabel("Issued Place (*)");
		lblIssuePlace.setBounds(381, 134, 113, 15);
		contentPane.add(lblIssuePlace);

		issuePlaceTextField = new JTextField();
		issuePlaceTextField.setBounds(503, 130, 202, 19);
		contentPane.add(issuePlaceTextField);
		issuePlaceTextField.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Permanent Address (*)");
		lblNewLabel_2.setBounds(12, 163, 165, 15);
		contentPane.add(lblNewLabel_2);

		permanentAddressTexField = new JTextField();
		permanentAddressTexField.setBounds(177, 161, 528, 19);
		contentPane.add(permanentAddressTexField);
		permanentAddressTexField.setColumns(10);

		JLabel lblCurrentAddress = new JLabel("Current Address (*)");
		lblCurrentAddress.setBounds(12, 192, 138, 15);
		contentPane.add(lblCurrentAddress);

		currentAddressTextField = new JTextField();
		currentAddressTextField.setBounds(177, 188, 528, 19);
		contentPane.add(currentAddressTextField);
		currentAddressTextField.setColumns(10);

		JLabel lblAcountNo = new JLabel("Account No. (*)");
		lblAcountNo.setBounds(12, 221, 107, 15);
		contentPane.add(lblAcountNo);

		accountNoTextField = new JTextField();
		accountNoTextField.setBounds(177, 217, 190, 19);
		contentPane.add(accountNoTextField);
		accountNoTextField.setColumns(10);

		JLabel lblNameOfBank = new JLabel("Bank Name (*)");
		lblNameOfBank.setBounds(381, 221, 113, 15);
		contentPane.add(lblNameOfBank);

		nameOfBankTextField = new JTextField();
		nameOfBankTextField.setBounds(503, 217, 202, 19);
		contentPane.add(nameOfBankTextField);
		nameOfBankTextField.setColumns(10);

		JLabel lblEducation = new JLabel("Education (*)");
		lblEducation.setBounds(12, 250, 90, 15);
		contentPane.add(lblEducation);

		educationComboBox = new JComboBox();
		educationComboBox.setModel(new DefaultComboBoxModel(EducationItems
				.toArray()));
		educationComboBox.setBounds(177, 246, 190, 19);
		contentPane.add(educationComboBox);

		JLabel lblDiploma = new JLabel("Diploma");
		lblDiploma.setBounds(381, 250, 70, 15);
		contentPane.add(lblDiploma);

		diplomaTextField = new JTextField();
		diplomaTextField.setBounds(503, 246, 202, 19);
		contentPane.add(diplomaTextField);
		diplomaTextField.setColumns(10);

		JLabel lblForeignLanguage = new JLabel("Foreign Language");
		lblForeignLanguage.setBounds(12, 279, 129, 15);
		contentPane.add(lblForeignLanguage);

		foreignLanguageTextField = new JTextField();
		foreignLanguageTextField.setBounds(177, 275, 190, 19);
		contentPane.add(foreignLanguageTextField);

		JLabel lblCertificateOfIt = new JLabel("IT Certificate");
		lblCertificateOfIt.setBounds(381, 279, 113, 15);
		contentPane.add(lblCertificateOfIt);

		certificateOfITcombobox = new JComboBox();
		certificateOfITcombobox.setModel(new DefaultComboBoxModel(
				ITCertificateItems.toArray()));
		certificateOfITcombobox.setBounds(503, 275, 202, 19);
		contentPane.add(certificateOfITcombobox);

		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EmployeeController.singleton.addAction();
			}
		});
		btnSave.setBounds(188, 347, 117, 25);
		contentPane.add(btnSave);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(363, 347, 117, 25);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EmployeeController.singleton.doCancel();
			}
		});
		contentPane.add(btnCancel);
		
		JLabel lblRequiredFields = new JLabel("(*) This information is required");
		lblRequiredFields.setForeground(Color.RED);
		lblRequiredFields.setBounds(12, 308, 355, 15);
		contentPane.add(lblRequiredFields);
		setLocationRelativeTo(null);
	}

	public JTextField getCurrentAddressTextField() {
		return currentAddressTextField;
	}

	public JTextField getAccountNoTextField() {
		return accountNoTextField;
	}

	public JTextField getNameOfBankTextField() {
		return nameOfBankTextField;
	}

	public JTextField getDiplomaTextField() {
		return diplomaTextField;
	}

	public JPanel getContentPane() {
		return contentPane;
	}

	public JTextField getFullNameTextField() {
		return fullNameTextField;
	}

	public JTextField getBirthdayTextField() {
		return birthdayTextField;
	}

	public ButtonGroup getButtonGroup() {
		return buttonGroup;
	}

	public JTextField getNoIdentityCardTextField() {
		return NoIdentityCardTextField;
	}

	public JTextField getIssueDateText() {
		return issueDateText;
	}

	public JTextField getIssuePlaceTextField() {
		return issuePlaceTextField;
	}

	public JTextField getPermanentAddressTexField() {
		return permanentAddressTexField;
	}

	public JTextField getCellPhoneNumberTextField() {
		return cellPhoneNumberTextField;
	}

	public JTextField getEmailAddressTextField() {
		return emailAddressTextField;
	}

	public JRadioButton getSexradioFemale() {
		return sexradioFemale;
	}

	public JRadioButton getSexradioMale() {
		return sexradioMale;
	}

	public JComboBox getEducationComboBox() {
		return educationComboBox;
	}

	public JComboBox getCertificateOfITcombobox() {
		return certificateOfITcombobox;
	}

	public JTextField getForeignLanguageTextField() {
		return foreignLanguageTextField;
	}
}