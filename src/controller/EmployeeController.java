package controller;

/**
 * Author: Le Duy Phong


 * Purpose of this class: this class control the process between EmployeeFrame and some concern class in model
 */
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import model.Address;
import model.BankAccount;
import model.Certificate;
import model.Diploma;
import model.Employee;
import model.IDCard;
import model.ProcessFile;
import model.Sex;
import model.Staff;
import model.TelephoneNumber;
import view.EmployeeFrame;

public class EmployeeController {
	public static EmployeeController singleton = new EmployeeController();
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
	private EmployeeFrame view;
	private List<String> emails = new ArrayList<String>();
	private List<TelephoneNumber> phoneNumbers = new ArrayList<TelephoneNumber>();
	private List<Address> addresses = new ArrayList<Address>();
	private List<BankAccount> accounts = new ArrayList<BankAccount>();
	private List<String> educations = new ArrayList<String>();
	private List<Diploma> diplomas = new ArrayList<Diploma>();
	private List<Certificate> languages = new ArrayList<Certificate>();
	private List<Certificate> itCertificates = new ArrayList<Certificate>();
	private boolean isUpdate = false;
	private int index = 0;

	public EmployeeController() {
		this.view = new EmployeeFrame();

	}
//this function use to set visible for employeeFrame.
	public void setVisible(boolean b) {
		this.view.setVisible(b);
	}
// this function is used to add new employee
	public void addAction() {
		if(isUpdate==false){
		emails.add(this.view.getEmailAddressTextField().getText());
		phoneNumbers.add(new TelephoneNumber(this.view
				.getCellPhoneNumberTextField().getText(), new Date()));
		addresses.add(new Address(this.view.getCurrentAddressTextField()
				.getText(), new Date()));
		accounts.add(new BankAccount(this.view.getAccountNoTextField()
				.getText(), this.view.getNameOfBankTextField().getText(),
				new Date()));

		diplomas.add(new Diploma(this.view.getDiplomaTextField().getText(),
				new Date()));
		
		itCertificates.add(new Certificate(this.view.getCertificateOfITcombobox().getSelectedIndex()+"",new Date()));
		languages.add(new Certificate(this.view.getForeignLanguageCombobox().getSelectedIndex()+"", new Date()));
		phoneNumbers.add(new TelephoneNumber(this.view.getCellPhoneNumberTextField().getText(), new Date()));
		
			Sex sex = null;
			Date birthday = null;
			Date issueDate = null;
				if (this.view.getSexradioMale().isSelected()) {
					sex = Sex.MALE;
				} else {
					sex = Sex.FEMALE;
				}
				try {
					birthday = dateFormat.parse(this.view.getBirthdayTextField().getText());
					issueDate=dateFormat.parse(this.view.getIssueDateText().getText());
					Staff.getInstance().addEmployee(
							new Employee(
									this.view.getFullNameTextField().getText(),
									birthday,
									sex, emails, phoneNumbers, new IDCard(
											this.view.getNoIdentityCardTextField()
													.getText(), issueDate, this.view
													.getIssuePlaceTextField()
													.getText()), this.view
											.getPermanentAddressTexField()
											.getText(), addresses, accounts,
									this.view.getEducationComboBox()
											.getSelectedIndex()+"",
									diplomas, languages, itCertificates));

				// lbResult.setText("Save successfully!");
				JOptionPane.showMessageDialog(this.view, "Save successfully!",
						"Save successfully!", JOptionPane.INFORMATION_MESSAGE);
				singleton.setVisible(false);
				StaffController.singleton.refresh();
				ProcessFile.WriteData(Staff.getInstance().getEmployees(), ProcessFile.FILENAME_EMPLOYEE);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(this.view, "You must input date follow the format yyyy-mm-dd, please!");
					e.printStackTrace();
				}
				
		
		}
		// the following code is used for update employee
		else{
			Employee employee = Staff.getInstance().getEmployees().get(index);
			employee.getAccounts().add(new BankAccount(this.view.getAccountNoTextField().getText(), this.view.getNameOfBankTextField().getText(), new Date()));
			employee.getDiplomas().add(new Diploma(this.view.getDiplomaTextField().getText(), new Date()));
			employee.setEducation(this.view.getEducationComboBox().getSelectedIndex()+"");
			employee.getEmails().add(this.view.getEmailAddressTextField().getText());
			employee.setFullName(this.view.getFullNameTextField().getText());
			employee.getItCertificates().add(new Certificate(this.view.getCertificateOfITcombobox().getSelectedIndex()+"",new Date()));
			employee.getLanguageCertificates().add(new Certificate(this.view.getForeignLanguageCombobox().getSelectedIndex()+"", new Date()));
			employee.setPermanentAddress(this.view.getPermanentAddressTexField().getText());
			employee.getPhoneNumbers().add(new TelephoneNumber(this.view.getCellPhoneNumberTextField().getText(), new Date()));
			if(this.view.getSexradioMale().isSelected()){
				employee.setSex(Sex.MALE);
			}else{
				employee.setSex(Sex.FEMALE);
			}
			employee.getTemporaryAddresses().add(new Address(this.view.getCurrentAddressTextField().getText(), new Date()));
			try {
				employee.setBirthday(dateFormat.parse(this.view.getBirthdayTextField().getText()));
				employee.setIdentityCard(new IDCard(this.view.getNoIdentityCardTextField().getText(), dateFormat.parse(this.view.getIssueDateText().getText()), this.view.getIssuePlaceTextField().getText()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(this.view, "You must input date follow the format yyyy-mm-dd, please!");
				e.printStackTrace();
			}
            employee.getItCertificates().add(new Certificate(this.view.getCertificateOfITcombobox().getSelectedIndex()+"",new Date()));
			Staff.getInstance().updateEmployee(index, employee);
			ProcessFile.WriteData(Staff.getInstance().getEmployees(), ProcessFile.FILENAME_EMPLOYEE);
			singleton.setVisible(false);
			StaffController.singleton.refresh();
			JOptionPane.showMessageDialog(this.view, "Update successful");
		}
		this.view.getFullNameTextField().setText("");
		
		
	}
//this function get infomation of employee to display on form, user will modify some necessary information on form and click button save to update employee
	public void displayEmployee(int index) {
		Employee employee = Staff.getInstance().getEmployees().get(index);
		this.view.getFullNameTextField().setText(employee.getFullName());
		this.view.getBirthdayTextField().setText(Staff.getInstance().dateFormat.format(employee.getBirthday()));
				
		this.view.getCellPhoneNumberTextField().setText(employee.getPhoneNumbers().get(employee.getPhoneNumbers().size()-1).getPhoneNumber());
		this.view.getCertificateOfITcombobox().setSelectedIndex(Integer.parseInt(employee.getItCertificates().get(employee.getItCertificates().size()-1).getName()));
		this.view.getDiplomaTextField().setText(employee.getDiplomas().get(employee.getDiplomas().size()-1).getName());
		this.view.getEducationComboBox().setSelectedIndex(Integer.parseInt(employee.getEducation()));
		this.view.getEmailAddressTextField().setText(employee.getEmails().get(employee.getEmails().size()-1));
		this.view.getForeignLanguageCombobox().setSelectedIndex(Integer.parseInt(employee.getLanguageCertificates().get(employee.getLanguageCertificates().size()-1).getName()));
		this.view.getIssueDateText().setText(Staff.getInstance().dateFormat.format(employee.getIdentityCard().getIssuedDate()));
		this.view.getIssuePlaceTextField().setText(employee.getIdentityCard().getIssuedPlace());
		this.view.getNoIdentityCardTextField().setText(employee.getIdentityCard().getCardNum());
		this.view.getNameOfBankTextField().setText((employee.getAccounts().get(employee.getAccounts().size()-1).getBankName()));
		this.view.getAccountNoTextField().setText(employee.getAccounts().get(employee.getAccounts().size()-1).getAccountNo());
		this.view.getPermanentAddressTexField().setText(employee.getPermanentAddress());
		this.view.getCurrentAddressTextField().setText(employee.getTemporaryAddresses().get(employee.getTemporaryAddresses().size()-1).getName());
		if(employee.getSex().equals(Sex.MALE)){
			this.view.getSexradioMale().setSelected(true);
		}else{
			this.view.getSexradioFemale().setSelected(true);
		}
		isUpdate = true;
		this.index =index;
		this.setVisible(true);

	}

	public void setUpdate(boolean isUpdate) {
		this.isUpdate = isUpdate;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
}
