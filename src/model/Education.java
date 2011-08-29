package model;

import java.io.Serializable;
import java.util.Date;

public class Education implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private Date validityDate;

	public Education(String name, Date validityDate) {
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
