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
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Vector;

public class Employee implements Serializable {
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
	// 20110822: LH removed
	//private Contract currentContract;
	private String education;
	private List<Diploma> diplomas;
	private List<Certificate> languageCertificates;
	private List<Certificate> itCertificates;

	public Employee() {
		this.fullName = "";
		this.birthday = new Date();
		this.sex = Sex.MALE;
		this.emails = new ArrayList<String>();
		this.phoneNumbers = new ArrayList<TelephoneNumber>();
		this.identityCard = new IDCard();
		this.permanentAddress = "";
		this.temporaryAddresses = new ArrayList<Address>();
		this.accounts = new ArrayList<BankAccount>();
		this.contracts = new ArrayList<Contract>();
		this.education = "";
		this.diplomas = new ArrayList<Diploma>();
		this.languageCertificates = new ArrayList<Certificate>();
		this.itCertificates = new ArrayList<Certificate>();
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
		this.contracts = null;
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
		boolean notFound = true;
		for (String item : emails) {
			if (email.equalsIgnoreCase(item)) {
				notFound = false;
				break;
			}
		}
		if (notFound) {
			emails.add(email);
		}
	}

	public void addPhoneNumber(TelephoneNumber num) {
		boolean notFound = true;
		for (TelephoneNumber item : phoneNumbers) {
			if (num.getPhoneNumber().equals(item.getPhoneNumber())) {
				notFound = false;
				break;
			}
		}
		if (notFound) {
			phoneNumbers.add(num);
		}
	}

	public void addTemporaryAddress(Address address) {
		boolean notFound = true;
		for (Address item : temporaryAddresses) {
			if (address.getName().equalsIgnoreCase(item.getName())) {
				notFound = false;
				break;
			}
		}
		if (notFound) {
			temporaryAddresses.add(address);
		}
	}

	public void addAccount(BankAccount acc) {
		boolean notFound = true;
		for (BankAccount item : accounts) {
			if (acc.getAccountNo().equals(item.getAccountNo())
					&& acc.getBankName().equalsIgnoreCase(item.getBankName())) {
				notFound = false;
				break;
			}
		}
		if (notFound) {
			accounts.add(acc);
		}
	}

	public void addContract(Contract contract) {
		boolean notFound = true;
		for (Contract item : contracts) {
			if (contract.getPosition().getTitle().equals(item.getPosition().getTitle())) {
				notFound = false;
				break;
			}
		}
		if (notFound) {
			contracts.add(contract);
		}
	}

	public void updateContract(int index, Contract newContract) {
		contracts.set(index, newContract);
	}

	public void deleteContract(int index) {
		contracts.remove(index);
	}

	public void addDiploma(Diploma diploma) {
		boolean notFound = true;
		for (Diploma item : diplomas) {
			if (diploma.getName().equalsIgnoreCase(item.getName())) {
				notFound = false;
				break;
			}
		}
		if (notFound) {
			diplomas.add(diploma);
		}
	}

	public void addLanguageCertificate(Certificate certificate) {
		boolean notFound = true;
		for (Certificate item : languageCertificates) {
			if (certificate.getName().equalsIgnoreCase(item.getName())) {
				notFound = false;
				break;
			}
		}
		if (notFound) {
			languageCertificates.add(certificate);
		}
	}

	public void addItCertificate(Certificate certificate) {
		boolean notFound = true;
		for (Certificate item : itCertificates) {
			if (certificate.getName().equalsIgnoreCase(item.getName())) {
				notFound = false;
				break;
			}
		}
		if (notFound) {
			itCertificates.add(certificate);
		}
	}

	/*
	 * @author Tu Thi Xuan Hien
	 */
	public Vector<String> getVector() {
		Vector<String> vec = new Vector<String>();
		vec.add(this.fullName);
		vec.add(Staff.dateFormat.format(birthday));
		vec.add((sex == Sex.FEMALE) ? "Female" : "Male");
		vec.add((getCurrentContract() == null) ? "No" : "Yes");
		return vec;
	}

	public void setCurrentContract(Contract con) {
		//this.currentContract = con;
		updateContract(contracts.size() - 1, con);
	}

	public Contract getCurrentContract() {
		// 20110822: LH changed
		//return this.currentContract;
		return getLastElement(contracts);
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
		// 20110822: LH added to fix the error of NullPointerException
		if (l == null) {
			return null;
		}
		return (l.isEmpty() ? null : l.get(l.size() - 1));
	}

	/*
	 * 20110822: LH added
	 * This methods search the contract which corresponds to the specified time.
	 * This methods is used inside the calculating payroll functionality, the
	 * functionality of saving Timekeeping records and update Timekeeping table.
	 */
	public Contract searchCorrespondingContract(int selectedYear,
			int selectedMonth) {
		if (contracts != null) {
			for (Contract con : contracts) {
				if (con != null) {
					Calendar c = Calendar.getInstance();
					c.setTime(con.getStartDate());
					int day = c.get(Calendar.DAY_OF_MONTH);
					c.set(selectedYear, selectedMonth - 1, day);
					if (con.checkMiddleTime(c)) {
						return con;
					}
				}
			}
		}
			
		return null;
	}
	
	
}
