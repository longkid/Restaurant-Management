package model;
/*
 * @author Tu Thi Xuan Hien
 */
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FileProcessing {
	public static String FILENAME_POSITION = "positiondata.dat";
	public static String FILENAME_EMPLOYEE = "employeedata.dat";
	public static String FILENAME_USERLOGIN = "userlogin.dat";
	
	/* *******************************************************************************
	 * Description: 
	 * 		this method is used to read an object
	 * 		the result will be returned to an Object, so we must convert the type 
	 * 		example: to read the employee list from the file:
	 * 			CListEmployee list=(CListEmployee) ProcessFile.ReadData(ProcessFile.FILENAME_EMPLOYEE);	
	 * Parameters:
	 * 		String strPath - the path to the file
	 * Exception:
	 * 		Error occurs if the file does not exist, or can't be accessed
	 * return type: Object
	 * *******************************************************************************/
	public static Object ReadData(String strPath) {
		Object obj = null;
		try {
			FileInputStream file = new FileInputStream(strPath);
			ObjectInputStream instream = new ObjectInputStream(file);
			obj = instream.readObject();
			instream.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		return obj;
	}
	
	/* *******************************************************************************
	 * Description: 
	 * 		this method is used to write an object into a file
	 * 		list: type CListEmployee	
	 * 		if the saving is successful, return TRUE, otherwise return FALSE
	 * Parameters:
	 * 		Object obj 
	 * 		String strPath 
	 * Exception:
	 * 		error occurs if the path is invalid or the drive can't be accessed   
	 * return type: boolean
	 * *******************************************************************************/
	public static boolean WriteData(Object obj, String strPath) {
		boolean bResult = false;
		try {
			FileOutputStream file = new FileOutputStream(strPath);
			ObjectOutputStream outstream = new ObjectOutputStream(file);
			outstream.writeObject(obj);
			outstream.close();
			bResult = true;
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return bResult;
	}
}
