package model;

import java.util.Date;
import java.util.List;

public class Employee {
	private String fullName;
	private Date birthday;
	private Sex sex;
	private List<String> emails;
	private List<TelephoneNumber> phoneNumbers;
	private IDCard identityCard;
	private String permanentAddress;
	private List<Address> temporaryAddresses;
	private List<BankAccount> accounts;
	private List<Contract> contracts;
	private String education;
	private List<Diploma> diplomas;
	private List<Certificate> languageCertificates;
	private List<Certificate> itCertificates;

	public Employee(String fullName, Date birthday, Sex sex,
			List<String> emails, List<TelephoneNumber> phoneNumbers,
			IDCard identityCard, String permanentAddress,
			List<Address> temporaryAddresses, List<BankAccount> accounts,
			String education, List<Diploma> diplomas,
			List<Certificate> languageCertificates,
			List<Certificate> itCertificates) {
		this.fullName = fullName;
		this.birthday = birthday;
		this.sex = sex;
		this.emails = emails;
		this.phoneNumbers = phoneNumbers;
		this.identityCard = identityCard;
		this.permanentAddress = permanentAddress;
		this.temporaryAddresses = temporaryAddresses;
		this.accounts = accounts;
		this.education = education;
		this.diplomas = diplomas;
		this.languageCertificates = languageCertificates;
		this.itCertificates = itCertificates;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Sex getSex() {
		return sex;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}

	public IDCard getIdentityCard() {
		return identityCard;
	}

	public void setIdentityCard(IDCard identityCard) {
		this.identityCard = identityCard;
	}

	public String getPermanentAddress() {
		return permanentAddress;
	}

	public void setPermanentAddress(String permanentAddress) {
		this.permanentAddress = permanentAddress;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public List<String> getEmails() {
		return emails;
	}

	public void addEmail(String email) {
		emails.add(email);
	}

	public List<TelephoneNumber> getPhoneNumbers() {
		return phoneNumbers;
	}

	public void addPhoneNumber(TelephoneNumber num) {
		phoneNumbers.add(num);
	}

	public List<Address> getTemporaryAddresses() {
		return temporaryAddresses;
	}
	
	public void addTemporaryAddress(Address address) {
		temporaryAddresses.add(address);
	}

	public List<BankAccount> getAccounts() {
		return accounts;
	}
	
	public void addAccount(BankAccount acc) {
		accounts.add(acc);
	}

	public List<Contract> getContracts() {
		return contracts;
	}
	
	public void addContract(Contract contract) {
		contracts.add(contract);
	}
	
	public void updateContract(int index, Contract newContract) {
		contracts.set(index, newContract);
	}
	
	public void deleteContract(int index) {
		contracts.remove(index);
	}

	public List<Diploma> getDiplomas() {
		return diplomas;
	}
	
	public void addDiploma(Diploma diploma) {
		diplomas.add(diploma);
	}
	
	public List<Certificate> getLanguageCertificates() {
		return languageCertificates;
	}
	
	public void addLanguageCertificate(Certificate certificate) {
		languageCertificates.add(certificate);
	}

	public List<Certificate> getItCertificates() {
		return itCertificates;
	}
	
	public void addItCertificate(Certificate certificate) {
		itCertificates.add(certificate);
	}
}
