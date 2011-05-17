package model;

import java.util.Date;
import java.util.List;

public class Employee {
	private String fullName;
	private Date birthday;
	private Sex sex;
	private List<String> emails;
	private List<TelephoneNumber> listPhoneNo;
	private IDCard identityCard;
	private String permanentAddress;
	private List<Address> temporaryAddresses;
	private List<BankAccount> accounts;
	private List<Contract> contracts;
	private List<String> educations;
	private List<Diploma> diplomas;
	private List<ForeignLanguageCertificate> languages;
	private List<ITCertificate> itCertificates;

	public Employee() {
	}

	public Employee(String fullName, Date birthday, Sex sex,
			List<String> emails, List<TelephoneNumber> listPhoneNo,
			IDCard identityCard, String permanentAddress,
			List<Address> temporaryAddresses, List<BankAccount> accounts,
			List<String> educations, List<Diploma> diplomas,
			List<ForeignLanguageCertificate> languages,
			List<ITCertificate> itCertificates) {
		this.fullName = fullName;
		this.birthday = birthday;
		this.sex = sex;
		this.emails = emails;
		this.listPhoneNo = listPhoneNo;
		this.identityCard = identityCard;
		this.permanentAddress = permanentAddress;
		this.temporaryAddresses = temporaryAddresses;
		this.accounts = accounts;
		this.educations = educations;
		this.diplomas = diplomas;
		this.languages = languages;
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

	public List<String> getEmails() {
		return emails;
	}

	public void setEmails(List<String> emails) {
		this.emails = emails;
	}

	public List<TelephoneNumber> getListPhoneNo() {
		return listPhoneNo;
	}

	public void setListPhoneNo(List<TelephoneNumber> listPhoneNo) {
		this.listPhoneNo = listPhoneNo;
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

	public List<Address> getAddresses() {
		return temporaryAddresses;
	}

	public void setAddresses(List<Address> temporaryAddresses) {
		this.temporaryAddresses = temporaryAddresses;
	}

	public List<BankAccount> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<BankAccount> accounts) {
		this.accounts = accounts;
	}

	public List<Contract> getContracts() {
		return contracts;
	}

	public void setContracts(List<Contract> contracts) {
		this.contracts = contracts;
	}

	public List<String> getEducations() {
		return educations;
	}

	public void setEducations(List<String> educations) {
		this.educations = educations;
	}

	public List<Diploma> getDiplomas() {
		return diplomas;
	}

	public void setDiplomas(List<Diploma> diplomas) {
		this.diplomas = diplomas;
	}

	public List<ForeignLanguageCertificate> getLanguages() {
		return languages;
	}

	public void setLanguages(List<ForeignLanguageCertificate> languages) {
		this.languages = languages;
	}

	public List<ITCertificate> getItCertificates() {
		return itCertificates;
	}

	public void setItCertificates(List<ITCertificate> itCertificates) {
		this.itCertificates = itCertificates;
	}

}
