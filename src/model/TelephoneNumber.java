package model;

import java.util.Date;

public class TelephoneNumber {
	private String phoneNumber;
	private Date validityDate;

	public TelephoneNumber(String phoneNumber, Date validityDate) {
		this.phoneNumber = phoneNumber;
		this.validityDate = validityDate;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Date getValidityDate() {
		return validityDate;
	}

	public void setValidityDate(Date validityDate) {
		this.validityDate = validityDate;
	}

}
