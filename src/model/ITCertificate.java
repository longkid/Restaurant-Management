package model;

import java.util.Date;

public class ITCertificate {
	private String name;
	private Date validityDate;

	public ITCertificate() {
	}

	public ITCertificate(String name, Date validityDate) {
		this.name = name;
		this.validityDate = validityDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getValidityDate() {
		return validityDate;
	}

	public void setValidityDate(Date validityDate) {
		this.validityDate = validityDate;
	}

}
