package model;

import java.io.Serializable;

/*
 * @author Tu Thi Xuan Hien
 */
public enum PositionTitle implements Serializable {
	ACCOUNTANT, HEAD_ACCOUNTANT, CASHIER, DIRECTOR,
	CHEF, EXECUTIVE_CHEF, BUSBOY, DISHWASHER, RUNNER, SERVER, HEAD_SERVER;

	public static String getTitleString(PositionTitle title) {
		switch (title) {
		case ACCOUNTANT:
			return "Accountant";
		case HEAD_ACCOUNTANT:
			return "Head Accountant";
		case CASHIER:
			return "Cashier";
		case DIRECTOR:
			return "Director";
		case CHEF:
			return "Chef";
		case EXECUTIVE_CHEF:
			return "Executive Chef";
		case BUSBOY:
			return "Busboy";
		case DISHWASHER:
			return "Dishwasher";
		case RUNNER:
			return "Runner";
		case SERVER:
			return "Server";
		case HEAD_SERVER:
			return "Head Server";
		default:
			return null;
		}
	}
}
