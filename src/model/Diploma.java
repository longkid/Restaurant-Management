package model;
/**
 * Author: Le Duy Phong

 * Purpose of this class: this class is used to save information about diploma of employee.
 */
import java.io.Serializable;
import java.util.Date;

public class Diploma implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private Date validityDate;

	public Diploma() {
		
	}

	public Diploma(String name, Date validityDate) {
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

	@Override
	public String toString() {
		return name + ", " + Staff.dateFormat.format(validityDate);
	}

}
