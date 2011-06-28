package model;
/**
 * Author: Le Duy Phong

<<<<<<< HEAD
import java.io.Serializable;
=======
 * Purpose of this class: this class is used to save information about employee.
 */
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;
@SuppressWarnings("deprecation")
public class Employee  implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String fullName;
	private Date birthday;
	private Sex sex;
	private List<String> emails = new ArrayList<String>();
	private List<TelephoneNumber> phoneNumbers = new ArrayList<TelephoneNumber>();
	private IDCard identityCard;
	private String permanentAddress;
	private List<Address> temporaryAddresses;
	private List<BankAccount> accounts;
	private List<Contract> contracts;
	private Contract currentContract;
	private String education;
	private List<Diploma> diplomas;
	private List<Certificate> languageCertificates;
	private List<Certificate> itCertificates;
	public Employee()
	{
		this.fullName = "";
		this.birthday = new Date();
		this.sex = Sex.MALE;
		this.emails = new ArrayList<String>();
		this.phoneNumbers = new ArrayList<TelephoneNumber>();
		this.identityCard = new IDCard();
		this.permanentAddress = "";
		this.temporaryAddresses = new ArrayList<Address>();
		this.accounts = new ArrayList<BankAccount>();
		this.education = "";
		this.diplomas = new ArrayList<Diploma>();
		this.languageCertificates = new ArrayList<Certificate>();
		this.itCertificates = new ArrayList<Certificate>();
		this.currentContract=new Contract();
	}
	
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

	public Date getBirthday() {
		return birthday;
	}

	public Sex getSex() {
		return sex;
	}

	public List<String> getEmails() {
		return emails;
	}

	public List<TelephoneNumber> getPhoneNumbers() {
		return phoneNumbers;
	}

	public IDCard getIdentityCard() {
		return identityCard;
	}

	public String getPermanentAddress() {
		return permanentAddress;
	}

	public List<Address> getTemporaryAddresses() {
		return temporaryAddresses;
	}

	public List<BankAccount> getAccounts() {
		return accounts;
	}

	public List<Contract> getContracts() {
		return contracts;
	}

	public String getEducation() {
		return education;
	}

	public List<Diploma> getDiplomas() {
		return diplomas;
	}

	public List<Certificate> getLanguageCertificates() {
		return languageCertificates;
	}

	public List<Certificate> getItCertificates() {
		return itCertificates;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}

	public void setEmails(List<String> emails) {
		this.emails = emails;
	}

	public void setPhoneNumbers(List<TelephoneNumber> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}

	public void setIdentityCard(IDCard identityCard) {
		this.identityCard = identityCard;
	}

	public void setPermanentAddress(String permanentAddress) {
		this.permanentAddress = permanentAddress;
	}

	public void setTemporaryAddresses(List<Address> temporaryAddresses) {
		this.temporaryAddresses = temporaryAddresses;
	}

	public void setAccounts(List<BankAccount> accounts) {
		this.accounts = accounts;
	}

	public void setContracts(List<Contract> contracts) {
		this.contracts = contracts;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public void setDiplomas(List<Diploma> diplomas) {
		this.diplomas = diplomas;
	}

	public void setLanguageCertificates(List<Certificate> languageCertificates) {
		this.languageCertificates = languageCertificates;
	}

	public void setItCertificates(List<Certificate> itCertificates) {
		this.itCertificates = itCertificates;
	}

	public void addEmail(String email) {
		emails.add(email);
	}

	public void addPhoneNumber(TelephoneNumber num) {
		phoneNumbers.add(num);
	}

	public void addTemporaryAddress(Address address) {
		temporaryAddresses.add(address);
	}

	public void addAccount(BankAccount acc) {
		accounts.add(acc);
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

	public void addDiploma(Diploma diploma) {
		diplomas.add(diploma);
	}

	public void addLanguageCertificate(Certificate certificate) {
		languageCertificates.add(certificate);
	}

	public void addItCertificate(Certificate certificate) {
		itCertificates.add(certificate);
	}
	/*
	 * @author Tu Thi Xuan Hien
	 */
	public Vector<String>getVector()
	{
		Vector<String>vec=new Vector<String>();
		vec.add(this.fullName);
		vec.add(this.birthday.getDay()+"/" +this.birthday.getMonth()+"/"+this.birthday.getYear());
		if(this.sex==Sex.FEMALE)
			vec.add("Female");
		else
			vec.add("Male");
		return vec;
	}
	public void setCurrentContract(Contract con)
	{
		this.currentContract=con;
	}
	public Contract getCurrentContract()
	{
		return this.currentContract;
	}
		
	public String getLatestEmail() {
		return getLastElement(emails);
	}
	
	public TelephoneNumber getLatestPhoneNumber() {
		return getLastElement(phoneNumbers);
	}
	
	public Address getLatestTempAddress() {
		return getLastElement(temporaryAddresses);
	}
	
	public BankAccount getLatestAccount() {
		return getLastElement(accounts);
	}
	
	public Contract getLatestContract() {
		return getLastElement(contracts);
	}
	
	public Diploma getLatestDiploma() {
		return getLastElement(diplomas);
	}
	
	public Certificate getLatestLangCertificate() {
		return getLastElement(languageCertificates);
	}
	
	public Certificate getLatestITCertificate() {
		return getLastElement(itCertificates);
	}
	
	public static <E> E getLastElement(List<E> l) {
		return l.get(l.size() - 1);
	}
}
