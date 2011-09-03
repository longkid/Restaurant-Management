package controller;

/**
 * Author: Le Duy Phong
 * 
 * Purpose of this class: this class control the process between EmployeeFrame and some concern class in model
 */
import java.text.ParseException;
import java.util.Date;

import javax.swing.JOptionPane;

import model.Address;
import model.BankAccount;
import model.Certificate;
import model.Diploma;
import model.Employee;
import model.IDCard;
import model.Sex;
import model.Staff;
import model.TelephoneNumber;
import view.EmployeeFrame;

public class EmployeeController {
	public static EmployeeController singleton = new EmployeeController();
	private EmployeeFrame view;
	private boolean isUpdate = false;
	private int index = 0;

	private EmployeeController() {
		view = new EmployeeFrame();
	}

	// this function use to set visible for employeeFrame.
	public void setVisible(boolean b) {
		view.setVisible(b);
	}

	// this function is used to add new employee
	public void addAction() {
		if (isUpdate == false) {
			Employee newEmployee = updateEmployee(null);
			if (newEmployee != null) {
				Staff.getInstance().addEmployee(newEmployee);
			}
		} else {
			Employee oldEmployee = Staff.getInstance().getEmployees()
					.get(index);
			Employee updatedEmployee = updateEmployee(oldEmployee);
			Staff.getInstance().updateEmployee(index, updatedEmployee);
		}
		
		StaffController.singleton.refresh();
	}

	@SuppressWarnings("unused")
	private Employee updateEmployee(Employee inputEmp) {
		Employee outputEmp;
		if (inputEmp == null) {
			outputEmp = new Employee();
		} else {
			outputEmp = inputEmp;
		}

		String notification = new String();

		String fullName = view.getFullNameTextField().getText();
		if (fullName.length() == 0) {
			notification += "\n- Full name";
		}

		Date birthday = null;
		try {
			birthday = Staff.getDateFormat().parse(view.getBirthdayTextField()
					.getText());
		} catch (ParseException e) {
			notification += "\n- Birthday following the format "
					+ Staff.getDateFormat().toPattern();
			// e.printStackTrace();
		}

		String phoneNumber = null;
		try {
			phoneNumber = view.getCellPhoneNumberTextField().getText();
			int number = Integer.parseInt(phoneNumber);
		} catch (NumberFormatException e) {
			notification += "\n- Correct phone number";
			// e.printStackTrace();
		}

		String idNumber = view.getNoIdentityCardTextField().getText();
		try {
			int number = Integer.parseInt(idNumber);
		} catch (NumberFormatException e) {
			notification += "\n- Correct Identity card No.";
			// e.printStackTrace();
		}
		Date issueDate = null;
		try {
			issueDate = Staff.getDateFormat().parse(view.getIssueDateText()
					.getText());
		} catch (ParseException e) {
			notification += "\n- Issued date following the format "
					+ Staff.getDateFormat().toPattern();
			// e.printStackTrace();
		}
		String place = view.getIssuePlaceTextField().getText();
		if (place.length() == 0) {
			notification += "\n- Issued place of ID card";
		}

		String permAddress = view.getPermanentAddressTexField().getText();
		if (permAddress.length() == 0) {
			notification += "\n- Permanent address";
		}
		String curraddress = view.getCurrentAddressTextField().getText();
		if (curraddress.length() == 0) {
			notification += "\n- Current address";
		}

		String accountNo = view.getAccountNoTextField().getText();
		String bankName = view.getNameOfBankTextField().getText();
		if (accountNo.length() == 0) {
			notification += "\n- Bank account No.";
		}
		if (bankName.length() == 0) {
			notification += "\n- Name of bank";
		}

		if (notification.length() != 0) { // Exist input errors
			notification = "You must provide the following information:"
					+ notification;
			JOptionPane.showMessageDialog(view, notification);
		} else { // No input errors
			outputEmp.setFullName(fullName);
			outputEmp.setBirthday(birthday);
			if (view.getSexradioMale().isSelected()) {
				outputEmp.setSex(Sex.MALE);
			} else {
				outputEmp.setSex(Sex.FEMALE);
			}
			String email = view.getEmailAddressTextField().getText();
			if (email.length() != 0) {
				outputEmp.addEmail(email);
			}
			outputEmp.addPhoneNumber(new TelephoneNumber(phoneNumber,
					new Date()));
			outputEmp.setIdentityCard(new IDCard(idNumber, issueDate, place));
			outputEmp.setPermanentAddress(permAddress);
			outputEmp.addTemporaryAddress(new Address(curraddress, new Date()));
			outputEmp.addAccount(new BankAccount(accountNo, bankName,
					new Date()));
			String education = view.getEducationComboBox().getSelectedItem()
					.toString();
			outputEmp.setEducation(education);
			String diploma = view.getDiplomaTextField().getText();
			if (diploma.length() != 0) {
				outputEmp.addDiploma(new Diploma(diploma, new Date()));
			}
			String language = view.getForeignLanguageTextField().getText();
			if (language.length() != 0) {
				outputEmp.addLanguageCertificate(new Certificate(language,
						new Date()));
			}
			String itCertificate = view.getCertificateOfITcombobox()
					.getSelectedItem().toString();
			if (itCertificate.length() != 0) {
				outputEmp.addItCertificate(new Certificate(itCertificate,
						new Date()));
			}
			singleton.setVisible(false);
			return outputEmp; // All necessary fields are valid -> Update
								// successfully
		}

		// Lack of necessary information or one of those fields is invalid ->
		// Update unsuccessfully
		singleton.setVisible(true);
		return inputEmp; // inputEmp = null or inputEmp contains old information
	}

