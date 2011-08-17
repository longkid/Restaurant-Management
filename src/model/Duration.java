package model;

import java.io.Serializable;

public enum Duration  implements Serializable{
	TWO_MONTHS, // probation period
	ONE_YEAR, THREE_YEARS, NO_LIMIT;
	
	public static String getDurationString(Duration duration) {
		switch (duration) {
		case TWO_MONTHS:
			return "Two Months";
		case ONE_YEAR:
			return "One Year";
		case THREE_YEARS:
			return "Three Years";
		case NO_LIMIT:
			return "No Limit";
		default:
			return null;
		}
	}
}
