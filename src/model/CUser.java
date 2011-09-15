package model;

import java.io.Serializable;

public class CUser implements Serializable {
	private static final long serialVersionUID = 1L;
	private String userName = "";
	private String password = "";
	private boolean rememberPassword = false;

	public CUser() {
		this.userName = "";
		this.password = "";
		this.rememberPassword = false;
	}

	public CUser(String strUserName, String strPassword,
			boolean bIsRememberPassword) {
		this.userName = strUserName;
		this.password = strPassword;
		this.rememberPassword = bIsRememberPassword;
	}

	public String getUserName() {
		return this.userName;
	}

	public String getPassword() {
		return this.password;
	}

	public boolean getRememberPassword() {
		return rememberPassword;
	}

	public void setRememeberPassword(boolean bIsRememberPassword) {
		rememberPassword = bIsRememberPassword;
	}
}