	/*
	 * This function displays existed information of an employee. The user will
	 * modify some necessary information on form and click button Save to update
	 * employee information
	 */
	public void displayEmployee(int index) {
		Employee employee = Staff.getInstance().getEmployees().get(index);
		
		view.getFullNameTextField().setText(employee.getFullName());
		view.getBirthdayTextField().setText(
				Staff.getDateFormat().format(employee.getBirthday()));
		if (employee.getSex().equals(Sex.MALE)) {
			view.getSexradioMale().setSelected(true);
		} else {
			view.getSexradioFemale().setSelected(true);
		}
		String latestEmail = employee.getLatestEmail();
		if (latestEmail != null) {
			view.getEmailAddressTextField().setText(latestEmail);
		}
		TelephoneNumber latestPhoneNumber = employee.getLatestPhoneNumber();
		if (latestPhoneNumber != null) {
			view.getCellPhoneNumberTextField().setText(
					latestPhoneNumber.getPhoneNumber());
		}
		view.getNoIdentityCardTextField().setText(
				employee.getIdentityCard().getCardNum());
		view.getIssueDateText().setText(
				Staff.getDateFormat().format(employee.getIdentityCard()
						.getIssuedDate()));
		view.getIssuePlaceTextField().setText(
				employee.getIdentityCard().getIssuedPlace());
		view.getPermanentAddressTexField().setText(
				employee.getPermanentAddress());
		Address latestTempAddress = employee.getLatestTempAddress();
		if (latestTempAddress != null) {
			view.getCurrentAddressTextField().setText(
					latestTempAddress.getName());
		}
		BankAccount latestAccount = employee.getLatestAccount();
		if (latestAccount != null) {
			view.getAccountNoTextField().setText(
					latestAccount.getAccountNo());
			view.getNameOfBankTextField().setText(
					(latestAccount.getBankName()));
		}
		view.getEducationComboBox().setSelectedItem(employee.getEducation());
		Diploma latestDiploma = employee.getLatestDiploma();
		if (latestDiploma != null) {
			view.getDiplomaTextField().setText(
					latestDiploma.getName());
		}
		Certificate latestLangCertificate = employee.getLatestLangCertificate();
		if (latestLangCertificate != null) {
			view.getForeignLanguageTextField().setText(
					latestLangCertificate.getName());
		}
		Certificate latestITCertificate = employee.getLatestITCertificate();
		if (latestITCertificate != null) {
			view.getCertificateOfITcombobox().setSelectedItem(
					latestITCertificate.getName());
		}

		isUpdate = true;
		this.index = index;
		setVisible(true);
	}

	public void setUpdate(boolean isUpdate) {
		this.isUpdate = isUpdate;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public void clearAllInputField() {
		view.getFullNameTextField().setText("");
		view.getBirthdayTextField().setText(Staff.getDateFormat().toPattern());
		view.getSexradioMale().setSelected(true);
		view.getEmailAddressTextField().setText("");
		view.getCellPhoneNumberTextField().setText("");
		view.getNoIdentityCardTextField().setText("");
		view.getIssueDateText().setText(Staff.getDateFormat().toPattern());
		view.getIssuePlaceTextField().setText("");
		view.getPermanentAddressTexField().setText("");
		view.getCurrentAddressTextField().setText("");
		view.getAccountNoTextField().setText("");
		view.getNameOfBankTextField().setText("");
		view.getEducationComboBox().setSelectedIndex(0);
		view.getDiplomaTextField().setText("");
		view.getForeignLanguageTextField().setText("");
		view.getCertificateOfITcombobox().setSelectedIndex(0);
	}

	public void doCancel() {
		singleton.setVisible(false);
	}
	
	// 20110828: LH added
	public void displayDetailsOfEmployee(int index2) {
		
	}

}
