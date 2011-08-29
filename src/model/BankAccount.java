package model;
/**
 * Author: Le Duy Phong

 * Purpose of this class: this class is used to save bank account of employee.
 */
import java.io.Serializable;
import java.util.Date;

public class BankAccount implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String accountNo;
	private String bankName;
	private Date validityDate;

	public BankAccount() {
		
	}

	public BankAccount(String accountNo, String bankName, Date validityDate) {
		this.accountNo = accountNo;
		this.bankName = bankName;
		this.validityDate = validityDate;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public Date getValidityDate() {
		return validityDate;
	}

	public void setValidityDate(Date validityDate) {
		this.validityDate = validityDate;
	}

}
