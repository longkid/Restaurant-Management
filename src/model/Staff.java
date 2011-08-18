package model;

/*Author: Le Duy Phong
 * This class is used for managing all employees in the restaurant.
 * There should be only one instance of this class in the application.
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.swing.JOptionPane;

public class Staff {
	private static Staff singleton;
	public static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy-MM-dd");
	private List<Employee> employees = null;
	private List<Position> positions = null;

	@SuppressWarnings("unchecked")
	private Staff() {
		File f = new File(FileProcessing.FILENAME_POSITION);
		if (f.exists()) {
			positions = (List<Position>) FileProcessing.ReadData(FileProcessing.FILENAME_POSITION);
		}
		
		if (positions == null) {
			positions = new ArrayList<Position>();
			initializePositions();
			FileProcessing.WriteData(positions, FileProcessing.FILENAME_POSITION);
		}
		f = new File(FileProcessing.FILENAME_EMPLOYEE);
		if (f.exists()) {
			employees = (List<Employee>) FileProcessing.ReadData(FileProcessing.FILENAME_EMPLOYEE);
		}
		if (employees == null) {
			employees = new ArrayList<Employee>();
			initializeEmployees();
			FileProcessing.WriteData(employees, FileProcessing.FILENAME_EMPLOYEE);
		}
	}

	public static Staff getInstance() {
		if (singleton == null) {
			singleton = new Staff();
		}
		
		return singleton;
	}

	private void initializePositions() {
		for (PositionTitle title : PositionTitle.values()) {
			positions.add(Position.createPosition(title));
		}
	}
	
	public List<Position> getPositions() {
		return positions;
	}
	
	public void setPositions(List<Position> positions) {
		this.positions = positions;
		writeData(positions, FileProcessing.FILENAME_POSITION);
	}

	public Position getPosition(int index) {
		return positions.get(index);
	}
	
	public void updatePosition(int index, Position p) {
		Position temp = positions.get(index);
		temp.setSalary(p.getSalary());
		temp.setOtherSalary(p.getOtherSalary());
		positions.set(index, temp);
		writeData(positions, FileProcessing.FILENAME_POSITION);
	}

	private void writeData(Object obj, String strPath) {
		boolean result = FileProcessing.WriteData(obj, strPath);
		if (result) {
			JOptionPane.showMessageDialog(null, "Successful Update");
		} else {
			JOptionPane.showMessageDialog(null, "Failed Update");
		}
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	/*
	 * Add the specified employee
	 */
	public void addEmployee(Employee emp) {
		employees.add(emp);
	}

	/*
	 * Update information of existed employee with information of specified employee
	 */
	public void updateEmployee(int index, Employee emp) {
		employees.set(index, emp);
	}

	/*
	 * Remove the employee equivalent to specified employee
	 */
	public void deleteEmployee(int index) {
		employees.remove(index);
	}
	
	/*
	 * 20110618 - LH: This method will initialize information of some
	 * employees for testing this application functionality. Later we
	 * will use this application to add new employee.
	 */
	private void initializeEmployees() {
		try {
			FileReader file = new FileReader("data/StaffInformation.txt");
			BufferedReader buff = new BufferedReader(file);
			boolean eof = false;
			int lineNum = 0;
			Employee emp = null;
			while (!eof) {
				String line = buff.readLine();
				if (line == null) {
					eof = true;
				} else {
					if (lineNum < 14) {
						if (!line.equals("null")) {
							emp = updateEmployee(emp, line, lineNum);
						}
						lineNum++;
					} else {
						employees.add(emp);
						lineNum = 0;
					}
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private Employee updateEmployee(Employee employee, String line, int lineNum) {
		StringTokenizer str = new StringTokenizer(line, ";");
		switch (lineNum) {
		case 0:
			employee = new Employee(); // Create a new Employee to contain new information
			employee.setFullName(line);
			break;
		case 1:
			try {
				employee.setBirthday(dateFormat.parse(line));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			break;
		case 2:
			employee.setSex(line.equals("M") ? Sex.MALE : Sex.FEMALE);
			break;
		case 3:
			List<String> emails = new ArrayList<String>();
			while (str.hasMoreTokens())
				emails.add(str.nextToken());
			employee.setEmails(emails);
			break;
		case 4:
			List<TelephoneNumber> phoneNumbers = new ArrayList<TelephoneNumber>();
			while (str.hasMoreTokens()) {
				TelephoneNumber elt = (TelephoneNumber) createObject(
						new TelephoneNumber(), str.nextToken());
				phoneNumbers.add(elt);
			}
			employee.setPhoneNumbers(phoneNumbers);
			break;
		case 5:
			IDCard id = (IDCard) createObject(new IDCard(), line);
			employee.setIdentityCard(id);
			break;
		case 6:
			employee.setPermanentAddress(line);
			break;
		case 7:
			List<Address> temporaryAddresses = new ArrayList<Address>();
			while (str.hasMoreTokens()) {
				Address elt = (Address) createObject(new Address(),
						str.nextToken());
				temporaryAddresses.add(elt);
			}
			employee.setTemporaryAddresses(temporaryAddresses);
			break;
		case 8:
			List<BankAccount> accounts = new ArrayList<BankAccount>();
			while (str.hasMoreTokens()) {
				BankAccount elt = (BankAccount) createObject(new BankAccount(),
						str.nextToken());
				accounts.add(elt);
			}
			employee.setAccounts(accounts);
			break;
		case 9:
			// List<Contract> contracts
			break;
		case 10:
			employee.setEducation(line);
			break;
		case 11:
			List<Diploma> diplomas = new ArrayList<Diploma>();
			while (str.hasMoreTokens()) {
				Diploma elt = (Diploma) createObject(new Diploma(),
						str.nextToken());
				diplomas.add(elt);
			}
			employee.setDiplomas(diplomas);
			break;
		case 12:
		case 13:
			List<Certificate> certificates = new ArrayList<Certificate>();
			while (str.hasMoreTokens()) {
				Certificate elt = (Certificate) createObject(new Certificate(),
						str.nextToken());
				certificates.add(elt);
			}
			if (lineNum == 12) {
				employee.setLanguageCertificates(certificates);
			} else if (lineNum == 13) {
				employee.setItCertificates(certificates);
			}
			break;
		}
		
		return employee;
	}

	private Object createObject(Object input, String datas) {
		StringTokenizer str = new StringTokenizer(datas, ":");
		try {
			if (input instanceof TelephoneNumber) {
				return new TelephoneNumber(str.nextToken(),
						dateFormat.parse(str.nextToken()));
			}
			if (input instanceof IDCard) {
				return new IDCard(str.nextToken(), dateFormat.parse(str
						.nextToken()), str.nextToken());
			}
			if (input instanceof Address) {
				return new Address(str.nextToken(), dateFormat.parse(str
						.nextToken()));
			}
			if (input instanceof BankAccount) {
				return new BankAccount(str.nextToken(), str.nextToken(),
						dateFormat.parse(str.nextToken()));
			}
			
			 /* if (input instanceof Contract) { Position pos = Position. return
			 * new Address(str.nextToken(), dateFormat.parse(str.nextToken()));
			 * }
			 * */
			 
			if (input instanceof Diploma) {
				return new Diploma(str.nextToken(), dateFormat.parse(str
						.nextToken()));
			}
			if (input instanceof Certificate) {
				return new Certificate(str.nextToken(), dateFormat.parse(str
						.nextToken()));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return null;
	}
}
