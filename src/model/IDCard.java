package model;

import java.util.Date;

public class IDCard {
	private String cardNum;
	private Date issuedDate;
	private String issuedPlace;

	public IDCard() {
	}

	public IDCard(String cardNum, Date issuedDate, String issuedPlace) {
		this.cardNum = cardNum;
		this.issuedDate = issuedDate;
		this.issuedPlace = issuedPlace;
	}

	public String getCardNum() {
		return cardNum;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}

	public Date getIssuedDate() {
		return issuedDate;
	}

	public void setIssuedDate(Date issuedDate) {
		this.issuedDate = issuedDate;
	}

	public String getIssuedPlace() {
		return issuedPlace;
	}

	public void setIssuedPlace(String issuedPlace) {
		this.issuedPlace = issuedPlace;
	}

}
