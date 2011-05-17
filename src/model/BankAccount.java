package model;

import java.util.Date;

public class BankAccount {
	private String accountNo;
	private String bankName;
	private Date validityDate;

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
