package model;

import java.io.Serializable;

/*
 * @author Tu Thi Xuan Hien
 */
public enum WorkingTime  implements Serializable{
	DAY_SHIFT, 		// 9 am - 4 pm
	NIGHT_SHIFT, 	// 4 pm - 11 pm
	OFFICE_HOURS	// 8 am - 5 pm
}
