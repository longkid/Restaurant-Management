package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
 * This class for managing all object in program: manage Employee, manage contract...etc.
 * It only have 1 instance.
 */
public class ManageStaffInHotel {
	private static ManageStaffInHotel singletonManageStaffInHotel;
	private List<Employee> listEmployees = new ArrayList<Employee>();
	private List<Contract> listContracts = new ArrayList<Contract>();

	private ManageStaffInHotel() {
	}

	public static ManageStaffInHotel getInstance() {
		if (singletonManageStaffInHotel == null)
			singletonManageStaffInHotel = new ManageStaffInHotel();

		return singletonManageStaffInHotel;
	}

	public List<Employee> getListEmployees() {
		return listEmployees;
	}

	public void setListEmployees(List<Employee> listEmployees) {
		this.listEmployees = listEmployees;
	}

	public List<Contract> getListContracts() {
		return listContracts;
	}

	public void setListContracts(List<Contract> listContracts) {
		this.listContracts = listContracts;
	}

	/*
	 * addEmployee: this method for adding more Employee object to listEmployees
	 * return type: List object of employee.
	 */
	public List<Employee> addEmployee(String fullName, Date birthday, Sex sex,
			List<String> emails, List<TelephoneNumber> listPhoneNo,
			IDCard identityCard, String permanentAddress,
			List<Address> addresses, List<BankAccount> accounts,
			List<String> educations, List<Diploma> diplomas,
			List<ForeignLanguageCertificate> languages,
			List<ITCertificate> itCertificates) {
		Employee e1 = new Employee(fullName, birthday, sex, emails,
				listPhoneNo, identityCard, permanentAddress, addresses,
				accounts, educations, diplomas, languages, itCertificates);
		listEmployees.add(e1);
		return listEmployees;

	}

	/*
	 * This method for update some basic information of Employee Return type of
	 * this method is an object Employee.
	 */
	public Employee updateEmployee(String fullName, Date birthday, Sex sex,
			List<String> emails, List<TelephoneNumber> listPhoneNo,
			IDCard identityCard, String permanentAddress,
			List<Address> addresses, List<BankAccount> accounts,
			List<String> educations, List<Diploma> diplomas,
			List<ForeignLanguageCertificate> languages,
			List<ITCertificate> itCertificates, Employee e) {
		e.setAccounts(accounts);
		e.setBirthday(birthday);
		e.setAddresses(addresses);
		e.setDiplomas(diplomas);
		e.setEducations(educations);
		e.setEmails(emails);
		e.setFullName(fullName);
		e.setIdentityCard(identityCard);
		e.setItCertificates(itCertificates);
		e.setLanguages(languages);
		e.setPermanentAddress(permanentAddress);
		e.setListPhoneNo(listPhoneNo);
		e.setSex(sex);
		return e;
	}

	/*
	 * This method for removing an object Employee out of list employee in
	 * ManageStaffInHotel. Return type of this method: void
	 */
	public void deleteEmployee(Employee e) {
		for (int i = 0; i < listEmployees.size(); i++) {
			if (listEmployees.get(i).getIdentityCard().getCardNum()
					.equals(e.getIdentityCard().getCardNum()))
				listEmployees.remove(i);
			break;
		}

	}
}
