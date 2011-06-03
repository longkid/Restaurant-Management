package model;

import java.util.Date;

public class ForeignLanguageCertificate {
	private String name;
	private Date validityDate;

	public ForeignLanguageCertificate(String name, Date validityDate) {
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
